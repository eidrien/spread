package org.agilar.spread;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VoteParserShould {

	JsonVoteParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new JsonVoteParser();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=VotingException.class)
	public void fail_if_missing_consultant_data() {
		parser.parse("{\"adrian\":3}");
	}

	@Test
	public void read_values_for_each_consultant_from_json() {
		
		parser.parse("{\"adrian\":1,\"alan\":2,\"alberto\":3,\"andres\":4,\"angel\":5,\"ariel\":6,\"david\":7,\"fernando\":8,\"joke\":9,\"joserra\":10,\"peter\":11,\"soledad\":12,\"tiago\":13,\"wouter\":14,\"xavier\":15}");

		Vote testVote = parser.getData();
		
		assertEquals(1, testVote.getValue(Consultant.ADRIAN), 0.01);
		assertEquals(2, testVote.getValue(Consultant.ALAN), 0.01);
		assertEquals(3, testVote.getValue(Consultant.ALBERTO), 0.01);
		assertEquals(4, testVote.getValue(Consultant.ANDRES), 0.01);
		assertEquals(5, testVote.getValue(Consultant.ANGEL), 0.01);
		assertEquals(6, testVote.getValue(Consultant.ARIEL), 0.01);
		assertEquals(7, testVote.getValue(Consultant.DAVID), 0.01);
		assertEquals(8, testVote.getValue(Consultant.FERNANDO), 0.01);
		assertEquals(9, testVote.getValue(Consultant.JOKE), 0.01);
		assertEquals(10, testVote.getValue(Consultant.JOSERRA), 0.01);
		assertEquals(11, testVote.getValue(Consultant.PETER), 0.01);
		assertEquals(12, testVote.getValue(Consultant.SOLEDAD), 0.01);
		assertEquals(13, testVote.getValue(Consultant.TIAGO), 0.01);
		assertEquals(14, testVote.getValue(Consultant.WOUTER), 0.01);
		assertEquals(15, testVote.getValue(Consultant.XAVIER), 0.01);
	}
	
	@Test(expected=VotingException.class)
	public void fail_if_data_is_not_numeric(){
		parser.parse("{\"adrian\":\"One\",\"alan\":2,\"alberto\":3,\"andres\":4,\"angel\":5,\"ariel\":6,\"david\":7,\"fernando\":8,\"joke\":9,\"joserra\":10,\"peter\":11,\"soledad\":12,\"tiago\":13,\"wouter\":14,\"xavier\":15}");
	}
	
}
