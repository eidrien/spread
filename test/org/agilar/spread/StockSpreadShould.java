package org.agilar.spread;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StockSpreadShould {

	StockSpread spread;

	@Before
	public void setUp() throws Exception {
		spread = new StockSpread();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void calculat_sum_using_all_consultants() {
		spread.setValue(Consultant.ADRIAN, 1);
		spread.setValue(Consultant.ALAN, 1);
		spread.setValue(Consultant.ALBERTO, 1);
		spread.setValue(Consultant.ANDRES, 1);
		spread.setValue(Consultant.ANGEL, 1);
		spread.setValue(Consultant.ARIEL, 1);
		spread.setValue(Consultant.DAVID, 1);
		spread.setValue(Consultant.FERNANDO, 1);
		spread.setValue(Consultant.JOKE, 1);
		spread.setValue(Consultant.JOSERRA, 1);
		spread.setValue(Consultant.PETER, 1);
		spread.setValue(Consultant.SOLEDAD, 1);
		spread.setValue(Consultant.TIAGO, 1);
		spread.setValue(Consultant.WOUTER, 1);
		spread.setValue(Consultant.XAVIER, 1);

		assertEquals(15, spread.getSumOfValues(), 0.1);
	}
	
	@Test
	public void validate_sum_is_correct_when_given_percentages() throws Exception {
		givenCorrectSpreadInPercentages();
	}

	@Test(expected = ParseException.class)
	public void validate_sum_is_incorrect_when_given_percentages_dont_add_to_100() throws Exception {
		givenIncorrectSpreadInPercentages();
		
		spread.calculateValues();
	}

	@Test
	public void validate_sum_is_correct_when_given_points_that_add_to_allowance() throws Exception {
		givenCorrectSpreadWithPoints();
	}

	@Test(expected = ParseException.class)
	public void validate_sum_is_incorrect_when_given_points_that_dont_add_to_allowance() throws Exception {
		givenIncorrectSpreadInPoints();
		
		spread.calculateValues();
	}

	@Test
	public void getPercentage_returns_value_when_values_where_in_percentages() throws Exception {
		givenCorrectSpreadInPercentages();
		
		assertEquals(10, spread.getPercentage(Consultant.ADRIAN), 0.01);
		assertEquals(1, spread.getPercentage(Consultant.JOSERRA), 0.01);
		assertEquals(5, spread.getPercentage(Consultant.XAVIER), 0.01);
	}

	@Test
	public void getPercentage_returns_calculated_percentage_when_values_where_in_points() throws Exception {
		givenCorrectSpreadWithPoints();

		assertEquals(10, spread.getPercentage(Consultant.ADRIAN), 0.01);
		assertEquals(1, spread.getPercentage(Consultant.JOSERRA), 0.01);
		assertEquals(5, spread.getPercentage(Consultant.XAVIER), 0.01);
	}

	@Test
	public void getPoints_returns_value_when_values_where_in_points() throws Exception {
		givenCorrectSpreadWithPoints();

		assertEquals(20, spread.getPoints(Consultant.ADRIAN));
		assertEquals(2, spread.getPoints(Consultant.JOSERRA));
		assertEquals(10, spread.getPoints(Consultant.XAVIER));
	}

	@Test
	public void getPoints_returns_calculated_points_when_values_where_in_percentages() throws Exception {
		givenCorrectSpreadInPercentages();
		
		assertEquals(20, spread.getPoints(Consultant.ADRIAN));
		assertEquals(2, spread.getPoints(Consultant.JOSERRA));
		assertEquals(10, spread.getPoints(Consultant.XAVIER));
	}
	
	@Test(expected = ParseException.class)
	public void calculateValues_with_correct_percentages_throws_exception_when_number_of_months_is_zero(){
		givenCorrectPercentageSpreadWithZeroNumberOfMonths();
		
		spread.calculateValues();
	}

	private void givenCorrectPercentageSpreadWithZeroNumberOfMonths() {
		spread = new StockSpread();

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
	}

	private void givenCorrectSpreadInPercentages() {
		spread = new StockSpread();
		spread.setMonthsAsConsultant(2);

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
	}

	private void givenCorrectSpreadWithPoints() {
		spread = new StockSpread();
		spread.setMonthsAsConsultant(2);
		
		spread.setValue(Consultant.ADRIAN, 20);
		spread.setValue(Consultant.ALAN, 20);
		spread.setValue(Consultant.ALBERTO, 20);
		spread.setValue(Consultant.ANDRES, 20);
		spread.setValue(Consultant.ANGEL, 20);
		spread.setValue(Consultant.ARIEL, 20);
		spread.setValue(Consultant.DAVID, 20);
		spread.setValue(Consultant.FERNANDO, 20);
		spread.setValue(Consultant.JOKE, 20);
		spread.setValue(Consultant.JOSERRA, 2);
		spread.setValue(Consultant.PETER, 2);
		spread.setValue(Consultant.SOLEDAD, 2);
		spread.setValue(Consultant.TIAGO, 2);
		spread.setValue(Consultant.WOUTER, 2);
		spread.setValue(Consultant.XAVIER, 10);
		
		spread.calculateValues();
	}

	private void givenIncorrectSpreadInPercentages() {
		spread = new StockSpread();
		spread.setMonthsAsConsultant(2);

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
		spread.setValue(Consultant.XAVIER, 1);
	}
	
	private void givenIncorrectSpreadInPoints() {
		spread = new StockSpread();
		spread.setMonthsAsConsultant(2);
		
		spread.setValue(Consultant.ADRIAN, 20);
		spread.setValue(Consultant.ALAN, 20);
		spread.setValue(Consultant.ALBERTO, 20);
		spread.setValue(Consultant.ANDRES, 20);
		spread.setValue(Consultant.ANGEL, 20);
		spread.setValue(Consultant.ARIEL, 20);
		spread.setValue(Consultant.DAVID, 20);
		spread.setValue(Consultant.FERNANDO, 20);
		spread.setValue(Consultant.JOKE, 20);
		spread.setValue(Consultant.JOSERRA, 2);
		spread.setValue(Consultant.PETER, 2);
		spread.setValue(Consultant.SOLEDAD, 2);
		spread.setValue(Consultant.TIAGO, 2);
		spread.setValue(Consultant.WOUTER, 2);
		spread.setValue(Consultant.XAVIER, 1);
	}
	
	public static StockSpread createValidStockSpread1(int monthsAsConsultant){
		StockSpread spread = new StockSpread();
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
	
	public static StockSpread createValidStockSpread2(int monthsAsConsultant){
		StockSpread spread = new StockSpread();
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
