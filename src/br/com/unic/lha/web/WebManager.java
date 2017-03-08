package br.com.unic.lha.web;

import java.io.IOException;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import br.com.unic.lha.domain.User;
import br.com.unic.lha.web.listeners.LoginListener;
import br.com.unic.lha.web.parsers.LoginJsonDataHolder;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * The Class WebManager.
 * 
 * @author hildon.lima
 * @version 1.0a
 */
public class WebManager {

	/** The Constant TAG. */
	private static final String TAG = "2beFit Web Manager -"
			+ WebManager.class.getSimpleName();

	/** The instance. */
	private static WebManager instance = null;

	/** The client. */
	private AsyncHttpClient client;

	/**
	 * Inits the class.
	 * 
	 * @param ctx
	 *            the ctx
	 */
	public static void initClass(final Context ctx) {
		instance = new WebManager();
		instance.init(ctx);
	}

	/**
	 * Inits the.
	 * 
	 * @param ctx
	 *            the ctx
	 */
	private void init(final Context ctx) {
		client = new AsyncHttpClient();
//		client.addHeader("Content-Type", "multipart/form-data");
	}

	/**
	 * Gets the single instance of WebManager.
	 * 
	 * @return single instance of WebManager
	 * @throws Exception
	 *             the exception
	 */
	public static WebManager getInstance() throws Exception {
		if (instance == null) {
			throw new Exception(
					"This class was not initialized. Call initClass() method, first!");
		}

		return instance;
	}

	/**
	 * Gets the request.
	 * 
	 * @param url
	 *            the url
	 * @param params
	 *            the params
	 * @param successListener
	 *            the success listener
	 * @return the request
	 */
	private void getRequest(final String url, final RequestParams params,
			final OnSuccessListener successListener) {
		client.get(url, params, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(final int statusCode, final Header[] headers,
					final JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				Log.e(TAG, "JSONObject: " + String.valueOf(response));
				successListener.onSuccess(String.valueOf(response));
			}

			@Override
			public void onSuccess(final int statusCode, final Header[] headers,
					final String responseString) {
				super.onSuccess(statusCode, headers, responseString);
				Log.e(TAG, "String: " + responseString);
				successListener.onSuccess(responseString);
			}

			@Override
			public void onFailure(final int statusCode, final Header[] headers,
					final String responseString, final Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				successListener.onFail(responseString);
			}

			/** {@inheritDoc} **/
			@Override
			public void onFailure(final int statusCode, final Header[] headers,
					final Throwable throwable, final JSONArray errorResponse) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, throwable, errorResponse);
				successListener.onFail(throwable.getMessage());
			}

			/** {@inheritDoc} **/
			@Override
			public void onFailure(final int statusCode, final Header[] headers,
					final Throwable throwable, final JSONObject errorResponse) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, throwable, errorResponse);
				successListener.onFail(throwable.getMessage());
			}

		});
	}

	/**
	 * The listener interface for receiving onSuccess events. The class that is
	 * interested in processing a onSuccess event implements this interface, and
	 * the object created with that class is registered with a component using
	 * the component's <code>addOnSuccessListener<code> method. When
	 * the onSuccess event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see OnSuccessEvent
	 */
	private interface OnSuccessListener {

		/**
		 * On success.
		 * 
		 * @param requestSuccessfull
		 *            the request successfull
		 */
		void onSuccess(String requestSuccessfull);

		/**
		 * On fail.
		 * 
		 * @param reason
		 *            the reason
		 */
		void onFail(String reason);
	}

	/**
	 * Post request.
	 * 
	 * @param url
	 *            the url
	 * @param params
	 *            the params
	 * @param successListener
	 *            the success listener
	 */
	private void postRequest(final String url, final RequestParams params,
			final OnSuccessListener successListener) {
		client.post(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(final int statusCode, final Header[] headers,
					final JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				Log.e(TAG, "JSONObject: " + String.valueOf(response));
				successListener.onSuccess(String.valueOf(response));
			}

			@Override
			public void onSuccess(final int statusCode, final Header[] headers,
					final String responseString) {
				super.onSuccess(statusCode, headers, responseString);
				Log.e(TAG, "String: " + responseString);
				successListener.onSuccess(responseString);
			}

			@Override
			public void onFailure(final int statusCode, final Header[] headers,
					final String responseString, final Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				successListener.onFail(responseString);
			}
		});
	}
	
	
	/**
	 * Login.
	 *
	 * @param email the email
	 * @param password the password
	 * @param view the view
	 */
	public void loginRequest(final String email, final String password, final LoginListener view) {
		final RequestParams params = new RequestParams();
		params.put("email", email);
		params.put("senha", password);
		final OnSuccessListener listener = new OnSuccessListener() {
			
			@Override
			public void onSuccess(final String requestSuccessfull) {
				//TODO
				Log.d("Login Response", requestSuccessfull);
				User user;
			
					try {
						user = LoginJsonDataHolder.getUserLoggedIn(requestSuccessfull);
						view.loginSuccess(user);
					} catch (final JSONException e) {
						onFail("ParserError");
						e.printStackTrace();
					} catch (final IOException e) {
						onFail("I/O Exception");
						e.printStackTrace();
					}
				
			}
			
			@Override
			public void onFail(final String reason) {
				Log.e("Login error", ""+reason);
				if (reason.contains("404")) {
					view.requestFailed("Usuário não encontrado, ou senha incorreta!");
				} else {
					view.requestFailed(reason);
				}
				
			}
		};
		
		postRequest(Constants.URL_LOGIN, params, listener);
	}

}
