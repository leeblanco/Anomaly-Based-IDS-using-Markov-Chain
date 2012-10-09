package com.markovdetection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class TableOfInitialProbabilities extends fileManipulation {

	static private HTTPRequest<List<String>> tableOfConcatChar = new HTTPRequest<List<String>>();
	static private HTTPRequest<LinkedHashMap<String, Double>> table = new HTTPRequest<LinkedHashMap<String, Double>>();
	static private HTTPRequest<String> addURLTableCopy = new HTTPRequest<String>();

	static private HTTPRequest<String> ListOfDetectedURLPairs = new HTTPRequest<String>();
	
	public TableOfInitialProbabilities(){	
		
	}
	
	public TableOfInitialProbabilities(HTTPRequest<String> getDetectedURL){
		parseURLToChar();
		concatURLCharGeneric();
	}
	
	public void concatURLChar(){
		//StringBuilder concatChar = new StringBuilder();
		String concatChar;
		
		if (addURLTableCopy.getListOfHttpReq().size()>0){
			for (int x=0; x<addURLTableCopy.getListOfHttpReq().size(); x++){
				List<String> tempAdd = new ArrayList<String>();//optional debugging
				HTTPRequest<String> tableOfConcatCharVer2 = new HTTPRequest<String>();
				
				for (int y=0; y<addURLTableCopy.getListOfHttpReq().size(); y++){
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
	 *concatURLCharGeneric has a triple for loop O(n^3) which concatenates URL characters into pairs.
	 *The concatenated characters came from parseURLToChar method. This method has a similar goal as concatURLChar
	 *but there is a difference on how the algorithm is written. The reason for is that URL's are being 
	 *detected one by one. Unlike the process in Learning phase where one big table is created from all the 
	 *unique URL characters processed during learning phase. The 2nd and 3rd loop has this kind of output after 
	 *concatenation. Example boy, girl is an element in getList List<String>
	 *bb bo by
	 *ob oo oy
	 *yb yo yy
	 *
	 *goes to first loop and process next element which is girl
	 *gg gi gr gl
	 *ig ii ir il
	 *rg ri rr rl
	 *lg li lr ll
	 *
	 *After the Character Pair is created is all stored in ListofDetectURLPairs
	 */
	public void concatURLCharGeneric(){
		String concatChar = null;
		
		System.out.println("\nStart concatenation process in detection phase");
		
		if (parseURLToChar().getListOfHttpReq().size()>0){
			for (List<String> getList: parseURLToChar().getListOfHttpReq()){
				for (int x=0; x<getList.size(); x++){
					for (int y=0; y<getList.size(); y++){
						concatChar = getList.get(x).concat(getList.get(y));
						ListOfDetectedURLPairs.addHTTPReq(concatChar);
					}
				}
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
			//System.out.print(KeyEntry+" ");
			addURLTableCopy.addHTTPReq(KeyEntry);
		}	
		//System.out.println("\nCopy addURL "+addURLTableCopy.getListOfHttpReq()+" ");
	}
	
	/*
	 * Parse URL into character pairs for example GET yahoo.com after parsing 
	 * G, E, T, , y, a, h, o, o, ., c, o, m and then stored in a LinkedHashMap<String, Double>
	 */
	public HTTPRequest<List<String>> parseURLToChar(){
		System.out.println("Parse URL to Character and store in map");
		
		HTTPRequest<LinkedHashMap<String, Double>> listOfParsedURLPairs = new HTTPRequest<LinkedHashMap<String, Double>>();
		
		for (String entry: getAddDetectedURL().getTableOfInitProb().keySet()){
			HTTPRequest<String> storeParseURLPairs = new HTTPRequest<String>();
			for (int x=0; x<entry.length(); x++){
				String parseHTTPURL = Character.toString(entry.charAt(x));
				storeParseURLPairs.putHTTPCharToTable(parseHTTPURL, 1.0);
			}
			listOfParsedURLPairs.addHTTPReq(storeParseURLPairs.getTableOfInitProb());
		}
		
		/*Copies the Parsed URL character from ListOFParsedURLPairs this is done so that it will be compatible with concatCharURLGeneric 
		*algorithms or logic flow
		*/
		HTTPRequest<List<String>> copyParsedKeys = new HTTPRequest<List<String>>();
		for (LinkedHashMap<String, Double> entry: listOfParsedURLPairs.getListOfHttpReq()){
			List<String> temp = new ArrayList<String>();
			for (String getKeys: entry.keySet()){
				temp.add(getKeys);
			}
			copyParsedKeys.addHTTPReq(temp);
		}
		return copyParsedKeys;
	}
	
	//optional method
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
	
	public void printListOfDetectedURLPairs(){
		System.out.println("\nListOfDetectedURLPairs");
		for (String url: ListOfDetectedURLPairs.getListOfHttpReq()){
			System.out.print(url+",");
		}
	}
	
	public HTTPRequest<String> getListOfDetectedURLPairs(){
		return ListOfDetectedURLPairs;
	}
	
	public HTTPRequest<LinkedHashMap<String,Double>> getTable(){
		return table;
	}
}


/*
 * //Works exactly the same as concatURLCharGeneric except that it is used for DetectionPhase class
	public void concatURLCharGeneric(HTTPRequest<String> addURLtableForDetection){
		String concatChar;
		HTTPRequest<String> KeyList = new HTTPRequest<String>();
		
		System.out.println("Size of AddDetectedURL: "+ getAddDetectedURL().getTableOfInitProb().keySet().size());
		//Copies the Key Entries or the unique URL characters from addURL Map entries
		for (String KeyEntry: addURLtableForDetection.getTableOfInitProb().keySet()){
			System.out.print(KeyEntry+",");
			//addURLtableForDetection.addHTTPReq(KeyEntry);
			KeyList.addHTTPReq(KeyEntry);	
		}
		System.out.print("\nSize: "+KeyList.getListOfHttpReq().size());//optional
		System.out.println("\nDetection copied key set");//optional
		//optional
		for (String keys: KeyList.getListOfHttpReq()){
			System.out.print(keys+" ");
		}
		
		if (KeyList.getListOfHttpReq().size()>0){
			for (int x=0; x<KeyList.getListOfHttpReq().size(); x++){
				List<String> tempAdd = new ArrayList<String>();//optional debugging
				HTTPRequest<String> tableOfConcatCharVer2 = new HTTPRequest<String>();
				
				for (int y=1; y<KeyList.getListOfHttpReq().size(); y++){
					//This line copies URL character from List which includes redundant characters
					//concatChar = getAddURL().getListOfHttpReq().get(x).concat(getAddURL().getListOfHttpReq().get(y));
					//This line copies URL character from a Map which doesn't include URL characters whose copy is more than one
					concatChar = KeyList.getListOfHttpReq().get(x).concat(KeyList.getListOfHttpReq().get(y));
					
					Double ctr = tableOfConcatCharVer2.getTableOfInitProb().get(concatChar);
					//Use table to count frequencies of URL Character pair using HTTPRequest putHTTPCharToTable method
					tableOfConcatCharVer2.putHTTPCharToTable(concatChar, (ctr==null? ctr=1.0: ctr+1));
					//optional for debugging
					tempAdd.add(concatChar);
				}
				
				//Using HTTPRequest addHTTPReq method table uses this to create a code equivalent to List<LinkedHashMap<String, Double>> 
				//thereby creating a table of URL character pairs with their corresponding frequencies
				tableForDetection.addHTTPReq(tableOfConcatCharVer2.getTableOfInitProb());
			}
		}
		else {
			System.out.println("\nAddURL is empty");
		}
	}
 * 
 * */
 