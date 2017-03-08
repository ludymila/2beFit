package br.com.unic.lha.view.activity.impl;

import android.R;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import br.com.unic.lha.NavigationDrawerFragment;
import br.com.unic.lha.view.activity.MainActivity;
import br.com.unic.lha.view.fragments.impl.MyProfileFragmentImpl;
import br.com.unic.lha.view.fragments.impl.RecipesFragmentImpl;
import br.com.unic.lha.view.fragments.impl.TimeLineFragmentsImpl;
import br.com.unic.lha.view.fragments.impl.WToEatFragmentImpl;

/**
 * The Class MainActivityImpl.
 * 
 * @author hdnn
 */
public class MainActivityImpl extends FragmentActivity implements
		MainActivity {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	/* (non-Javadoc)
	 * @see br.com.unic.lha.NavigationDrawerFragment.NavigationDrawerCallbacks#onNavigationDrawerItemSelected(int)
	 */
	@Override
	public void onNavigationDrawerItemSelected(final int position) {
		// update the main content by replacing fragments
		final FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment nextFrag;
		switch (position) {
		case 0:
			MyProfileFragmentImpl mProfileFrag = (MyProfileFragmentImpl) getSupportFragmentManager().findFragmentByTag(MyProfileFragmentImpl.class.getSimpleName());
			if (mProfileFrag == null) {
				mProfileFrag = new MyProfileFragmentImpl();
			}
			nextFrag = mProfileFrag;
			break;
		case 1:
			TimeLineFragmentsImpl timeLineFrag = (TimeLineFragmentsImpl) getSupportFragmentManager().findFragmentByTag(MyProfileFragmentImpl.class.getSimpleName());
			if (timeLineFrag == null) {
				timeLineFrag = new TimeLineFragmentsImpl();
			}
			nextFrag = timeLineFrag;
			break;
		case 2:
			WToEatFragmentImpl wToEatFrag = (WToEatFragmentImpl) getSupportFragmentManager().findFragmentByTag(MyProfileFragmentImpl.class.getSimpleName());
			if (wToEatFrag == null) {
				wToEatFrag = new WToEatFragmentImpl();
			}
			nextFrag = wToEatFrag;
			break;
		case 3:
			RecipesFragmentImpl recipesFrag = (RecipesFragmentImpl) getSupportFragmentManager().findFragmentByTag(MyProfileFragmentImpl.class.getSimpleName());
			if (recipesFrag == null) {
				recipesFrag = new RecipesFragmentImpl();
			}
			nextFrag = recipesFrag;
			break;
		default:
			nextFrag = null;
			break;
		}
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						nextFrag).commit();
	}

	/**
	 * Restore action bar.
	 */
	@SuppressWarnings("deprecation")
	public void restoreActionBar() {
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
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
//
//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//		/**
//		 * The fragment argument representing the section number for this
//		 * fragment.
//		 */
//		private static final String ARG_SECTION_NUMBER = "section_number";
//
//		/**
//		 * Returns a new instance of this fragment for the given section number.
//		 *
//		 * @param sectionNumber the section number
//		 * @return the placeholder fragment
//		 */
//		public static PlaceholderFragment newInstance(final int sectionNumber) {
//			final PlaceholderFragment fragment = new PlaceholderFragment();
//			final Bundle args = new Bundle();
//			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//			fragment.setArguments(args);
//			return fragment;
//		}
//
//		/**
//		 * Instantiates a new placeholder fragment.
//		 */
//		public PlaceholderFragment() {
//		}
//
//		/* (non-Javadoc)
//		 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
//		 */
//		@Override
//		public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
//				final Bundle savedInstanceState) {
//			final View rootView = inflater.inflate(R.layout.fragment_main, container,
//					false);
//			return rootView;
//		}
//
//		/* (non-Javadoc)
//		 * @see android.app.Fragment#onAttach(android.app.Activity)
//		 */
//		@Override
//		public void onAttach(final Activity activity) {
//			super.onAttach(activity);
//			((MainActivityImpl) activity).onSectionAttached(getArguments().getInt(
//					ARG_SECTION_NUMBER));
//		}
//	}

}
