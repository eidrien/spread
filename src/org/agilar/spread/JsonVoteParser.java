package org.agilar.spread;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonVoteParser {

	Vote lastReadValue;
	
	public void parse(String json) {
		Vote parsedJsonSpread = new Vote();
		
		JSONObject obj = new JSONObject(json);
		for(Consultant consultant : Consultant.values()){
			try{
				int value = obj.getInt(consultant.name().toLowerCase());
				parsedJsonSpread.setValue(consultant, value);
			}catch(JSONException e){
				throw new VotingException("Consultant value for " + consultant.name().toLowerCase() + " missing", e);
			}
		}
		
		lastReadValue = parsedJsonSpread;
	}

	public Vote getData() {
		return lastReadValue;
	}

}
