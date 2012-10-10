package com.markovdetection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class fileManipulation {

	static private HTTPRequest<String> addURL = new HTTPRequest<String>();
	static private HTTPRequest<String> addDetectedURL = new HTTPRequest<String>();
	//private HTTPRequest<Integer> storeChar = new HTTPRequest<Integer>();
	
	///home/dimaz/Documents/IDSTextFiles/SaturdayCharDistNormalSmall.txt
	//SatCharDistVerySmall.txt
	///home/dimaz/Documents/IDSTextFiles/Markov/MarkovDetectSmall.txt
	public void openUsingRead(){
		try{///home/dimaz/Documents/IDSTextFiles/SatCharDistVerySmall.txt
			BufferedReader buff = new BufferedReader(new FileReader("/home/dimaz/Documents/IDSTextFiles/SaturdayCharDistNormal.txt"));
			try{
				Double ctr = 0.0;
				int readIntData = buff.read(); //read txt file char by char
				
				while (readIntData!=-1){
					
						if (!(readIntData==13||readIntData==10)){ //if condition skips if ascii code for 10 = new line and 13 = carriage return is encountered
							//storeChar.putHTTPCharToTable(readIntData, ctr);
							//convert int to char or convert ascii code to human readable character then convert character value to String
							String convData = Character.toString(((char) readIntData)); 						
							//addURL.addHTTPReq(convData);
							addURL.putHTTPCharToTable(convData, ctr);						
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
	
	public HTTPRequest<String> openFileForDetection(){
		try{///home/dimaz/Documents/IDSTextFiles/CharDistSunSmall2.txt
			///home/dimaz/Documents/IDSTextFiles/Markov/MarkovDetectSmall.txt
			///home/dimaz/Documents/IDSTextFiles/CharDistSunday.txt
			BufferedReader buff = new BufferedReader(new FileReader("/home/dimaz/Documents/IDSTextFiles/CharDistSunday.txt"));
			try{
				Double ctr = 0.0;
				String readData = buff.readLine(); //read txt file char by char
				
				while (readData!=null){		
						if (!readData.isEmpty()){ //if condition skips if ascii code for 10 = new line and 13 = carriage return is encountered
							addDetectedURL.putHTTPCharToTable(readData, ctr);						
							ctr++;
						}
				readData = buff.readLine();
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
		return addDetectedURL;
	}
	
	public void showAddURLList(){
		System.out.println("\nURL in List");
		for (String x: addURL.getListOfHttpReq()){
			System.out.print(x+",");
		}
	}
	
	//Save results of Markov Detection in a text file
	public void saveLOGMAPResult(HTTPRequest<Double> getResults){
		
		try{
			BufferedWriter writeBuf = new BufferedWriter(new FileWriter("/home/dimaz/Documents/IDSTextFiles/Markov/Result.txt"));
			try{
				
				for (Double getVal: getResults.getListOfHttpReq()){
					String convDoubleToString = Double.toString(getVal);
					writeBuf.write(convDoubleToString);
					writeBuf.newLine();
				}
			}
			finally {
				writeBuf.close();
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found "+ e);
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
	//optional method used to show map entries of addURL
	public void showAddURL(){
		System.out.println("\nAdd URL: ");
		for (Map.Entry<String, Double> x: addURL.getTableOfInitProb().entrySet()){
			System.out.print(x+" ");
		}
	}
		 
	public void showDetectedURLs(){
		System.out.println("Show Detected URL Characters: ");
		for (Map.Entry<String, Double> entry: addDetectedURL.getTableOfInitProb().entrySet()){
			System.out.print(entry+" ");
		}
	}
	
	public HTTPRequest<String> getAddDetectedURL(){
		return addDetectedURL;
	}
	
	public HTTPRequest<String> getAddURL(){
		return addURL;
	}

}
/*
 * public HTTPRequest<String> openFileForDetection(){
		try{
			BufferedReader buff = new BufferedReader(new FileReader("/home/dimaz/Documents/IDSTextFiles/CharDistSunSmall2.txt"));
			try{
				Double ctr = 0.0;
				int readIntData = buff.read(); //read txt file char by char
				
				while (readIntData!=-1){
					
						if (!(readIntData==13||readIntData==10)){ //if condition skips if ascii code for 10 = new line and 13 = carriage return is encountered
							//storeChar.putHTTPCharToTable(readIntData, ctr);
							//convert int to char or convert ascii code to human readable character then convert character value to String
							String convData = Character.toString(((char) readIntData)); 
							
							//learningPhase.addHTTPReq(convData);
							addDetectedURL.putHTTPCharToTable(convData, ctr);						
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
		
		return addDetectedURL;
	}
 * */
 