package com.matthewfarley.lapsmysqltest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LapFragment extends Fragment {
	private Button mButtonAddLap;
	private Button mButtonQuery;
	private TextView mLapCountView;
	private int mLapCount; 
	private LapManager mLapManager;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mLapManager = LapManager.getLapManager(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, 
			Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_laps, parent, false);
		
		mLapCountView = (TextView)v.findViewById(R.id.lapsFragment_text_count);
		updateLapsCount(); //Needs to be done by a Loader for the first time
		
		mButtonAddLap = (Button)v.findViewById(R.id.lapsFragment_add_button);
		mButtonAddLap.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mLapManager.addLap();
				updateLapsCount();
			}
		});
		
		mButtonQuery = (Button)v.findViewById(R.id.lapsFragment_query_button);//Testing only
		mButtonQuery.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateLapsCount();
			}
		});
		
		return v;
	}
	
	private void updateLapsCount(){
		if(mLapCountView != null  && mLapManager != null ){
			mLapCount = mLapManager.getTodaysLapCount();
			mLapCountView.setText(String.valueOf(mLapCount));
		}else{
			mLapCount = 0;
			mLapCountView.setText(String.valueOf(mLapCountView));
		}
	}

}
