package org.agilar.spread;

import java.util.HashMap;
import java.util.Map;

public class Deviation {
	
	
	public Deviation(){
		this.values = new HashMap<Consultant, Double>();
	}
	
	private Map<Consultant, Double> values;

	public double getValue(Consultant consultant) {
		
		Double value = this.values.get(consultant);
		return (value==null)?0:value;
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


	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public String toString(){
		String intro = "Deviations from the average vote = "; 
		
		String voteBody = voteBodyToString();
		
		return intro + voteBody;
	}

	private String voteBodyToString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(Consultant voted : Consultant.values()){
			double deviation = this.getValue(voted);
			sb.append(voted.name()).append("=").append(deviation).append(",");
		}
		sb.append("}");
		return sb.toString();
	}

}
