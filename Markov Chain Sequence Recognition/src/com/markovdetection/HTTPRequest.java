package com.markovdetection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class HTTPRequest<T> {

	private List <T> listOfReq = new ArrayList<T>();
	private LinkedHashMap<T, Double> tableOfInitProb = new LinkedHashMap<T, Double>();
	
	
	public HTTPRequest(){	
	}
	
	public HTTPRequest(T entry){
		listOfReq.add(entry);
	}
	
	public HTTPRequest(T entry, Double value){
		tableOfInitProb.put(entry, value);
	}
	
	
	public void addHTTPReq (T uri){
		listOfReq.add(uri);
	}
	
	public void putHTTPCharToTable(T key, Double value){
		tableOfInitProb.put(key, value);
	}
	
	
	public List<T> getListOfHttpReq(){
		return listOfReq;
	}
	
	public LinkedHashMap<T, Double> getTableOfInitProb(){
		return tableOfInitProb;
	}
	
	
}
/*	public void printList(){
for (T element: listOfReq){
	System.out.print(element.toString()+" ");
}
}

public void printTables(){
for (Map.Entry<T, Double> entry: tableOfInitProb.entrySet()){
	//System.out.println(entry.getKey().toString()+" = "+ entry.getValue().toString()+" -- ");
	System.out.print(entry.toString()+" ");
}
}*/
