package org.agilar.spread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking {

	private Deviation deviationPerConsultant;
	List<Consultant> consultantByDeviation;
	
	public Ranking(){
		consultantByDeviation = new ArrayList<Consultant>();
		consultantByDeviation.addAll(Arrays.asList(Consultant.values()));
	}
	
	public void setDeviation(Deviation deviation) {
		this.deviationPerConsultant = deviation;
		updateRanking();
	}

	public void updateRanking(){
		Collections.sort(consultantByDeviation, new Comparator<Consultant>(){
		    public int compare(Consultant x, Consultant y) {
		        return Double.compare(deviationPerConsultant.getValue(x),deviationPerConsultant.getValue(y));
		    }
		});
	}
	
	public String toString(){
	
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(Consultant voter : consultantByDeviation){
			double deviation = deviationPerConsultant.getValue(voter);
			sb.append(voter.name()).append("=").append(deviation).append(",");
		}
		sb.append("}");
		return sb.toString();
	}


}