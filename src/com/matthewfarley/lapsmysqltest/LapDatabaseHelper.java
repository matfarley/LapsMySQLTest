package com.matthewfarley.lapsmysqltest;

import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class LapDatabaseHelper extends SQLiteOpenHelper {
	private Context mAppContext;
	
	private static final String DB_NAME = "laps.sqlite";
	private static final int VERSION = 1;
	
	private static String TABLE_LAP = "lap";
	private static final String COLUMN_LAP_ID = "_id";
	private static final String COLUMN_LAP_START_DATE = "start_date";
	
	public LapDatabaseHelper(Context context){
		super(context, DB_NAME, null, VERSION);
		mAppContext = context;
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db){
		//Create the "lap" table
		db.execSQL("create table lap (" +
		    "_id integer primary key autoincrement, start_date integer)");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//Implement changes to schema here
	}
	
	//Usually would return a long as an id once finished adding
	public void insertLap(){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_LAP_START_DATE, getIntDateToday());
		getWritableDatabase().insert(TABLE_LAP, null, cv);
		
		//Toast for Testing only
		Toast.makeText(mAppContext, "Lap Added " + 
		    cv.getAsLong(COLUMN_LAP_START_DATE), 
		    Toast.LENGTH_SHORT)
		    .show();	
	}
	
	//Query DB for number of laps done today
	public int queryLapsToday(){
		Cursor results = getReadableDatabase().query(TABLE_LAP,
				null, COLUMN_LAP_START_DATE + "= " + getIntDateToday(),
				null, null, null, null);
		
		//Toast for Testing only
		Toast.makeText(mAppContext, "Lap Added " + 
				results.getCount(), 
			    Toast.LENGTH_SHORT)
			    .show();
		
		return results.getCount();		
	}
	
	//Used to get todays date in the format ddmmyyyy
	public int getIntDateToday(){
		Calendar c1 = Calendar. getInstance();
	    StringBuilder sb = new StringBuilder();
	    sb.append(c1.get(Calendar.YEAR));
	    sb.append(c1.get(Calendar.MONTH));
	    sb.append(c1.get(Calendar.DAY_OF_MONTH));
	    return Integer.parseInt(sb.toString());
	}

}
