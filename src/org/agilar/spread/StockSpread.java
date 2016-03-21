package org.agilar.spread;

import java.util.HashMap;
import java.util.Map;

public class StockSpread {
	
	private int monthsAsConsultant;
	private Map<Consultant, Double> values;
	private Map<Consultant, Double> percentagesGiven;
	private Map<Consultant, Integer> pointsGiven;

	private double sumPercentages;
	private int sumPoints;

	public StockSpread(){
		this.values = new HashMap<Consultant, Double>();
		this.percentagesGiven = new HashMap<Consultant, Double>();
		this.pointsGiven = new HashMap<Consultant, Integer>();
	}
	
	
	
	public int getMonthsAsConsultant() {
		return monthsAsConsultant;
	}



	public void setMonthsAsConsultant(int monthsAsConsultant) {
		this.monthsAsConsultant = monthsAsConsultant;
	}



	public double getValue(Consultant consultant) {
		return this.values.get(consultant);
	}

	public void setValue(Consultant consultant, double value) {
		this.values.put(consultant, value);
	}

	public double getSumOfValues() {
		double sum = 0;
		for(Consultant consultant : Consultant.values()){
			sum += getValue(consultant); 
		}
		return sum;
	}


	public void calculateValues(){
		
		if(isValid()){
			if(getSumOfValues() == 100){
				this.percentagesGiven = new HashMap<Consultant, Double>(this.values);
				calculatePointsFromPercentages();
			}else{
				this.pointsGiven = new HashMap<Consultant, Integer>();
				for(Consultant consultant : Consultant.values()){
					this.pointsGiven.put(consultant, (int)getValue(consultant));
				}
				calculatePercentagesFromPoints();
			}
		}else{
			throw new ParseException("Values don't add up to 100 or to 100*"+this.monthsAsConsultant+" it adds up to "+getSumOfValues());
		}
	}

	private void calculatePercentagesFromPoints() {
		for(Consultant consultant : Consultant.values()){
			this.percentagesGiven.put(consultant, getValue(consultant)/this.monthsAsConsultant); 
		}
	}



	private void calculatePointsFromPercentages() {
		for(Consultant consultant : Consultant.values()){
			this.pointsGiven.put(consultant, (int)(getValue(consultant)*this.monthsAsConsultant)); 
		}
	}



	public boolean isValid() {
		if(this.monthsAsConsultant <= 1){
			throw new ParseException("Number of months must be greater than 1");
		}
		double sum = getSumOfValues();
		if(sum == 100){
			return true;
		}
		return sum == 100 * this.monthsAsConsultant;
	}



	public double getPercentage(Consultant consultant) {
		return this.percentagesGiven.get(consultant);
	}



	public int getPoints(Consultant consultant) {
		return this.pointsGiven.get(consultant);
	}

}
