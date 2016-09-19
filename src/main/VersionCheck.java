package main;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import resources.lang.Language;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class VersionCheck {

	//TODO: Change version
	public static String version = "1.4";

	public static String[] getVersion() throws IOException {
		URL url = new URL("https://raw.githubusercontent.com/KingOfDog/Kopfrechen-Trainer/master/version.json");
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(url.openStream());
			reader = new BufferedReader(isr);
			List<String> lines = new ArrayList<String>();
			String line = null;
			while((line = reader.readLine()) != null) {
				lines.add(line);
			}
			String verNew = lines.get(1).split(": ")[1].split("\"")[1];
			String path = lines.get(2).split(": ")[1].split("\"")[1];
			String version_info = lines.get(3).split(": ")[1].split("\"")[1];
			String check_sum = lines.get(4).split(": ")[1].split("\"")[1];
			String[] result = {verNew, path, version_info, check_sum};
			return result;
		} catch(Exception e) {
			return null;
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
	}
	
	public static byte[] createChecksum(File file) throws Exception {
		InputStream fis =  new FileInputStream(file);
		
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
		
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	public static String getMD5Checksum(File file) throws Exception {
		byte[] b = createChecksum(file);
		String result = "";
       
		for (int i=0; i < b.length; i++) {
			result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}
	
	public static String[] update() throws IOException, InvocationTargetException {
		String[] verInfo = getVersion();
		if(verInfo == null) {
			String[] result = {"false", version, version, version, version};
			return result;
		}
		String ver = null;
		String path = null;
		String versionInfo = null;
		String checkSum = null;
		String update = "false";
		if(!version.equals(verInfo[0])) {
			update = "true";
			path = verInfo[1];
			versionInfo = verInfo[2];
			checkSum = verInfo[3];
			ver = verInfo[0];
		}
		String[] result = {update, ver, path, versionInfo, checkSum};
		return result;
	}
	
	public static void getUpdate(String path, String checkSum, String version) throws Exception {
		File file = new File(VersionCheck.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\..\\kopfrechen-trainer-" + version + ".exe");
		FileUtils.copyURLToFile(new URL(path), file);
		String checkSumLocal = getMD5Checksum(file);
		if(checkSumLocal.equals(checkSum)) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(Language.get("update.success"));
			alert.setHeaderText(String.format(Language.get("update.success.header"), version));
			alert.setContentText(String.format(Language.get("update.success.content"), version));
			
			ButtonType newVersion = new ButtonType(Language.get("update.version.new"), ButtonData.APPLY);
			ButtonType oldVersion = new ButtonType(Language.get("update.version.old"), ButtonData.CANCEL_CLOSE);
			
			alert.getButtonTypes().setAll(newVersion, oldVersion);
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == newVersion) {
				ProcessBuilder p = new ProcessBuilder();
				String newPath = VersionCheck.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\..\\kopfrechen-trainer-" + version + ".exe";
				p.command(newPath);
				p.start();
				
				System.exit(0);
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(Language.get("update.failed"));
			alert.setHeaderText(String.format(Language.get("update.failed.header"), version));
			alert.setContentText(Language.get("update.failed.content"));
			
			ButtonType link = new ButtonType(Language.get("update.download"));
			ButtonType cancel = new ButtonType(Language.get("update.cancel"), ButtonData.CANCEL_CLOSE);
			
			alert.getButtonTypes().setAll(link, cancel);
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == link) {
				Desktop.getDesktop().browse(new URI(path));
			}
		}
	}
	
}
