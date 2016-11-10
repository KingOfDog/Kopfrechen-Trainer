package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;

import settings.Settings;
import settings.SettingsDeserializer;
import statistics.Statistics;
import statistics.StatisticsDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileHandler {

	public static void main() throws Exception {
		File dir = new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH");
		if(!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\stats.json");
		if(!file.exists()) {
			file.createNewFile();
			writeStats();
		}
		File settings = new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\settings.json");
		if(!settings.exists()) {
			settings.createNewFile();
			writeSettings();
		}
	}
	
	public static void writeSettings() {
		Settings obj = new Settings();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
		Gson gson = gsonBuilder.create();
		String json = gson.toJson(obj);
		try (FileWriter file = new FileWriter(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\settings.json")) {
			file.write(json);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getSettings() throws FileNotFoundException {
		Scanner scanner = new Scanner( new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\settings.json"), "UTF-8" );
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Settings.class, new SettingsDeserializer());
		Gson gson = gsonBuilder.create();
		gson.fromJson(text, Settings.class);
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void getStats() throws Exception {
		Scanner scanner = new Scanner( new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\..\\MATH\\stats.json"), "UTF-8" );
		String text = scanner.useDelimiter("\\A").next();
		scanner.close();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Statistics.class, new StatisticsDeserializer());
		Gson gson = gsonBuilder.create();
		gson.fromJson(text, Statistics.class);
		if(!Statistics.generateChecksum().equals(Statistics.getSession())) {
			Statistics.clear();
		}
	}
}
