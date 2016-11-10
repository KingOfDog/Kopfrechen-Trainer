package updates;

import java.util.HashMap;

public class VersionJSON {

    private HashMap<String, String> lines;

    public VersionJSON() {
        lines.put("version", "0.0.A0");
        lines.put("path", "unkown");
        lines.put("changelog", "unkown");
        lines.put("fileSize", "0B");
        lines.put("checksum", "unkown");
        lines.put("update", "false");
    }

//    private String getLine(int line) {
//        return lines.get(line);
//    }
//
//    private String getLineContent(int line) {
//        return getLine(line).split(": ")[1].split("\"")[1];
//    }
//
//    private void fillLinesList() {
//        for(String key : this.lines.keySet()) {
//            this.lines.replace(key, getLineContent());
//        }
//        for(int i = 0; i < 6; i++) {
//            this.lines.replace(this.lines.keySet()[i], getLineContent(i));
//        }
//    }
//
//    public HashMap<String, String> getResult() {
//        fillLinesList();
//        return this.lines;
//    }

}
