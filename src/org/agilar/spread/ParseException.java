package org.agilar.spread;

import org.json.JSONException;

public class ParseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParseException(String string, JSONException e) {
		super(string, e);
	}

	public ParseException(String string) {
		super(string);
	}

}
