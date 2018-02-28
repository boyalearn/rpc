package com.rpc.io.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static String HOST;
	
	public static int PORT;
	
	static{
		Properties prop = new Properties();  
		String fileName;
		try {
			fileName = new File("").getCanonicalPath()+File.separator+"config"+File.separator+"connection.properties";
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			prop.load(bufferedReader);
			HOST=prop.getProperty("host");
			PORT=new Integer(prop.getProperty("port"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) throws IOException{
		
		System.out.println(HOST);
		System.out.println(PORT);
	}
}
