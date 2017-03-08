package br.com.unic.lha.utils;

import android.content.Context;
import android.content.SharedPreferences;
import br.com.unic.lha.domain.User;

/**
 * The Class PreferencesUtils.
 * 
 * @author hildon.lima
 */
public class PreferencesUtils {
	
	/** The Constant NAME. */
	private static final String NAME = "user_name";
	
	/** The Constant LAST_NAME. */
	private static final String LAST_NAME = "user_lastname";
	
	/** The Constant GENDER. */
	private static final String GENDER = "user_gender";
	
	/** The Constant NICKNAME. */
	private static final String NICKNAME = "user_nickname";
	
	/** The Constant CITY. */
	private static final String CITY = "user_city";
	
	/** The Constant EMAIL. */
	private static final String EMAIL = "user_email";
	
	/** The Constant PASSWORD. */
	private static final String PASSWORD = "user_password";
	
	/** The Constant appPreferencesName. */
	private static final String APP_PREFS_FILE_NAME = "AppFit-prefs";
	
	/**
	 * Save user logged in.
	 *
	 * @param context the context
	 * @param user the user
	 */
	public static void saveUserLoggedIn(final Context context, final User user) {
		final SharedPreferences preferences = context.getSharedPreferences(APP_PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final SharedPreferences.Editor edit = preferences.edit();
		edit.putString(NAME, user.getName());
		edit.putString(LAST_NAME, user.getLastName());
		edit.putString(GENDER, user.getGender());
		edit.putString(NICKNAME, user.getNickName());
		edit.putString(PASSWORD, user.getPassword());
		edit.putString(EMAIL, user.getEmail());
		edit.putString(CITY, user.getCity());
		
		edit.commit();
	}
	
	/**
	 * Gets the user logged in.
	 *
	 * @param context the context
	 * @return the user logged in
	 */
	public static User getUserLoggedIn(final Context context) {
		final User user = new User();
		final SharedPreferences preferences = context.getSharedPreferences(APP_PREFS_FILE_NAME, Context.MODE_PRIVATE);
		user.setName(preferences.getString(NAME, ""));
		user.setLastName(preferences.getString(LAST_NAME, ""));
		user.setGender(preferences.getString(GENDER, ""));
		user.setNickName(preferences.getString(NICKNAME, ""));
		user.setEmail(preferences.getString(EMAIL, ""));
		user.setPassword(preferences.getString(PASSWORD, ""));
		user.setCity(preferences.getString(CITY, ""));
		
		return user;
	}
	
}
