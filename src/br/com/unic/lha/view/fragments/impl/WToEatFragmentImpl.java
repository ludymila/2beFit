package br.com.unic.lha.view.fragments.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.unic.lha.R;
import br.com.unic.lha.view.fragments.WToEatFragment;

/**
 * The Class WToEatFragmentImpl.
 * 
 * @author hdnn
 */
public class WToEatFragmentImpl extends Fragment implements WToEatFragment {
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_wtoeat_screen, null);
	}

}
