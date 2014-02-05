package course.examples.Fragments.DynamicLayout;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import course.examples.Fragments.DynamicLayout.TitlesFragment.ListSelectionListener;

public class QuoteViewerActivity extends Activity implements
		ListSelectionListener {

	public static String[] mTitleArray;
	public static String[] mQuoteArray;

	private final QuotesFragment mQuoteFragment = new QuotesFragment();
	private FragmentManager mFragmentManager;
	private FrameLayout mTitleFrameLayout, mQuotesFrameLayout;

	private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
	private static final String TAG = "QuoteViewerActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		// in this example we're dynamically adding and removing fragments
		//
		
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");

		super.onCreate(savedInstanceState);

		mTitleArray = getResources().getStringArray(R.array.Titles);
		mQuoteArray = getResources().getStringArray(R.array.Quotes);

		
		// call setContentView with the main layout. 
		setContentView(R.layout.main);

		
		// get references to the layouts
		mTitleFrameLayout = (FrameLayout) findViewById(R.id.title_fragment_container);
		mQuotesFrameLayout = (FrameLayout) findViewById(R.id.quote_fragment_container);

		// adding fragments dynamically is a 4 step process
		// 1. get a reference to the fragment manager
		// 2. begin a fragment transaction
		// 3. add the fragment
		// 4. commit the fragment transaction
		
		// in this case we start by adding only the title fragment. 
		// we only add the quote fragment when the text in the 
		// title fragment is clicked
		
		// if the user clicks on a title the .onListSelection() method
		// is called
		
		mFragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = mFragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.title_fragment_container,
				new TitlesFragment());
		fragmentTransaction.commit();

		mFragmentManager
				.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
					public void onBackStackChanged() {
						setLayout();
					}
				});
	}

	private void setLayout() {
		if (!mQuoteFragment.isAdded()) {
			mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
					MATCH_PARENT, MATCH_PARENT));
			mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT));
		} else {
			mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT, 1f));
			mQuotesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
					MATCH_PARENT, 2f));
		}
	}

	
	
	@Override
	public void onListSelection(int index) {
		
		// first we test if the quote fragment has already been added to 
		// the layout.
		// if the quote fragment has not been added to the layout
		// then we add it now by starting another fragmentTransaction
		//
	
		
		
		if (!mQuoteFragment.isAdded()) {
			FragmentTransaction fragmentTransaction = mFragmentManager
					.beginTransaction();
			fragmentTransaction.add(R.id.quote_fragment_container,
					mQuoteFragment);
			
			// note: we add the fragment transaction to the BackStack
			//       we do this so clicking back will take the user back 
			//       to the initial screen. 
			//       we have to do this because by default fragment changes
			//       are not tracked by the backstack
			
			
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			
			// note: executePendingTransactions() forces the transaction 
			//       to be execute synchronously .
			
			mFragmentManager.executePendingTransactions();
		}
		if (mQuoteFragment.getShownIndex() != index) {
			mQuoteFragment.showIndex(index);
		}
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}

}