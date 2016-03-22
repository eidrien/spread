package org.agilar.spread;

import java.util.HashMap;
import java.util.Map;

public class Vote {
	
	
	public Vote(){
		this.values = new HashMap<Consultant, Double>();
		this.percentagesGiven = new HashMap<Consultant, Double>();
		this.pointsGiven = new HashMap<Consultant, Integer>();
	}
	
	
	private Consultant voter;

	public void setVoter(Consultant consultant) {
		this.voter = consultant;
	}

	private int monthsAsConsultant;

	public int getMonthsAsConsultant() {
		return monthsAsConsultant;
	}

	public void setMonthsAsConsultant(int monthsAsConsultant) {
		this.monthsAsConsultant = monthsAsConsultant;
	}


	private Map<Consultant, Double> values;

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


	private Map<Consultant, Double> percentagesGiven;
	private Map<Consultant, Integer> pointsGiven;
	private String errorMessage;

	public void calculateValues(){
		
		checkValidity();
		if(getSumOfValues() <= 100){
			this.percentagesGiven = new HashMap<Consultant, Double>(this.values);
			calculatePointsFromPercentages();
		}else{
			this.pointsGiven = new HashMap<Consultant, Integer>();
			for(Consultant consultant : Consultant.values()){
				this.pointsGiven.put(consultant, (int)getValue(consultant));
			}
			calculatePercentagesFromPoints();
		}
	}

	public String getErrorMessage() {
		return errorMessage;
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


	private boolean valid;
	
	public boolean isValid() {
		return this.valid;
	}
		
	public void checkValidity(){
		if(this.monthsAsConsultant <= 1){
			throw new VotingException("Number of months must be greater than 1");
		}
		double sum = getSumOfValues();
		
		this.valid = sum == 100 || sum == 100 * this.monthsAsConsultant;
		if(!this.valid){
			this.errorMessage = getVoterName() + " values' don't add up to 100 or to 100*"+this.monthsAsConsultant+" it adds up to "+getSumOfValues();
		}
	}



	public double getPercentage(Consultant consultant) {
		return this.percentagesGiven.get(consultant);
	}



	public int getPoints(Consultant consultant) {
		return this.pointsGiven.get(consultant);
	}

	public String toString(){
		String voterName = getVoterName();
		
		String intro = voterName + "'s vote = "; 
		
		String voteBody = voteBodyToString();
		
		return intro + voteBody;
	}

	private String getVoterName() {
		if(this.voter == null){
			return "AVERAGE";
		}
		return this.voter.name();
	}

	private String voteBodyToString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(Consultant voted : Consultant.values()){
			double percentage = this.getPercentage(voted);
			sb.append(voted.name()).append("=").append(percentage).append(",");
		}
		sb.append("}");
		return sb.toString();
	}

}
