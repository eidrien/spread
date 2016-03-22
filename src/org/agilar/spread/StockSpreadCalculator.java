package org.agilar.spread;

import java.io.FileNotFoundException;

public class StockSpreadCalculator {
	
	public static void main(String[] args) throws FileNotFoundException {
		JsonVoteParser parser = new JsonVoteParser();
		VoteDataFileReader fileReader = new VoteDataFileReader("/Users/eidrien/Dropbox/WIP/Agilar Stock Distribution", parser);
		fileReader.scanData();
		
		VotingRound round = fileReader.getData();
		
		round.calculateAverage();
		Vote average = round.getAverageVote();
		
		System.out.println(average.toString());
	}
}
