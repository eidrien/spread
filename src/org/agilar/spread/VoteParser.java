package org.agilar.spread;

import org.json.JSONException;
import org.json.JSONObject;

public class VoteParser {

	StockSpread lastReadValue;
	
	public void parse(String json) {
		StockSpread parsedJsonSpread = new StockSpread();
		
		JSONObject obj = new JSONObject(json);
		for(Consultant consultant : Consultant.values()){
			try{
				int value = obj.getInt(consultant.name().toLowerCase());
				parsedJsonSpread.setValue(consultant, value);
			}catch(JSONException e){
				throw new ParseException("Consultant value for " + consultant.name().toLowerCase() + " missing", e);
			}
		}
		
		lastReadValue = parsedJsonSpread;
	}

	public StockSpread getData() {
		return lastReadValue;
	}

}
