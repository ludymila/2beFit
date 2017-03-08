package br.com.unic.lha.web.parsers;

import java.io.IOException;

import org.json.JSONException;

import br.com.unic.lha.domain.User;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class LoginJsonDataHolder.
 * 
 * @author hildon.lima
 */
public class LoginJsonDataHolder {
	
	/**
	 * Gets the user logged in.
	 *
	 * @param json the json
	 * @return the user logged in
	 * @throws JSONException the JSON exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static User getUserLoggedIn(final String json) throws JSONException, IOException {

	    final User user = new ObjectMapper()
	        .readValue(json, User.class);

	    return user;
	}

}
