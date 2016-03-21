package org.agilar.spread;

import java.util.HashMap;
import java.util.Map;

public class VotingRound {

	Map<Consultant, StockSpread> roundData;
	StockSpread average;
	
	public VotingRound(){
		this.roundData = new HashMap<Consultant, StockSpread>();
		this.average = new StockSpread();
	}
	
	public void putStockSpread(Consultant consultant, StockSpread data) {
		this.roundData.put(consultant, data);
	}

	public StockSpread getStockSpread(Consultant consultant) {
		return this.roundData.get(consultant);
	}

	public void calculateAverage() {
		int totalMonths = 0;
		for(Consultant voted: Consultant.values()){
			totalMonths += this.roundData.get(voted).getMonthsAsConsultant();
			int sumPoints = 0;
			for(Consultant voter: Consultant.values()){
				sumPoints+= this.roundData.get(voter).getPoints(voted);
			}
			this.average.setValue(voted, sumPoints);
			this.average.setMonthsAsConsultant(totalMonths);
		}
		this.average.calculateValues();
	}

	public StockSpread getAverageStockSpread() {
		return this.average;
	}

}
