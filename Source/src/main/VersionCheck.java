package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VersionCheck {

	public static String version = "1.0";

	public static String[] getVersion() throws IOException {
		URL url = new URL("https://raw.githubusercontent.com/KingOfDog/Kopfrechen-Trainer/master/version.json");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
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
			e.printStackTrace();
			return null;
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
	}
	
	public static String[] update() throws IOException {
		String[] verInfo = getVersion();
		String ver = null;
		String path = null;
		String versionInfo = null;
		String checkSum = null;
		String update = "false";
		if(verInfo[0] != version) {
			update = "true";
			path = verInfo[1];
			versionInfo = verInfo[2];
			checkSum = verInfo[3];
			ver = verInfo[0];
		}
		String[] result = {update, ver, path, versionInfo, checkSum};
		return result;
	}
	
}
