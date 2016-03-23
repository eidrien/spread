package org.agilar.spread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VotingRound {

	Map<Consultant, Vote> roundData;
	Vote average;
	Deviation deviation;
	Ranking ranking;
	
	
	public VotingRound(VoteFactory factory){
		this.roundData = new HashMap<Consultant, Vote>();
		this.average = factory.createAverageVote();
		this.deviation = factory.createDeviationVote();
		this.ranking = factory.createRanking();
	}
	
	public void addVote(Consultant consultant, Vote data) {
		this.roundData.put(consultant, data);
	}

	public Vote getVote(Consultant consultant) {
		return this.roundData.get(consultant);
	}

	public void calculateStatistics() {
		calculateAverage();
		calculateDeviationFromAverage();
		calculateRanking();
	}

	private void calculateAverage() {
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

	private void calculateDeviationFromAverage() {
		
		for(Consultant voter: getVotingConsultants()){
			Vote vote = this.roundData.get(voter);
			double averageDeviation = this.average.calculateDeviation(vote);
			this.deviation.setValue(voter, averageDeviation);
		}
	}
	
	private void calculateRanking(){
		this.ranking.setDeviation(deviation);
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

	public Deviation getDeviationsFromAverage() {
		return deviation;
	}

	public String toString() {
		StringBuffer text = new StringBuffer();
		
		Vote average = this.getAverageVote();
		text.append("===== Average Stock Distribution =====");
		text.append("\n");
		text.append(average.toString());
		text.append("\n");
		
		Deviation deviation = this.getDeviationsFromAverage();

		text.append("===== Deviation from Average per Member =====");
		text.append("\n");
		text.append(deviation.toString());
		text.append("\n");
		
		text.append("===== Ranking of Members per Deviation =====");
		text.append("\n");
		text.append(ranking.toString());
		text.append("\n");
		
		return text.toString();
	}

}
