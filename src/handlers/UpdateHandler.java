package handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import org.apache.commons.io.FileUtils;
import resources.lang.Language;
import updates.Version;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.util.NoSuchElementException;

public class UpdateHandler {

    //TODO: Change version
    public static String version = "1.5";

    private Gson gson;

    public UpdateHandler() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public Version getVersion() {
        try {
            String content = downloadURL("https://raw.githubusercontent.com/KingOfDog/MATH/master/version.json");

            return gson.fromJson(content, Version.class);
        } catch (IOException e) {
            System.err.println("Error while downloading Version-Information: " + e.getMessage());
            return new Version();
        }
    }

    private String downloadURL(URL url) throws IOException {
        InputStreamReader input = new InputStreamReader(url.openStream());
        BufferedReader reader = new BufferedReader(input);

        StringBuilder content = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        reader.close();
        return content.toString();
    }

    private String downloadURL(String url) throws IOException {
        return downloadURL(new URL(url));
    }

    public static String getLineValue(String line) {
        return line.split(": ")[1].split("\"")[1];
    }


    public Version update() {
        return getVersion();
    }

    public void getUpdate(String path, String checkSum, String version) throws Exception {
        String filePath = UpdateHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\..\\math-" + version + ".exe";
        File file = new File(filePath);
        FileUtils.copyURLToFile(new URL(path), file);
        String checkSumLocal = getMD5Checksum(file);

        if (checkSumLocal.equals(checkSum)) {
            if (this.confirmUpdate()) {
                ProcessBuilder p = new ProcessBuilder();
                String newPath = UpdateHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\..\\math-" + version + ".exe";
                p.command(newPath);
                p.start();

                System.exit(0);
            }
        } else {
            if (this.confirmManualInstall()) {
                Desktop.getDesktop().browse(new URI(path));
            }
        }
    }

    private boolean confirmUpdate() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Language.get("update.success"));
        alert.setHeaderText(String.format(Language.get("update.success.header"), version));
        alert.setContentText(String.format(Language.get("update.success.content"), version));

        ButtonType newVersion = new ButtonType(Language.get("update.version.new"), ButtonData.APPLY);
        ButtonType oldVersion = new ButtonType(Language.get("update.version.old"), ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(newVersion, oldVersion);
        try {
            ButtonType selected = alert.showAndWait().get();
            return selected == newVersion;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean confirmManualInstall() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(Language.get("update.failed"));
        alert.setHeaderText(String.format(Language.get("update.failed.header"), version));
        alert.setContentText(Language.get("update.failed.content"));

        ButtonType link = new ButtonType(Language.get("update.download"));
        ButtonType cancel = new ButtonType(Language.get("update.cancel"), ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(link, cancel);

        try {
            ButtonType selected = alert.showAndWait().get();
            return selected == link;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public byte[] createChecksum(File file) throws Exception {
        InputStream fis = new FileInputStream(file);

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

    public String getMD5Checksum(File file) throws Exception {
        byte[] b = createChecksum(file);
        String result = "";

        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
