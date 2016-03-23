package org.agilar.spread;

public class VoteFactory {

	
	public VotingRound createRound(){
		return new VotingRound(this);
	}
	
	public Vote createVote(){
		return new Vote();
	}

	public Vote createAverageVote(){
		return new Vote();
	}

	public Deviation createDeviationVote(){
		return new Deviation();
	}

	public Ranking createRanking() {
		return new Ranking();
	}
}
