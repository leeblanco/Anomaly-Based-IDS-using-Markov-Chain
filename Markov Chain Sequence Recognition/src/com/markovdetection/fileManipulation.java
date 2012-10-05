package com.markovdetection;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class fileManipulation {

	static private HTTPRequest<String> addURL = new HTTPRequest<String>();
	//static private HTTPRequest<String> learningPhase = new HTTPRequest<String>();
	//private HTTPRequest<List<String>> addChars = new HTTPRequest<List<String>>();
	private HTTPRequest<Integer> storeChar = new HTTPRequest<Integer>();
	
	///home/dimaz/Documents/IDSTextFiles/SaturdayCharDistNormalSmall.txt
	//SatCharDistVerySmall.txt
	public void openUsingRead(){
		try{
			BufferedReader buff = new BufferedReader(new FileReader("/home/dimaz/Documents/IDSTextFiles/SatCharDistVerySmall.txt"));
			try{
				Double ctr = 0.0;
				int readIntData = buff.read(); //read txt file char by char
				
				while (readIntData!=-1){
					
						if (!(readIntData==13||readIntData==10)){ //if condition skips if ascii code for 10 = new line and 13 = carriage return is encountered
							//storeChar.putHTTPCharToTable(readIntData, ctr);
							//convert int to char or convert ascii code to human readable character then convert character value to String
							String convData = Character.toString(((char) readIntData)); 
							addURL.addHTTPReq(convData);
							//learningPhase.addHTTPReq(convData);
							//addURL.putHTTPCharToTable(convData, ctr);						
							ctr++;
						}
				readIntData = buff.read();
				}
			}
			
			finally{                   
				buff.close();
			}	
			
		}
		catch(FileNotFoundException e){
			System.out.println("File not found"+e);
		}
		catch(IOException e){
			System.out.println("oist exception");
		}
	}
	
	
	
	//optional method debugging 
	/*public void copyHTTPCharKeysFromMap(){
		for (Map.Entry<List<String>, Double> entry: addMe.getTableOfInitProb().entrySet()){
			addChars.addHTTPReq(entry.getKey());
		}
		
	
	}*/
	
	public void showAddURLList(){
		System.out.println("\nURL in List");
		for (String x: addURL.getListOfHttpReq()){
			System.out.print(x+",");
		}
	}
	
	//optional method used to show map entries of addURL
	public void showAddURL(){
		System.out.println("\nAdd URL: ");
		for (Map.Entry<String, Double> x: addURL.getTableOfInitProb().entrySet()){
			System.out.print(x+" ");
		}
	}
	
	//optional method used to show map entries of storechar 
	public void showIntChars(){
		System.out.println("Show integer: ");
		for (Map.Entry<Integer, Double> entry: storeChar.getTableOfInitProb().entrySet()){
			System.out.print(entry+" ");
		}
	}
	
	
	public HTTPRequest<String> getAddURL(){
		return addURL;
	}
	
	/*
	 * Convert URI and store in a list of characters sample 
	 * from GET google.com and GET yahoo.com to List<String> G, E, T, , g, o, o, g, l, e,. ,c ,o ,m
	 *  List<String> G, E, T, , g, o, o, g, l, e, ., c, o, m
	 * 
	public List<String> parseHTTPReqToCharbyChar(String getHTTP){ 
		List<String> parsingReq = new ArrayList<String>();
		String convChar=null;

			for (int x = 0; x<getHTTP.length(); x++){
				convChar = Character.toString(getHTTP.charAt(x));
				parsingReq.add(convChar);
			}

		return parsingReq;
	}*/

}
