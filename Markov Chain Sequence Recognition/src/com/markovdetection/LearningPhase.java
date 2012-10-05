package com.markovdetection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LearningPhase extends TableOfInitialProbabilities {

	private HTTPRequest<List<String>> learn = new HTTPRequest<List<String>>();
	
	public LearningPhase(){
	}
	
	/*
	 * This method will count the number of times a pair of characters appears in 
	 * tableOfConcatChar which is generated in the class TableOfInitialProbabilities
	 * */
	public void frequencyCount(){
		List<List<String>> copyTableOfConcatChar = new ArrayList<List<String>>();
		List<List<Integer>> listOfPairFreq = new ArrayList<List<Integer>>();
		
		//check if getTableOfConcatChar contains an entry
		if (getTableOfConcatChar().getListOfHttpReq().size()>0){
			//copyTableOfConcatChar.addAll(getTableOfConcatChar().getListOfHttpReq()); //copy contents of tableOfConcatChar to temp storage 
			for (List<String> elem: getTableOfConcatChar().getListOfHttpReq()){
				List<Integer> countNoOfPairs = new ArrayList<Integer>();
				for (String insideElem: elem){
					countNoOfPairs.add(Collections.frequency(elem, insideElem));
				}
				listOfPairFreq.add(countNoOfPairs);
			}
			
		}
		else{
			System.out.println("TableOfConcatChar is empty");
		}
		
		System.out.println("\nList Of Character Pair Frequency");
		for (List<Integer> elem: listOfPairFreq){
			System.out.println(elem);
		}
		
	}
	

}
