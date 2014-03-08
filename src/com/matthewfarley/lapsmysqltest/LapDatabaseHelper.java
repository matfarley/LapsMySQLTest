package com.matthewfarley.lapsmysqltest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
		ContentValues cv = new ContentValues();;
		cv.put(COLUMN_LAP_START_DATE, new Date().getTime()); //Store date as Millis
		getWritableDatabase().insert(TABLE_LAP, null, cv);
		
		//Toast for Testing only
		Toast.makeText(mAppContext, "Lap Added " + 
		    new Date(cv.getAsLong(COLUMN_LAP_START_DATE)), 
		    Toast.LENGTH_SHORT)
		    .show();	
	}
	
	//Query DB for number of laps done today
	public int queryLapsToday(){
		int count;
		Cursor results = getReadableDatabase().query(TABLE_LAP,
				null, COLUMN_LAP_START_DATE + " BETWEEN " + todayStart() +
				" AND " + todayEnd(),
				null, null, null, null);
		
		count = results.getCount();	
		
		//Toast for Testing only
		Toast.makeText(mAppContext, "Laps Added: " + 
				count, 
			    Toast.LENGTH_SHORT)
			    .show();
		
		results.close();
		
		return count;
			
	}
	
	private long todayStart(){
		Calendar c = Calendar. getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		return c.getTimeInMillis();
	}
	
	private long todayEnd(){
		Calendar c = Calendar. getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		return c.getTimeInMillis();
	}

}
