package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Options;

public class SettingsService {
	private static final String JSON_URL =  "settings.json";
	private static Options options;
	
	
	private static void readSettingsFile() {
		Object object;
		JSONObject jo;
		
		String ip;
		String port;
		String user;
		String password;
		String columnas;
		String filas;
		try {
			object = new JSONParser().parse(new FileReader(JSON_URL));
			jo = (JSONObject) object;
			
			ip = (String) jo.get("ip");
			port = (String) jo.get("port");
			user = (String) jo.get("user");
			password = (String) jo.get("password");
			
			if(jo.get("columnas")!=null && !jo.get("columnas").toString().isEmpty()) {
				columnas = (String) jo.get("columnas");
			}else {
				columnas = "2";
			}
			
			if(jo.get("filas")!=null && !jo.get("filas").toString().isEmpty()) {
				filas = (String) jo.get("filas");
			}else {
				filas = "5";
			}
			if(ip == null) ip = "";
			if(port == null) port = "";
			if(user == null) user = "";
			if(password == null) password = "";
			
			options = new Options(ip,port,user,password,columnas,filas);
		} catch (FileNotFoundException e) {
			try {
				
				createFile();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public static Options getOptions() {
		if(options == null) {
			readSettingsFile();
		}
		return options;
	}
	
	private static void createFile() throws IOException {
		FileWriter writer = new FileWriter(JSON_URL);
		try(writer){
			writer.write("{}");
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void writeSettings(Options options) throws IOException, ParseException {
		Object object;
		JSONObject jo;
		PrintWriter pw;
		
		object = new JSONParser().parse(new FileReader(new File(JSON_URL)));
		jo = (JSONObject) object;

		jo.put("ip", options.getIp());
		jo.put("port", options.getPort());
		jo.put("user", options.getUser());
		jo.put("password", options.getPassword());
		jo.put("columnas", options.getColumnas());
		jo.put("filas", options.getFilas());
		
		pw = new PrintWriter(JSON_URL);
		pw.write(jo.toJSONString());
		pw.flush();
		pw.close();
		SettingsService.options = options;
		
		
	}

}
