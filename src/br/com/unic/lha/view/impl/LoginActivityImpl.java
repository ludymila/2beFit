package br.com.unic.lha.view.impl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.unic.lha.R;
import br.com.unic.lha.domain.User;
import br.com.unic.lha.utils.PreferencesUtils;
import br.com.unic.lha.view.activity.LoginActivity;
import br.com.unic.lha.view.activity.impl.MainActivityImpl;
import br.com.unic.lha.web.WebManager;

public class LoginActivityImpl extends Activity implements LoginActivity {
	
	/** The login edit. */
	private EditText loginEdit;
	
	/** The password edit. */
	private EditText passwordEdit;
	
	/** The login button. */
	private Button loginButton;
	
	private ProgressDialog pDialog;
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		setupView();
	}
	
	/**
	 * Setup view.
	 */
	private void setupView() {
		loginEdit = (EditText) findViewById(R.id.edt_login);
		passwordEdit = (EditText) findViewById(R.id.edt_password);
		loginButton = (Button) findViewById(R.id.btn_login);
		loginButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		final int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/** {@inheritDoc} **/ 
	@Override
	public void onClick(final View view) {
		try {
			WebManager.getInstance().loginRequest(loginEdit.getText().toString(), passwordEdit.getText().toString(), this);
			pDialog = new ProgressDialog(this);
			pDialog.setMessage("Logando...");
			pDialog.show();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loginSuccess(final User user) {
		pDialog.dismiss();
		PreferencesUtils.saveUserLoggedIn(this, user);
		Toast.makeText(this, "Usuário logado com sucesso! Bem vindo  "+user.getNickName(), Toast.LENGTH_SHORT).show();
		final Intent intent = new Intent(this, MainActivityImpl.class);
		startActivity(intent);
	}

	@Override
	public void requestFailed(final String message) {
		pDialog.dismiss();
		Toast.makeText(this, "Falha ao logar: "+message, Toast.LENGTH_SHORT).show();
	}
}
