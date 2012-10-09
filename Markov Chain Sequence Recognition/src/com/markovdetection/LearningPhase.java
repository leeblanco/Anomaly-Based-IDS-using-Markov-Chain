package com.markovdetection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LearningPhase extends TableOfInitialProbabilities {

	static private HTTPRequest<LinkedHashMap<String, Double>> tableCalculatedFreq = new HTTPRequest<LinkedHashMap<String, Double>>();
	private HTTPRequest<Double> RowSummation = new HTTPRequest<Double>();
	static private HTTPRequest<String> maps = new HTTPRequest<String>();
	
	public LearningPhase(){
	}
	
	/*
	 * This method will count the frequency of each URL pair per row or URL pair sequence whichever is easier to understand 
	 * This is done by first summing up all the frequencies or the number of times each URL character pair in a row occurs
	 * Let's say we have GE=2 GG=3 GT=4 total for that row 9. The total sum (9) is then used as a divisor to calculate the 
	 * relative frequency of each URL character pair but only for that row. If in the next row the total sum is 10, then that
	 * number will be used to calculate relative frequency for that row. The effect of this calculation is a vector probability
	 * where the summation of each rows relative frequencies is always equal to 1.
	 * */
	public void calculateFreq(){
		
		//check if getTableOfConcatChar contains an entry
		if (getTable().getListOfHttpReq().size()>0){
			
			for (int x=0; x<getTable().getListOfHttpReq().size(); x++){
			
				LinkedHashMap<String, Double> entry = getTable().getListOfHttpReq().get(x);
				Iterator <String>iterKeys = entry.keySet().iterator(); // use to iterate over keys of charSequence per x iteration
				Iterator <Double> iterVals = entry.values().iterator();// use to iterate over values of charSequence per x iteration
				
				while (iterKeys.hasNext() && iterVals.hasNext()){
					String getKeys = iterKeys.next(); //assigns each key value (String) to getKeys per iteration
					Double getVal = iterVals.next();//assigns each value (Double) to getVal per iteration
					
					entry.put(getKeys, (getVal/RowSummation.getListOfHttpReq().get(x)));
					
				}
				tableCalculatedFreq.addHTTPReq(entry);
			}
		}
		else{
			System.out.println("Table from TIP class is empty");
		}
	}
	
	public void calcSumPerRow(){
		if (getTable().getListOfHttpReq().size()>0){
			
			for (LinkedHashMap<String, Double> valEntries: getTable().getListOfHttpReq()){
				Double sumTotalVal = 0.0;
				for (Double val: valEntries.values()){
					sumTotalVal += val;
				}
				RowSummation.addHTTPReq(sumTotalVal);
			}
		}
		
		else{
			System.out.println("Get Table is empty");
		}
	}
	
	
	/*Useful Method but wasn't use in the process
	 *This method copies TableCalculatedFreq entries to maps. TableCalculatedFreq is List<Map<String, Double> while maps
	 *is one dimensional LinkedHashMap<String, Double thus it is necessary to write the extra while loop to get the Key
	 *and values. This method exists so that in the comparison later in Detection phase only two for loops will be created
	 *which is O(n^2) instead or 3 for loops the same as the original code. 
	 */
	public void copyTableCalcFreqToOneDMap(){
		if (tableCalculatedFreq.getListOfHttpReq().size()>0){
			System.out.print("\nCopying calculated frequencies to one-D map");
			for (LinkedHashMap<String, Double> mapEntry: tableCalculatedFreq.getListOfHttpReq()){
				Iterator<String> iterKey = mapEntry.keySet().iterator();
				Iterator<Double> iterVal = mapEntry.values().iterator();
				
				//System.out.print("\n Key-Value pair: ");
				while (iterKey.hasNext() && iterVal.hasNext()){
					String keys = iterKey.next();
					Double value = iterVal.next();
					maps.putHTTPCharToTable(keys, value);
					//System.out.print(" "+keys+" "+value);
				}
				//System.out.println();
			}
		}
		else {
			System.out.println("\ntableCalculatedfrequency is empty");
		}
		
	}
	//Useful method but didn't use it
	public HTTPRequest<String> getTableCalcFreqToOneDMap(){
		return maps;
	}
	
	
	public HTTPRequest<LinkedHashMap<String, Double>> getTableCalculatedFreq(){
		return tableCalculatedFreq;
	}
	
	//optional for debugging
	public void printMaps(){
		if (maps.getTableOfInitProb().size()>0){
			System.out.println("\nOne Dimensional Map calculated Frequencies");
			for (Map.Entry<String, Double> getEntry: maps.getTableOfInitProb().entrySet()){
				System.out.print(getEntry+",");
			}
		}
		else{
			System.out.println("Maps is empty size: "+maps.getTableOfInitProb().size());
		}
	}
	
	public void printFrequencies(){
		System.out.println("\nRelative Frequencies size: "+ tableCalculatedFreq.getListOfHttpReq().size());
		for (LinkedHashMap<String, Double> entries: tableCalculatedFreq.getListOfHttpReq()){
			System.out.println(entries);
		}
	}

	public void printRowSummation(){
		System.out.println("RowSummation: "+ RowSummation.getListOfHttpReq()+ " ");
	}
}