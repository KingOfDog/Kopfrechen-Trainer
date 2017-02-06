package handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import properties.BooleanProperty;
import properties.IntegerProperty;
import properties.LocaleProperty;
import settings.Settings;
import settings.SettingsDeserializer;
import statistics.Statistics;
import statistics.StatisticsDeserializer;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Scanner;

public class FileHandler {

    public static void main() throws Exception {
        File dir = new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\stats.json");
        if (!file.exists()) {
            file.createNewFile();
            writeStats();
        }
        File settings = new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\settings.json");
        if (!settings.exists()) {
            settings.createNewFile();
            writeSettings();
        }
    }

    public static void writeSettings() {
        Settings obj = Settings.getInstance();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.registerTypeAdapter(BooleanProperty.class, new BooleanProperty.Serializer());
        gsonBuilder.registerTypeAdapter(IntegerProperty.class, new IntegerProperty.Serializer());
        gsonBuilder.registerTypeAdapter(LocaleProperty.class, new LocaleProperty.Serializer());
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(obj);
        try (FileWriter file = new FileWriter(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\settings.json")) {
            file.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getSettings() throws FileNotFoundException {
        FileReader file = new FileReader(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\settings.json");
        BufferedReader reader = new BufferedReader(file);
        StringBuilder text = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Settings.class, new SettingsDeserializer());
        Gson gson = gsonBuilder.create();
        try {
            gson.fromJson(text.toString(), Settings.class);
        } catch (NullPointerException e) {
            System.out.println("Couldn't load settings from file.");
        }
    }

    public static void writeStats() throws Exception {
        Statistics.setSession(Statistics.generateChecksum());
        Statistics obj = new Statistics();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(obj);
        try (FileWriter file = new FileWriter(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\stats.json")) {
            file.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getStats() throws Exception {
        Scanner scanner = new Scanner(new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\stats.json"), "UTF-8");
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Statistics.class, new StatisticsDeserializer());
        Gson gson = gsonBuilder.create();
        gson.fromJson(text, Statistics.class);
        if (!Statistics.generateChecksum().equals(Statistics.getSession())) {
            Statistics.clear();
        }
    }
}
