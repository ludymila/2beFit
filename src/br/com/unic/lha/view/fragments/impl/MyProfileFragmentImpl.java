package br.com.unic.lha.view.fragments.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.unic.lha.R;
import br.com.unic.lha.view.fragments.MyProfileFragment;

/**
 * The Class MyProfileFragmentImpl.
 */
public class MyProfileFragmentImpl extends Fragment implements
		MyProfileFragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_my_profile_screen, null);
	}
	
}
