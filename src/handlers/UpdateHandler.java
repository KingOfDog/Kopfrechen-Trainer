//package handlers;
//
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.ButtonBar.ButtonData;
//import javafx.scene.control.ButtonType;
//import org.apache.commons.io.FileUtils;
//import resources.lang.Language;
//import sun.text.normalizer.VersionInfo;
//import updates.VersionJSON;
//
//import java.awt.*;
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URL;
//import java.security.MessageDigest;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//
//public class UpdateHandler {
//
//    //TODO: Change version
//    public static String version = "1.5";
//
//    public static List<String> getVersion() throws MalformedURLException {
//        URL url = new URL("https://raw.githubusercontent.com/KingOfDog/MATH/master/version.json");
//        BufferedReader reader = null;
//        try {
//            InputStreamReader inputstreamreader = new InputStreamReader(url.openStream());
//            reader = new BufferedReader(inputstreamreader);
//            List<String> lines = new ArrayList<>();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                lines.add(line);
//            }
//            return lines;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String getLineValue(String line) {
//        return line.split(": ")[1].split("\"")[1];
//    }
//
////    public static String[] getVersion() throws IOException {
////        URL url = new URL("https://raw.githubusercontent.com/KingOfDog/Kopfrechen-Trainer/master/version.json");
////        BufferedReader reader = null;
////        try {
////            InputStreamReader isr = new InputStreamReader(url.openStream());
////            reader = new BufferedReader(isr);
////            List<String> lines = new ArrayList<String>();
////            String line = null;
////            while ((line = reader.readLine()) != null) {
////                lines.add(line);
////            }
////            String verNew = lines.get(1).split(": ")[1].split("\"")[1];
////            String path = lines.get(2).split(": ")[1].split("\"")[1];
////            String version_info = lines.get(3).split(": ")[1].split("\"")[1];
////            String file_size = lines.get(4).split(": ")[1].split("\"")[1];
////            String check_sum = lines.get(5).split(": ")[1].split("\"")[1];
////            String[] result = {verNew, path, version_info, file_size, check_sum};
////            return result;
////        } catch (Exception e) {
////            return null;
////        } finally {
////            if (reader != null) {
////                reader.close();
////            }
////        }
////    }
//
//    public static HashMap<String, String> update() throws IOException, InvocationTargetException {
//        List<String> lines = getVersion();
//
//        VersionJSON json = new VersionJSON();
//        HashMap<String, String> versionInfo = json.getResult();
//
//        if(lines == null) {
//            return versionInfo;
//        }
//
//        if (!version.equals(verInfo[0])) {
//            update = "true";
//            path = verInfo[1];
//            versionInfo = verInfo[2];
//            checkSum = verInfo[3];
//            ver = verInfo[0];
//        }
//        String[] result = {update, ver, path, versionInfo, checkSum};
//        return result;
//    }
//
//    public static void getUpdate(String path, String checkSum, String version) throws Exception {
//        File file = new File(UpdateHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\..\\math-" + version + ".exe");
//        FileUtils.copyURLToFile(new URL(path), file);
//        String checkSumLocal = getMD5Checksum(file);
//        if (checkSumLocal.equals(checkSum)) {
//            Alert alert = new Alert(AlertType.CONFIRMATION);
//            alert.setTitle(Language.get("update.success"));
//            alert.setHeaderText(String.format(Language.get("update.success.header"), version));
//            alert.setContentText(String.format(Language.get("update.success.content"), version));
//
//            ButtonType newVersion = new ButtonType(Language.get("update.version.new"), ButtonData.APPLY);
//            ButtonType oldVersion = new ButtonType(Language.get("update.version.old"), ButtonData.CANCEL_CLOSE);
//
//            alert.getButtonTypes().setAll(newVersion, oldVersion);
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == newVersion) {
//                ProcessBuilder p = new ProcessBuilder();
//                String newPath = UpdateHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "\\..\\math-" + version + ".exe";
//                p.command(newPath);
//                p.start();
//
//                System.exit(0);
//            }
//        } else {
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle(Language.get("update.failed"));
//            alert.setHeaderText(String.format(Language.get("update.failed.header"), version));
//            alert.setContentText(Language.get("update.failed.content"));
//
//            ButtonType link = new ButtonType(Language.get("update.download"));
//            ButtonType cancel = new ButtonType(Language.get("update.cancel"), ButtonData.CANCEL_CLOSE);
//
//            alert.getButtonTypes().setAll(link, cancel);
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == link) {
//                Desktop.getDesktop().browse(new URI(path));
//            }
//        }
//    }
//
//    public static byte[] createChecksum(File file) throws Exception {
//        InputStream fis = new FileInputStream(file);
//
//        byte[] buffer = new byte[1024];
//        MessageDigest complete = MessageDigest.getInstance("MD5");
//        int numRead;
//
//        do {
//            numRead = fis.read(buffer);
//            if (numRead > 0) {
//                complete.update(buffer, 0, numRead);
//            }
//        } while (numRead != -1);
//
//        fis.close();
//        return complete.digest();
//    }
//
//    public static String getMD5Checksum(File file) throws Exception {
//        byte[] b = createChecksum(file);
//        String result = "";
//
//        for (int i = 0; i < b.length; i++) {
//            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
//        }
//        return result;
//    }
//}
