package br.com.unic.lha.view.fragments.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.unic.lha.R;
import br.com.unic.lha.view.fragments.RecipesFragment;

/**
 * The Class RecipesFragmentImpl.
 */
public class RecipesFragmentImpl extends Fragment implements RecipesFragment {
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	return inflater.inflate(R.layout.fragment_recipes_screen, null);
}
}
