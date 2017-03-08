package br.com.unic.lha.app;

import android.app.Application;
import br.com.unic.lha.web.WebManager;

/**
 * The Class AppAplication.
 * 
 * @author hildon.lima
 */
public class AppAplication extends Application {
	
	/** {@inheritDoc} **/ 
	@Override
	public void onCreate() {
		super.onCreate();
		WebManager.initClass(this);
	}
	
	/** {@inheritDoc} **/ 
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
