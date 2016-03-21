package org.agilar.spread;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpreadDataFileReaderShould {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void read_data_from_months_per_consultant() throws FileNotFoundException {
		SpreadDataFileReader reader = new SpreadDataFileReader("files", new VoteParser());
		
		reader.scanData();
		VotingRound spreadData = reader.getData();
		
		StockSpread adriansVote = spreadData.getStockSpread(Consultant.ADRIAN);
		assertEquals(23, adriansVote.getMonthsAsConsultant());
	}
	
	@Test
	public void read_data_from_all_consultants() throws Exception {
		SpreadDataFileReader reader = new SpreadDataFileReader("files", new VoteParser());
		
		reader.scanData();
		VotingRound spreadData = reader.getData();
		
		StockSpread adriansVote = spreadData.getStockSpread(Consultant.ADRIAN);
		assertEquals(3, adriansVote.getPercentage(Consultant.ADRIAN), 0.1);

		StockSpread alansVote = spreadData.getStockSpread(Consultant.ALAN);
		assertEquals(10, alansVote.getPercentage(Consultant.ALAN), 0.1);

		StockSpread xaviersVote = spreadData.getStockSpread(Consultant.XAVIER);
		assertEquals(40, xaviersVote.getPercentage(Consultant.XAVIER), 0.1);
	}

}
