package org.agilar.spread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpreadDataFileReader {

	private File directory;
	private MonthsPerConsultant allowance;
	
	private VoteParser parser;
	private VotingRound round;
	
	public SpreadDataFileReader(String directory, VoteParser parser) {
		this.directory = new File(directory);
		this.allowance = new MonthsPerConsultant(); 
		this.parser = parser;
	}

	public void scanData() throws FileNotFoundException {
		VotingRound round = new VotingRound();
		for(Consultant consultant : Consultant.values()){
			File spreadFile = new File(directory, "stockSpread-"+consultant.name().toLowerCase()+".json");
			String content = new Scanner(spreadFile).useDelimiter("\\Z").next();
			parser.parse(content);
			StockSpread vote = parser.getData();
			vote.setMonthsAsConsultant(allowance.getMonths(consultant));
			vote.calculateValues();
			round.putStockSpread(consultant, vote);
		}	
		this.round = round;
	}

	public VotingRound getData() {
		return this.round;
	}

}
