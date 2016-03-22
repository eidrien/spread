package org.agilar.spread;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VoteShould {

	Vote vote;

	@Before
	public void setUp() throws Exception {
		vote = new Vote();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void calculat_sum_using_all_consultants() {
		vote.setValue(Consultant.ADRIAN, 1);
		vote.setValue(Consultant.ALAN, 1);
		vote.setValue(Consultant.ALBERTO, 1);
		vote.setValue(Consultant.ANDRES, 1);
		vote.setValue(Consultant.ANGEL, 1);
		vote.setValue(Consultant.ARIEL, 1);
		vote.setValue(Consultant.DAVID, 1);
		vote.setValue(Consultant.FERNANDO, 1);
		vote.setValue(Consultant.JOKE, 1);
		vote.setValue(Consultant.JOSERRA, 1);
		vote.setValue(Consultant.PETER, 1);
		vote.setValue(Consultant.SOLEDAD, 1);
		vote.setValue(Consultant.TIAGO, 1);
		vote.setValue(Consultant.WOUTER, 1);
		vote.setValue(Consultant.XAVIER, 1);

		assertEquals(15, vote.getSumOfValues(), 0.1);
	}
	
	@Test
	public void validate_sum_is_correct_when_given_percentages() throws Exception {
		givenCorrectSpreadInPercentages();
	}

	@Test
	public void validate_sum_is_incorrect_when_given_percentages_dont_add_to_100() throws Exception {
		givenIncorrectSpreadInPercentages();
		
		vote.calculateValues();
		
		assertFalse(vote.isValid());
	}

	@Test
	public void validate_sum_is_correct_when_given_points_that_add_to_allowance() throws Exception {
		givenCorrectSpreadWithPoints();
	}

	@Test
	public void validate_sum_is_incorrect_when_given_points_that_dont_add_to_allowance() throws Exception {
		givenIncorrectSpreadInPoints();
		
		vote.calculateValues();
		
		assertFalse(vote.isValid());
	}

	@Test
	public void getPercentage_returns_value_when_values_where_in_percentages() throws Exception {
		givenCorrectSpreadInPercentages();
		
		assertEquals(10, vote.getPercentage(Consultant.ADRIAN), 0.01);
		assertEquals(1, vote.getPercentage(Consultant.JOSERRA), 0.01);
		assertEquals(5, vote.getPercentage(Consultant.XAVIER), 0.01);
	}

	@Test
	public void getPercentage_returns_calculated_percentage_when_values_where_in_points() throws Exception {
		givenCorrectSpreadWithPoints();

		assertEquals(10, vote.getPercentage(Consultant.ADRIAN), 0.01);
		assertEquals(1, vote.getPercentage(Consultant.JOSERRA), 0.01);
		assertEquals(5, vote.getPercentage(Consultant.XAVIER), 0.01);
	}

	@Test
	public void getPoints_returns_value_when_values_where_in_points() throws Exception {
		givenCorrectSpreadWithPoints();

		assertEquals(20, vote.getPoints(Consultant.ADRIAN));
		assertEquals(2, vote.getPoints(Consultant.JOSERRA));
		assertEquals(10, vote.getPoints(Consultant.XAVIER));
	}

	@Test
	public void getPoints_returns_calculated_points_when_values_where_in_percentages() throws Exception {
		givenCorrectSpreadInPercentages();
		
		assertEquals(20, vote.getPoints(Consultant.ADRIAN));
		assertEquals(2, vote.getPoints(Consultant.JOSERRA));
		assertEquals(10, vote.getPoints(Consultant.XAVIER));
	}
	
	@Test(expected = VotingException.class)
	public void calculateValues_with_correct_percentages_throws_exception_when_number_of_months_is_zero(){
		givenCorrectPercentageSpreadWithZeroNumberOfMonths();
		
		vote.calculateValues();
	}

	private void givenCorrectPercentageSpreadWithZeroNumberOfMonths() {
		vote = new Vote();

		vote.setValue(Consultant.ADRIAN, 10);
		vote.setValue(Consultant.ALAN, 10);
		vote.setValue(Consultant.ALBERTO, 10);
		vote.setValue(Consultant.ANDRES, 10);
		vote.setValue(Consultant.ANGEL, 10);
		vote.setValue(Consultant.ARIEL, 10);
		vote.setValue(Consultant.DAVID, 10);
		vote.setValue(Consultant.FERNANDO, 10);
		vote.setValue(Consultant.JOKE, 10);
		vote.setValue(Consultant.JOSERRA, 1);
		vote.setValue(Consultant.PETER, 1);
		vote.setValue(Consultant.SOLEDAD, 1);
		vote.setValue(Consultant.TIAGO, 1);
		vote.setValue(Consultant.WOUTER, 1);
		vote.setValue(Consultant.XAVIER, 5);
		
		vote.calculateValues();
	}

	private void givenCorrectSpreadInPercentages() {
		vote = new Vote();
		vote.setMonthsAsConsultant(2);

		vote.setValue(Consultant.ADRIAN, 10);
		vote.setValue(Consultant.ALAN, 10);
		vote.setValue(Consultant.ALBERTO, 10);
		vote.setValue(Consultant.ANDRES, 10);
		vote.setValue(Consultant.ANGEL, 10);
		vote.setValue(Consultant.ARIEL, 10);
		vote.setValue(Consultant.DAVID, 10);
		vote.setValue(Consultant.FERNANDO, 10);
		vote.setValue(Consultant.JOKE, 10);
		vote.setValue(Consultant.JOSERRA, 1);
		vote.setValue(Consultant.PETER, 1);
		vote.setValue(Consultant.SOLEDAD, 1);
		vote.setValue(Consultant.TIAGO, 1);
		vote.setValue(Consultant.WOUTER, 1);
		vote.setValue(Consultant.XAVIER, 5);
		
		vote.calculateValues();
	}

	private void givenCorrectSpreadWithPoints() {
		vote = new Vote();
		vote.setMonthsAsConsultant(2);
		
		vote.setValue(Consultant.ADRIAN, 20);
		vote.setValue(Consultant.ALAN, 20);
		vote.setValue(Consultant.ALBERTO, 20);
		vote.setValue(Consultant.ANDRES, 20);
		vote.setValue(Consultant.ANGEL, 20);
		vote.setValue(Consultant.ARIEL, 20);
		vote.setValue(Consultant.DAVID, 20);
		vote.setValue(Consultant.FERNANDO, 20);
		vote.setValue(Consultant.JOKE, 20);
		vote.setValue(Consultant.JOSERRA, 2);
		vote.setValue(Consultant.PETER, 2);
		vote.setValue(Consultant.SOLEDAD, 2);
		vote.setValue(Consultant.TIAGO, 2);
		vote.setValue(Consultant.WOUTER, 2);
		vote.setValue(Consultant.XAVIER, 10);
		
		vote.calculateValues();
	}

	private void givenIncorrectSpreadInPercentages() {
		vote = new Vote();
		vote.setMonthsAsConsultant(2);

		vote.setValue(Consultant.ADRIAN, 10);
		vote.setValue(Consultant.ALAN, 10);
		vote.setValue(Consultant.ALBERTO, 10);
		vote.setValue(Consultant.ANDRES, 10);
		vote.setValue(Consultant.ANGEL, 10);
		vote.setValue(Consultant.ARIEL, 10);
		vote.setValue(Consultant.DAVID, 10);
		vote.setValue(Consultant.FERNANDO, 10);
		vote.setValue(Consultant.JOKE, 10);
		vote.setValue(Consultant.JOSERRA, 1);
		vote.setValue(Consultant.PETER, 1);
		vote.setValue(Consultant.SOLEDAD, 1);
		vote.setValue(Consultant.TIAGO, 1);
		vote.setValue(Consultant.WOUTER, 1);
		vote.setValue(Consultant.XAVIER, 1);
	}
	
	private void givenIncorrectSpreadInPoints() {
		vote = new Vote();
		vote.setMonthsAsConsultant(2);
		
		vote.setValue(Consultant.ADRIAN, 20);
		vote.setValue(Consultant.ALAN, 20);
		vote.setValue(Consultant.ALBERTO, 20);
		vote.setValue(Consultant.ANDRES, 20);
		vote.setValue(Consultant.ANGEL, 20);
		vote.setValue(Consultant.ARIEL, 20);
		vote.setValue(Consultant.DAVID, 20);
		vote.setValue(Consultant.FERNANDO, 20);
		vote.setValue(Consultant.JOKE, 20);
		vote.setValue(Consultant.JOSERRA, 2);
		vote.setValue(Consultant.PETER, 2);
		vote.setValue(Consultant.SOLEDAD, 2);
		vote.setValue(Consultant.TIAGO, 2);
		vote.setValue(Consultant.WOUTER, 2);
		vote.setValue(Consultant.XAVIER, 1);
	}
	
	public static Vote createValidStockSpread1(int monthsAsConsultant){
		Vote spread = new Vote();
		spread.setMonthsAsConsultant(monthsAsConsultant);

		spread.setValue(Consultant.ADRIAN, 10);
		spread.setValue(Consultant.ALAN, 10);
		spread.setValue(Consultant.ALBERTO, 10);
		spread.setValue(Consultant.ANDRES, 10);
		spread.setValue(Consultant.ANGEL, 10);
		spread.setValue(Consultant.ARIEL, 10);
		spread.setValue(Consultant.DAVID, 10);
		spread.setValue(Consultant.FERNANDO, 10);
		spread.setValue(Consultant.JOKE, 10);
		spread.setValue(Consultant.JOSERRA, 1);
		spread.setValue(Consultant.PETER, 1);
		spread.setValue(Consultant.SOLEDAD, 1);
		spread.setValue(Consultant.TIAGO, 1);
		spread.setValue(Consultant.WOUTER, 1);
		spread.setValue(Consultant.XAVIER, 5);
		
		spread.calculateValues();
		
		return spread;
	}
	
	public static Vote createValidStockSpread2(int monthsAsConsultant){
		Vote spread = new Vote();
		spread.setMonthsAsConsultant(monthsAsConsultant);

		spread.setValue(Consultant.ADRIAN, 6);
		spread.setValue(Consultant.ALAN, 6);
		spread.setValue(Consultant.ALBERTO, 6);
		spread.setValue(Consultant.ANDRES, 6);
		spread.setValue(Consultant.ANGEL, 6);
		spread.setValue(Consultant.ARIEL, 6);
		spread.setValue(Consultant.DAVID, 6);
		spread.setValue(Consultant.FERNANDO, 6);
		spread.setValue(Consultant.JOKE, 6);
		spread.setValue(Consultant.JOSERRA, 5);
		spread.setValue(Consultant.PETER, 5);
		spread.setValue(Consultant.SOLEDAD, 5);
		spread.setValue(Consultant.TIAGO, 5);
		spread.setValue(Consultant.WOUTER, 5);
		spread.setValue(Consultant.XAVIER, 21);
		
		spread.calculateValues();
		
		return spread;
	}
	
}
