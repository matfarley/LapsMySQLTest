package com.matthewfarley.lapsmysqltest;

import android.content.Context;

public class LapManager {
	private String mLapsCount;
	private Context mAppContext;
	private static LapManager mLapManager;
	private LapDatabaseHelper mDatabaseHelper;
	
	//Singleton
	private LapManager(Context context){
		mAppContext = context;
		mDatabaseHelper = new LapDatabaseHelper(mAppContext);
	}
	
	public static LapManager getLapManager(Context context){
		if (mLapManager == null){
			mLapManager = new LapManager(context.getApplicationContext());
		}
		return mLapManager;
	}
	
	// Adds a lap to DB
	public void addLap(){ 
	   mDatabaseHelper.insertLap();
	}
	
	//queries database.
	public int getTodaysLapCount(){ 
	    return mDatabaseHelper.queryLapsToday();
	}

}
