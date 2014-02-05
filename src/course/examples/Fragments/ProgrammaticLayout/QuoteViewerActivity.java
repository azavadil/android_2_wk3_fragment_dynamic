package course.examples.Fragments.ProgrammaticLayout;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import course.examples.Fragments.ProgrammaticLayout.TitlesFragment.ListSelectionListener;

public class QuoteViewerActivity extends Activity implements ListSelectionListener {

	public static String[] mTitleArray;
	public static String[] mQuoteArray;
	private final QuotesFragment mQuoteFragment = new QuotesFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mTitleArray = getResources().getStringArray(R.array.Titles);
		mQuoteArray = getResources().getStringArray(R.array.Quotes);
		
		
		// call to setContentView
		setContentView(R.layout.main);

		// here's where we add the fragment
		// 4 steps
		// 1. Get a reference to the fragmentManager
		// 2. call begin transaction method on the fragment manager, returns a fragment transaction
		// 3. call the add method of the fragment transaction passing in an id for the framelayout and a 
		//      fragment that that view will hold (for both title and quote fragment)
		// 4. call commit .method() on fragment transaction. 
	
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(R.id.title_frame, new TitlesFragment());
		fragmentTransaction.add(R.id.quote_frame, mQuoteFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onListSelection(int index) {
		if (mQuoteFragment.getShownIndex() != index) {
			mQuoteFragment.showQuoteAtIndex(index);
		}
	}
}