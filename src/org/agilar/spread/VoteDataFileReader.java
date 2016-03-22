package org.agilar.spread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VoteDataFileReader {

	private File directory;
	private MonthsWorkedPerConsultant allowance;
	
	private JsonVoteParser parser;
	private VotingRound round;
	
	public VoteDataFileReader(String directory, JsonVoteParser parser) {
		this.directory = new File(directory);
		this.allowance = new MonthsWorkedPerConsultant(); 
		this.parser = parser;
	}

	public void scanData() throws FileNotFoundException {
		VotingRound round = new VotingRound();
		for(Consultant consultant : Consultant.values()){
			try{
				File spreadFile = new File(directory, "stockSpread-"+consultant.name().toLowerCase()+".json");
				String content = new Scanner(spreadFile).useDelimiter("\\Z").next();
				parser.parse(content);
				Vote vote = parser.getData();
				vote.setVoter(consultant);
				vote.setMonthsAsConsultant(allowance.getMonths(consultant));
				vote.calculateValues();
				round.addVote(consultant, vote);
				if(!vote.isValid()){
					System.err.println(vote.getErrorMessage());
				}
				System.out.println("Vote added by " + consultant.name());
			}catch(VotingException e){
				System.err.println(e.getMessage());
			}catch(FileNotFoundException e){
				System.err.println(consultant.name() + " didn't vote this round");
			}
		}	
		this.round = round;
	}

	public VotingRound getData() {
		return this.round;
	}

}
