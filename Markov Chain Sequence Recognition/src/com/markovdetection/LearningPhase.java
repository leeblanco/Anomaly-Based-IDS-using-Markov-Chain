package com.markovdetection;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LearningPhase extends TableOfInitialProbabilities {

	private HTTPRequest<LinkedHashMap<String, Double>> tableCalculatedFreq = new HTTPRequest<LinkedHashMap<String, Double>>();
	private HTTPRequest<Double> RowSummation = new HTTPRequest<Double>();
	
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
	}
	
	public void printFrequencies(){
		System.out.println("Relative Frequencies size: "+ tableCalculatedFreq.getListOfHttpReq().size());
		for (LinkedHashMap<String, Double> entries: tableCalculatedFreq.getListOfHttpReq()){
			System.out.println(entries);
		}
	}

	public void printRowSummation(){
		System.out.println("RowSummation: "+ RowSummation.getListOfHttpReq()+ " ");
	}
}

/*for (Double getVal: entry.values()){
sumRowVector = sumRowVector + getVal;
//rowSum.addHTTPReq(sumRowVector);
}
Iterator<Double> sum = entry.values().iterator();
Iterator<String> calcFreq = entry.keySet().iterator();
Double sumTotalVal = 0.0;

while (sum.hasNext()){
sumTotalVal += sum.next();

}
System.out.println("sumTotalVal: "+sumTotalVal);

String URLPair;
Double URLCountPerPair =0.0;
while (calcFreq.hasNext() && sum.hasNext()){
URLPair = calcFreq.next();
URLCountPerPair = sum.next();
System.out.println("URLPair: "+ URLPair+ " URLCountPerPair: "+ URLCountPerPair);
//Double freq = calcFrequency.getTableOfInitProb().get(URLPair);
calcFrequency.putHTTPCharToTable(URLPair, (URLCountPerPair/sumTotalVal));
//System.out.print(calcFrequency.getTableOfInitProb()+ " ");
}
System.out.println();
tableCalculatedFreq.addHTTPReq(calcFrequency.getTableOfInitProb()); 
*/
