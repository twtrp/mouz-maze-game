package System;
import java.io.*;
import java.util.*;

public class Config {

	public static Properties cfg = new Properties();
	
	public void write(String key, String value) {
		try {
			cfg.setProperty(key, value);
			cfg.store(new FileOutputStream("saves.txt"), null);
		}
		catch(IOException ex) {
		}
	}
	public boolean getConfig(String key) {
		String value = "";
		try {
			cfg.load(new FileInputStream("saves.txt"));
			value = cfg.getProperty(key);
		}
		catch(IOException ex) {
			this.write("timerBool", "true");
			this.write("scaleBool", "true");
			this.write("coordBool", "false");
			if (key == "timerBool") {
				return true;
			}
			if (key == "scaleBool") {
				return true;
			}
			if (key == "coordBool") {
				return false;
			}
		}
		return Boolean.parseBoolean(value);
	}
	public int getTime(String key) {
		String valueStr = "";
		int valueInt = 0;
		try {
			cfg.load(new FileInputStream("saves.txt"));
			valueStr = cfg.getProperty(key);
		}
		catch(IOException ex) {
		}
		try {
			valueInt = Integer.parseInt(valueStr);
		}
		catch(NumberFormatException ex){
			valueInt = 99999;
		}
		return valueInt;
	}
	public boolean isCompleted(String key) {
		return cfg.containsKey(key);
	}
	public int saveSize() {
		return cfg.size()-3;
	}
	public void del(String key) {
		cfg.remove(key);
	}
}
