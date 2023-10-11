package com.kamus.highriskpregnancy;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	static final String TAG = "DbHelper";
	static final String DB_NAME = "loginInfo.db"; //
	static final int DB_VERSION = 2; //
	static final String TABLE = "loginInfo";
	static final String PATIENTTABLE = "patientinfo";
	static final String _ID = "_ID";					// index = 0
	static final String usrname = "name";				// index = 1
	static final String role = "pekerjaan";				// index = 2

	static final String PID = "PID";
	static final String PATIENT_ID = "PatientID";
	static final String PATIENT_NAME = "PatientName";
	static final String VISIT = "Visit";
	static final String IDENTITY = "Identity";
	static final String PHONE_NUM = "PhoneNumber";
	static final String DATE = "date";
	static final String ADDRESS = "Address";


	
	Context context;
	
	 public MySQLiteHelper(Context context) {
    	 
	    	super(context, DB_NAME, null, DB_VERSION);
	    	this.context = context;
	    }	
 

    // Called only once, first time the DB is created
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
    	Log.i("MySQLiteHelper", "onCreate");
    	
    	/*
    	 String sql = "create table " + TABLE + " (" + "_ID" + " int primary key, "+ NewsID + " int, "
		    + Author + " text, " + AuthorMail + " text, "+ Title + " text, "+ Info + " text, "
		    + NType + " text, " + Pic + " blob, "+ SynCB + " text, " + SynCA + " text)"; //		    
		    db.execSQL(sql);
    	 */
    	
		    String sql = "create table " + TABLE + " (" + "_ID" + " int primary key, "+ usrname + " int, "
		    + role + " text) "; //
		    db.execSQL(sql); //

			String sql2 = "create table " + PATIENTTABLE + " (" + "PID" + " int primary key , "+ PATIENT_ID + " text,  "
				+ PATIENT_NAME + " text, " + VISIT + " text, "+ IDENTITY + " text, "+ PHONE_NUM + " text, "
				+ DATE + " text, " + ADDRESS + " text)";
			db.execSQL(sql2);

		    
		    Log.i(TAG, "onCreated '" + TABLE + "' sql: " + sql);




    }
    
    // Called whenever newVersion != oldVersion
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //
    	
		    // Typically do ALTER TABLE statements, but...we're just in development,
		    // so:
		    db.execSQL("drop table if exists " + TABLE);// drops the old database
		    Log.i(TAG, "onUpdated table '" + TABLE + "'");
			db.execSQL("drop table if exists " + PATIENTTABLE);
			Log.i(TAG, "onUpgrade: " + PATIENTTABLE);
		    
		 /*   db.execSQL("drop table if exists wpconfigmd");// drops the old database
		    Log.i(TAG, "onUpdated table 'wpconfig'");


		db.execSQL("drop table if exists tblSetting");// drops the old database
		Log.i(TAG, "onUpdated table 'wpconfig'");*/


		onCreate(db); // run onCreate to get new database
    }

	public boolean deleteAllRows(){

	 	boolean bflag = false;

		SQLiteDatabase db = this.getWritableDatabase();

		try{

			//db.delete(TABLE, "", null);

			db.execSQL("DELETE FROM " + TABLE); //delete all rows in a table
			bflag = true;
		}

		catch(SQLException e){

			Log.e("Error delete" + TABLE, e.getMessage());

		}

		db.close();
		return bflag;

	}

	public Boolean isTableExists() {
		SQLiteDatabase checkDB = this.getReadableDatabase();


		if(checkDB != null){
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}	public boolean validateLogin(String username) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE "+ usrname + "=?", new String[]{username});
		boolean isValid = cursor.getCount() > 0;
		cursor.close();
		db.close();
		return isValid;
	}

}	 