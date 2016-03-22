package org.agilar.spread;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VotingRound {

	Map<Consultant, Vote> roundData;
	Vote average;
	
	public VotingRound(){
		this.roundData = new HashMap<Consultant, Vote>();
		this.average = new Vote();
	}
	
	public void addVote(Consultant consultant, Vote data) {
		this.roundData.put(consultant, data);
	}

	public Vote getVote(Consultant consultant) {
		return this.roundData.get(consultant);
	}

	public void calculateAverage() {
		for(Consultant voted: getAllConsultants()){
			int sumPoints = 0;
			for(Consultant voter: getVotingConsultants()){
				Vote vote = this.roundData.get(voter);
				sumPoints+= vote.getPoints(voted);
			}
			this.average.setValue(voted, sumPoints);
		}
		
		calculateTotalMonths();
		this.average.calculateValues();
	}

	private void calculateTotalMonths() {
		int totalMonths = 0;
		for(Consultant voter: getVotingConsultants()){
			totalMonths += this.roundData.get(voter).getMonthsAsConsultant();
		}
		this.average.setMonthsAsConsultant(totalMonths);
		
	}

	private Consultant[] getAllConsultants() {
		return Consultant.values();
	}

	private Set<Consultant> getVotingConsultants() {
		return this.roundData.keySet();
	}

	public Vote getAverageVote() {
		return this.average;
	}

}
