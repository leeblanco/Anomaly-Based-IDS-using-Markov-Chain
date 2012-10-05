package com.markovdetection;

import com.markovdetection.*;

public class MainMarkovDetection {

	public static void main(String[] args) {
		fileManipulation open = new fileManipulation();
		fileManipulation constructTable = new TableOfInitialProbabilities();
		
		System.out.println("Start Markov... open file");
		open.openUsingRead();
		//open.showAddURLList();
		//open.showIntChars();
		//open.showAddURL();
		constructTable.showAddURL();
		
		System.out.println("Construct Table...");
		TableOfInitialProbabilities createTable = new TableOfInitialProbabilities();
		createTable.concatURLChar();
		createTable.printConcatChars();
		createTable.printTable();

		
		
		
		System.out.println("\nTable Construction Done... Starting Learning Phase");
		
		//LearningPhase learn = new LearningPhase();
		//learn.frequencyCount();
		//System.out.println("Learning Phase done");
	}

}