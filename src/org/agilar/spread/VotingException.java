package org.agilar.spread;

import org.json.JSONException;

public class VotingException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VotingException(String string, JSONException e) {
		super(string, e);
	}

	public VotingException(String string) {
		super(string);
	}

}
