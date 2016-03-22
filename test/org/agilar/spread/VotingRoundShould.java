package org.agilar.spread;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VotingRoundShould {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void calculate_average_vote_should_be_same_as_votes_when_all_votes_are_the_same() {
		VotingRound round = new VotingRound();
		
		addVote1(round, Consultant.ADRIAN, 2);
		addVote1(round, Consultant.ALAN, 2);
		addVote1(round, Consultant.ALBERTO, 2);
		addVote1(round, Consultant.ANDRES, 2);
		addVote1(round, Consultant.ANGEL, 2);
		addVote1(round, Consultant.ARIEL, 2);
		addVote1(round, Consultant.DAVID, 2);
		addVote1(round, Consultant.FERNANDO, 2);
		addVote1(round, Consultant.JOKE, 2);
		addVote1(round, Consultant.JOSERRA, 2);
		addVote1(round, Consultant.PETER, 2);
		addVote1(round, Consultant.SOLEDAD, 2);
		addVote1(round, Consultant.TIAGO, 2);
		addVote1(round, Consultant.WOUTER, 2);
		addVote1(round, Consultant.XAVIER, 2);
		
		round.calculateAverage();
		
		Vote average = round.getAverageVote();
		assertEquals(30, average.getMonthsAsConsultant());
		assertEquals(10, average.getPercentage(Consultant.ADRIAN), 0.01);
		assertEquals(1, average.getPercentage(Consultant.JOSERRA), 0.01);
		assertEquals(5, average.getPercentage(Consultant.XAVIER), 0.01);
	}

	@Test
	public void calculate_average_should_be_same_if_all_votes_are_the_same_with_different_months() throws Exception {
		VotingRound round = new VotingRound();

		addVote1(round, Consultant.ADRIAN, 2);
		addVote1(round, Consultant.ALAN, 2);
		addVote1(round, Consultant.ALBERTO, 3);
		addVote1(round, Consultant.ANDRES, 4);
		addVote1(round, Consultant.ANGEL, 5);
		addVote1(round, Consultant.ARIEL, 6);
		addVote1(round, Consultant.DAVID, 7);
		addVote1(round, Consultant.FERNANDO, 8);
		addVote1(round, Consultant.JOKE, 9);
		addVote1(round, Consultant.JOSERRA, 10);
		addVote1(round, Consultant.PETER, 11);
		addVote1(round, Consultant.SOLEDAD, 12);
		addVote1(round, Consultant.TIAGO, 13);
		addVote1(round, Consultant.WOUTER, 14);
		addVote1(round, Consultant.XAVIER, 15);

		
		round.calculateAverage();
		
		Vote average = round.getAverageVote();
		assertEquals(121, average.getMonthsAsConsultant());
		assertEquals(10, average.getPercentage(Consultant.ADRIAN), 0.01);
		assertEquals(1, average.getPercentage(Consultant.JOSERRA), 0.01);
		assertEquals(5, average.getPercentage(Consultant.XAVIER), 0.01);
	}

	@Test
	public void calculate_average_should_be_half_way_betwen_two_options_with_same_votes() throws Exception {
		VotingRound round = new VotingRound();

		addVote1(round, Consultant.ADRIAN, 2);
		addVote1(round, Consultant.ALAN, 2);
		addVote1(round, Consultant.ALBERTO, 2);
		addVote1(round, Consultant.ANDRES, 2);
		addVote1(round, Consultant.ANGEL, 2);
		addVote1(round, Consultant.ARIEL, 2);
		addVote1(round, Consultant.DAVID, 2);
		addVote1(round, Consultant.FERNANDO, 2);
		
		addVote2(round, Consultant.JOKE, 2);
		addVote2(round, Consultant.JOSERRA, 2);
		addVote2(round, Consultant.PETER, 2);
		addVote2(round, Consultant.SOLEDAD, 2);
		addVote2(round, Consultant.TIAGO, 2);
		addVote2(round, Consultant.WOUTER, 2);
		addVote2(round, Consultant.XAVIER, 4);

		
		round.calculateAverage();
		
		Vote average = round.getAverageVote();
		assertEquals(32, average.getMonthsAsConsultant());
		assertEquals(8, average.getPercentage(Consultant.ADRIAN), 0.01);
		assertEquals(3, average.getPercentage(Consultant.JOSERRA), 0.01);
		assertEquals(13, average.getPercentage(Consultant.XAVIER), 0.01);
	}
	
	@Test
	public void calculate_average_without_votes_from_all_consultants() throws Exception {
		VotingRound round = new VotingRound();

		addVote1(round, Consultant.ADRIAN, 2);
		addVote1(round, Consultant.ALAN, 2);
		addVote1(round, Consultant.ALBERTO, 2);
		addVote1(round, Consultant.ANDRES, 2);
		addVote1(round, Consultant.ANGEL, 2);
		addVote1(round, Consultant.ARIEL, 2);
		addVote1(round, Consultant.DAVID, 2);
		addVote1(round, Consultant.FERNANDO, 2);
		
		
		round.calculateAverage();
		
		Vote average = round.getAverageVote();
		assertEquals(16, average.getMonthsAsConsultant());
		assertEquals(10, average.getPercentage(Consultant.ADRIAN), 0.01);
		assertEquals(1, average.getPercentage(Consultant.JOSERRA), 0.01);
		assertEquals(5, average.getPercentage(Consultant.XAVIER), 0.01);
	}

	private void addVote1(VotingRound round, Consultant consultant, int months) {
		Vote stockSpread;
		stockSpread = VoteShould.createValidStockSpread1(months);
		stockSpread.setMonthsAsConsultant(months);
		round.addVote(consultant, stockSpread);
	}

	private void addVote2(VotingRound round, Consultant consultant, int months) {
		Vote stockSpread;
		stockSpread = VoteShould.createValidStockSpread2(months);
		stockSpread.setMonthsAsConsultant(months);
		round.addVote(consultant, stockSpread);
	}
}
