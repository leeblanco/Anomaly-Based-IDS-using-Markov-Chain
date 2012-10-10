package com.markovdetection;

public class MainMarkovDetection {

	public static void main(String[] args) {
		fileManipulation open = new fileManipulation();
		
		System.out.println("Start Markov... open file");
		open.openUsingRead(); //open file for learning
		//open.showAddURLList();
		//open.showAddURL();
		
		
		System.out.println("\nConstruct Table...");
		TableOfInitialProbabilities createTable = new TableOfInitialProbabilities();
		System.out.println("\nCopy table from map");
		createTable.copyAddURLTableFromMap();
		createTable.concatURLChar();
		//createTable.printTable();
		
		System.out.println("\n\nTable Construction Done... Starting Learning Phase");
		
		LearningPhase learn = new LearningPhase();
		System.out.println("Calculate Sum per row");
		learn.calcSumPerRow();
		System.out.println("Calculate Sum per frequency");
		learn.calculateFreq();
		//learn.copyTableCalcFreqToOneDMap();
		//learn.printMaps();
		//learn.printRowSummation();//Display Row Summation
		//learn.printFrequencies();//Display Vector of Initial Probabilities
		
		System.out.println("Learning Phase done");
		
		System.out.println("\nStarting Detection Phase");
		System.out.println("\nRead file with exploits");
		fileManipulation openExploit = new fileManipulation();
		openExploit.openFileForDetection(); // Open file for detection
		//openExploit.showDetectedURLs();
		//System.out.println("\nURL for Detection size: "+openExploit.getAddDetectedURL().getListOfHttpReq().size());
		
		System.out.println("\nCreate table for detecting anomalies");
		TableOfInitialProbabilities tableForDetection = new TableOfInitialProbabilities(openExploit.getAddDetectedURL());
		//tableForDetection.printListOfDetectedURLPairs();
		
		DetectionPhase detect = new DetectionPhase();
		//detect.printFreqTableLearning();
		System.out.println("\nStart Comparison of Learned URL pair and detected URL pair");
		detect.Comparison(); //Compares detected and Learned URL pairs
		System.out.println("\nComputing MAP");
		detect.computeMAP();// Compute MAP using estevez-tapiadors equation
		System.out.println("\nDetecting Changes");
		detect.detectChanges();//check if detect URL Pair is normal or plot values to see difference
		//detect.printListOfDetectedURLPairs();
		//detect.printCountDetectedURLPair();
		//detect.printobservedMAP();
		//detect.printMarkovDetection();
		System.out.println("\nSaving file...");
		openExploit.saveLOGMAPResult(detect.getStoreValChange());
		System.out.println("\n\nMarkov Detection Process complete");
		
		
	}

}
