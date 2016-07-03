package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SaveFiles {

	public static void main() throws IOException {
		File dir = new File("Kopfrechen Trainer");
		if(!dir.exists()) {
			dir.mkdir();
		}
		File file = new File("Kopfrechen Trainer/stats.txt");
		if(!file.exists()) {
			file.createNewFile();
			writeEmpty();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public static void writeEmpty() throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("author", "Marcel Struck");
		
		JSONArray stats = new JSONArray();
		stats.add("exercises:0");
		stats.add("solved:0");
		obj.put("stats", stats);
		
		try (FileWriter file = new FileWriter("Kopfrechen Trainer/stats.txt")) {
			file.write(obj.toJSONString());
		}
	}
	
	public static void addOne(boolean solved) {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("Kopfrechen Trainer/stats.txt"));
			JSONObject json = (JSONObject) obj;
			JSONArray stats = (JSONArray) json.get("stats");
			@SuppressWarnings("unchecked")
			Iterator<String> iterator = stats.iterator();
			List<String> result = new ArrayList<String>();
			while(iterator.hasNext()) {
				String[] parts = iterator.next().split(":");
				result.add(parts[1]);
			}
			int ex = Integer.parseInt(result.get(0)) + 1;
			int exSol = Integer.parseInt(result.get(1));
			if(solved) {
				exSol++;
			}
			write(ex, exSol);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public static void write(int exercises, int solved) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("author", "KingOfDog");
		
		JSONArray stats = new JSONArray();
		stats.add("exercises:" + exercises);
		stats.add("solved:" + solved);
		obj.put("stats", stats);
		
		try (FileWriter file = new FileWriter("Kopfrechen Trainer/stats.txt")) {
			file.write(obj.toJSONString());
		}
	}
	
	public static List<String> read() {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("Kopfrechen Trainer/stats.txt"));
			JSONObject json = (JSONObject) obj;
			JSONArray stats = (JSONArray) json.get("stats");
			@SuppressWarnings("unchecked")
			Iterator<String> iterator = stats.iterator();
			List<String> result = new ArrayList<String>();
			
			while(iterator.hasNext()) {
				String[] parts = iterator.next().split(":");
				result.add(parts[1]);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
}
