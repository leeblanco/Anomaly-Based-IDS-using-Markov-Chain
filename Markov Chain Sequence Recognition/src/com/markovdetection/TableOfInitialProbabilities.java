package com.markovdetection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class TableOfInitialProbabilities extends fileManipulation {

	static private HTTPRequest<List<String>> tableOfConcatChar = new HTTPRequest<List<String>>();
	static private HTTPRequest<LinkedHashMap<String, Double>> table = new HTTPRequest<LinkedHashMap<String, Double>>();
	static private HTTPRequest<String> addURLTableCopy = new HTTPRequest<String>();
	
	public TableOfInitialProbabilities(){	
	}
	
	public void concatURLChar(){
		//StringBuilder concatChar = new StringBuilder();
		String concatChar;
		
		if (addURLTableCopy.getListOfHttpReq().size()>0){
			for (int x=0; x<addURLTableCopy.getListOfHttpReq().size(); x++){
				List<String> tempAdd = new ArrayList<String>();//optional debugging
				HTTPRequest<String> tableOfConcatCharVer2 = new HTTPRequest<String>();
				
				for (int y=1; y<addURLTableCopy.getListOfHttpReq().size(); y++){
					//This line copies URL character from List which includes redundant characters
					//concatChar = getAddURL().getListOfHttpReq().get(x).concat(getAddURL().getListOfHttpReq().get(y));
					//This line copies URL character from a Map which doesn't include URL characters whose copy is more than one
					concatChar = addURLTableCopy.getListOfHttpReq().get(x).concat(addURLTableCopy.getListOfHttpReq().get(y));
					
					Double ctr = tableOfConcatCharVer2.getTableOfInitProb().get(concatChar);
					//Use table to count frequencies of URL Character pair using HTTPRequest putHTTPCharToTable method
					tableOfConcatCharVer2.putHTTPCharToTable(concatChar, (ctr==null? ctr=1.0: ctr+1));
					//optional for debugging
					tempAdd.add(concatChar);
				}
				
				//Using HTTPRequest addHTTPReq method table uses this to create a code equivalent to List<LinkedHashMap<String, Double>> 
				//thereby creating a table of URL character pairs with their corresponding frequencies
				table.addHTTPReq(tableOfConcatCharVer2.getTableOfInitProb());
				//optional debugging
				tableOfConcatChar.addHTTPReq(tempAdd);
			}
		}
		else {
			System.out.println("\nAddURL is empty");
		}
	}
	
	
	public void concatURLCharGeneric(){
		//StringBuilder concatChar = new StringBuilder();
		String concatChar;
		
		if (addURLTableCopy.getListOfHttpReq().size()>0){
			for (int x=0; x<addURLTableCopy.getListOfHttpReq().size(); x++){
				List<String> tempAdd = new ArrayList<String>();//optional debugging
				HTTPRequest<String> tableOfConcatCharVer2 = new HTTPRequest<String>();
				
				for (int y=1; y<addURLTableCopy.getListOfHttpReq().size(); y++){
					//This line copies URL character from List which includes redundant characters
					//concatChar = getAddURL().getListOfHttpReq().get(x).concat(getAddURL().getListOfHttpReq().get(y));
					//This line copies URL character from a Map which doesn't include URL characters whose copy is more than one
					concatChar = addURLTableCopy.getListOfHttpReq().get(x).concat(addURLTableCopy.getListOfHttpReq().get(y));
					
					Double ctr = tableOfConcatCharVer2.getTableOfInitProb().get(concatChar);
					//Use table to count frequencies of URL Character pair using HTTPRequest putHTTPCharToTable method
					tableOfConcatCharVer2.putHTTPCharToTable(concatChar, (ctr==null? ctr=1.0: ctr+1));
					//optional for debugging
					tempAdd.add(concatChar);
				}
				
				//Using HTTPRequest addHTTPReq method table uses this to create a code equivalent to List<LinkedHashMap<String, Double>> 
				//thereby creating a table of URL character pairs with their corresponding frequencies
				table.addHTTPReq(tableOfConcatCharVer2.getTableOfInitProb());
				//optional debugging
				tableOfConcatChar.addHTTPReq(tempAdd);
			}
		}
		else {
			System.out.println("\nAddURL is empty");
		}
	}
	
	
	/*
	 * Copies the Key Entries or the unique URL characters from addURL Map entries. This is done since Map has a property that 
	 * it only retains one unique copy of a Character and any redundancy is weed out. addURLTableCopy is a list used to store 
	 * the Key Entries from addURL Map.
	 * */
	public void copyAddURLTableFromMap(){
		//System.out.println("Copy addURL size: "+getAddURL().getTableOfInitProb().size());
		for (String KeyEntry: getAddURL().getTableOfInitProb().keySet()){
			System.out.print(KeyEntry+" ");
			addURLTableCopy.addHTTPReq(KeyEntry);
		}	
		//System.out.println("\nCopy addURL "+addURLTableCopy.getListOfHttpReq()+" ");
	}
	
	

	
	public void printConcatChars(){
		System.out.println("\nConcatenated URL char");
		for (List<String> elements: tableOfConcatChar.getListOfHttpReq())
			System.out.println(elements+" ");
		
	}
	
	//optional method to print contents of Table
	public void printTable(){
		System.out.println("\nTable in List");
		for (LinkedHashMap<String, Double> entry: table.getListOfHttpReq()){
			System.out.println(entry+" ");
		}
	}
	
	
	
	//optional for optimization -- converts List<StringBuffer> to List<String>
	public List<String> convStringBufferToString(List<StringBuilder> buff){
		List<String> conv = new ArrayList<String>();
		for (StringBuilder x: buff){
			conv.add(x.toString());
		}
		return conv;
	}
	
	
	public HTTPRequest<String> setURLs(){
		return getAddURL();
	}
	
	public HTTPRequest<List<String>> getTableOfConcatChar(){
		return tableOfConcatChar;
	}
	
	public HTTPRequest<LinkedHashMap<String,Double>> getTable(){
		return table;
	}
}
