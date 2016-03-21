package org.agilar.spread;

import java.util.HashMap;
import java.util.Map;

public class MonthsPerConsultant {

	private Map<Consultant, Integer> numberOfMonths;
	
	public MonthsPerConsultant(){
		this.numberOfMonths = new HashMap<Consultant, Integer>();
		this.numberOfMonths.put(Consultant.ADRIAN, 23);
		this.numberOfMonths.put(Consultant.ALAN, 26);
		this.numberOfMonths.put(Consultant.ALBERTO, 21);
		this.numberOfMonths.put(Consultant.ANDRES, 23);
		this.numberOfMonths.put(Consultant.ANGEL, 23);
		this.numberOfMonths.put(Consultant.ARIEL, 38);
		this.numberOfMonths.put(Consultant.DAVID, 24);
		this.numberOfMonths.put(Consultant.FERNANDO, 39);
		this.numberOfMonths.put(Consultant.JOKE, 61);
		this.numberOfMonths.put(Consultant.JOSERRA, 41);
		this.numberOfMonths.put(Consultant.PETER, 21);
		this.numberOfMonths.put(Consultant.SOLEDAD, 15);
		this.numberOfMonths.put(Consultant.TIAGO, 87);
		this.numberOfMonths.put(Consultant.WOUTER, 8);
		this.numberOfMonths.put(Consultant.XAVIER, 102);
	}

	public int getMonths(Consultant consultant) {
		return this.numberOfMonths.get(consultant);
	}
	
	
}
