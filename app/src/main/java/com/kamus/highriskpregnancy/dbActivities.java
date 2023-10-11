package com.kamus.highriskpregnancy;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;*/

public class dbActivities {
	
	MySQLiteHelper dbHelper; 
    SQLiteDatabase db;
	static final String TABLE = "loginInfo";
	static final String PATIENTTABLE = "patientinfo";


    
    public int checkDB(MySQLiteHelper helper)
	 {		
		int primary = 0;
		 dbHelper = helper;
		 SQLiteDatabase db = this.dbHelper.getReadableDatabase();
	    	Cursor cur = null ;
	    	cur = db.query(TABLE,
	                new String[] {"_ID"},
	                null,
	                null ,
	                null,
	                null,
	                null);
			 if (cur.moveToFirst())
		        {
		            do
		            {
		            	primary = cur.getInt(0);
		            } while (cur.moveToNext());
		        }
			 else
			 {
			     primary = 0; 
			 }
			 
			 db.close();
			 return primary;
	 }

	public boolean isDatabaseEmpty() {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		String query = "SELECT COUNT(*) FROM " + TABLE;
		Cursor cursor = db.rawQuery(query, null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		cursor.close();
		db.close();

		return count == 0;
	}


   /* public int countSettingDB(MySQLiteHelper helper)
    {
        Log.i("countConfigDB", "start");

        int iCount = 0;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = null ;
        cur = db.query("tblSetting",
                new String[] {"_ID"},
                null,
                null ,
                null,
                null,
                null);
        if (cur.moveToFirst())
        {
            do
            {
                iCount = iCount + 1;
                Log.i("counttblSettingDB", "_ID=" + Integer.toString(cur.getInt(0)));

            } while (cur.moveToNext());
        }
        else
        {
            iCount = 0;
            //insertLabel();
        }

        Log.i("countConfigDB", "end");

        db.close();
        return iCount;
    }
    
    public int countConfigDB(MySQLiteHelper helper)
	 {
		Log.i("countConfigDB", "start");
		
		int iCount = 0;
		 SQLiteDatabase db = helper.getReadableDatabase();
			Cursor cur = null ;
			cur = db.query("wpconfigmd",
			new String[] {"_ID"},
		        null,
		        null ,
		        null,
		        null,
		        null);
		 if (cur.moveToFirst())
		    {
		        do
		        {
		        	iCount = iCount + 1;
		        	Log.i("countConfigDB", "_ID=" + Integer.toString(cur.getInt(0)));
		        	
		        } while (cur.moveToNext());
		    }
		 else
		 {
			 iCount = 0; 
		     //insertLabel();
		 }
		 
		 Log.i("countConfigDB", "end");
			
		 db.close();
		 return iCount;
	 }*/
    
    public int checkConfigDB(MySQLiteHelper helper)
	 {
		Log.i("checkConfigDB", "start");
		
		int primary = 0;
		 SQLiteDatabase db = helper.getReadableDatabase();
			Cursor cur = null ;
			cur = db.query(TABLE,
			new String[] {"_ID"},
		        null,
		        null ,
		        null,
		        null,
		        null);
		 if (cur.moveToFirst())
		    {
		        do
		        {
		        	primary = cur.getInt(0);
		        	Log.i("checkConfigDB", "_ID=" + Integer.toString(cur.getInt(0)));
		        	//Log.i("checkConfigDB", "_ID=" + Integer.toString(cur.getInt(0)) + "; domain='"+cur.getString(3)+"'" + "; active=" + Integer.toString(cur.getInt(4)));
		            //Log.i("checkConfigDB", cur.getInt(0) + "; " + cur.getString(1) + "; " + cur.getString(2) + "; " + cur.getString(3) + "; " + cur.getInt(4));
		        } while (cur.moveToNext());
		    }
		 else
		 {
		     primary = 0; 
		     //insertLabel();
		 }
		 
		 Log.i("checkConfigDB", "end");
			
		 db.close();
		 return primary;
	 }
    
    public int checkCategoryDB(MySQLiteHelper helper)
	 {
		int primary = 0;
		 SQLiteDatabase db = helper.getReadableDatabase();
	    	Cursor cur = null ;
	    	cur = db.query("wpnewscatmd",
	                new String[] {"_ID"},
	                null,
	                null ,
	                null,
	                null,
	                null);
			 if (cur.moveToFirst())
		        {
		            do
		            {
		            	primary = cur.getInt(0);
			            //Log.d("DB get primary",cur.getString(0) + " " + cur.getString(1)+ " " + cur.getString(2));
		            } while (cur.moveToNext());
		        }
			 else
			 {
			     primary = 0; 
			     //insertLabel();
			 }
			 
			 db.close();
			 return primary;
	 }

	public byte[] GetBlob(int primary, int pos, MySQLiteHelper Helper)
	 {		
		 SQLiteDatabase db = Helper.getReadableDatabase();
		 		 
	    	Cursor cur = null ;
	    	byte[] label = null;
	    	try{
	    		cur = db.query("wpnewsmd",
	                new String[] {},
	                "_ID = '"+ primary +"'",
	                null ,
	                null,
	                null,
	                null);
	    	
			 if (cur.moveToFirst())
		        {
		            do
		            {
		            	label = cur.getBlob(pos);
		            	//Log.d("DB Bitmap"," "+cur.getString(0));
		            } while (cur.moveToNext());
		        }
			 
			 db.close();}catch(Exception e)
			 {
				 Log.d("Delete error"," "+e.getMessage());
			 }
			 return label;
	 }
	public int countConfigDB(MySQLiteHelper helper)
	{
		Log.i("countConfigDB", "start");

		int iCount = 0;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cur = null ;
		cur = db.query(TABLE,
				new String[] {"_ID"},
				null,
				null ,
				null,
				null,
				null);
		if (cur.moveToFirst())
		{
			do
			{
				iCount = iCount + 1;
				Log.i("countConfigDB", "_ID=" + Integer.toString(cur.getInt(0)));

			} while (cur.moveToNext());
		}
		else
		{
			iCount = 0;
			//insertLabel();
		}

		Log.i("countConfigDB", "end");

		db.close();
		return iCount;
	}


	public String getResult(MySQLiteHelper helper, String att, String selection)
	 {		
		 String value;
		 dbHelper = helper;
		 
		 SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		 
	    	Cursor cur = null ;
	    	cur = db.query(TABLE,
	                new String[] {att},
	                selection,
	                null,
	                null,
	                null,
	                null);
			 if (cur.moveToFirst())
		        {
		            do
		            {
		            	value= cur.getString(0);
		            	//Log.i("DB Get Icon name",cur.getString(0));
		            } while (cur.moveToNext());
		        }
			 else
			 {
				 value=null;
			     //insertLabel();
			 }
			 db.close();
			 return value;
	 }
	public String getPatientinfo(MySQLiteHelper helper, String att, String selection)
	{
		String value;
		dbHelper = helper;

		SQLiteDatabase db = this.dbHelper.getReadableDatabase();

		Cursor cur = null ;
		cur = db.query(PATIENTTABLE,
				new String[] {att},
				selection,
				null,
				null,
				null,
				null);
		if (cur.moveToFirst())
		{
			do
			{
				value= cur.getString(0);
				//Log.i("DB Get Icon name",cur.getString(0));
			} while (cur.moveToNext());
		}
		else
		{
			value=null;
			//insertLabel();
		}
		db.close();
		return value;
	}

 /*   public int getSettingValue(MySQLiteHelper helper, String att, String selection)
    {
        int value;
        dbHelper = helper;

        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cur = null ;
        cur = db.query("tblSetting",
                new String[] {att},
                selection,
                null,
                null,
                null,
                null);
        if (cur.moveToFirst())
        {
            do
            {
                value= cur.getInt(0);
                //Log.i("DB Get Icon name",cur.getString(0));
            } while (cur.moveToNext());
        }
        else
        {
            value=0;
            //insertLabel();
        }
        db.close();
        return value;
    }
    
    public String getConfigValue(MySQLiteHelper helper, String att, String selection)
	 {		
		 String value;
		 dbHelper = helper;
		 
		 SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		 
	    	Cursor cur = null ;
	    	cur = db.query("wpconfigmd",
	                new String[] {att},
	                selection,
	                null,
	                null,
	                null,
	                null);
			 if (cur.moveToFirst())
		        {
		            do
		            {
		            	value= cur.getString(0);
		            	//Log.i("DB Get Icon name",cur.getString(0));
		            } while (cur.moveToNext());
		        }
			 else
			 {
				 value=null;
			     //insertLabel();
			 }
			 db.close();
			 return value;
	 }
*/
    public String[] getResultArray(MySQLiteHelper helper, String att, String selection)
	 {
		Log.i("getResultArray", "Get: " + att);
		
		 String[] value;
		 int i = 0;
		 dbHelper = helper;
		 
		 SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		 
	    	Cursor cur = null ;
	    	cur = db.query("wpnewsmd",
	                new String[] {"*"},
	                selection,
	                null,
	                null,
	                null,
	                null);
	    	 value = new String[cur.getColumnCount()];
	    	 
	    	 if (cur.moveToFirst())
		        {
		            do
		            {
		            	for (i=0;i<5;i++)
		            	{
		            		value[i]= cur.getString(i);
		            		Log.i("DB Get news values","value = "+cur.getString(i));
		            	}
		            	
		            } while (cur.moveToNext());
		        }
			 else
			 {
        		 Log.d("DB Get news values","no val");
			 }
			 db.close();
			 return value;
	 }
    
    public String[] getResultConfig(MySQLiteHelper helper, String att, String selection)
	{
		Log.i("getResultConfig", "Get: " + att);

		String[] value;
		int i = 0;

		SQLiteDatabase db = helper.getReadableDatabase();

		Cursor cur = null ;
		cur = db.query(TABLE,
				new String[] {"*"},
				selection,
				null,
				null,
				null,
				null);
		value = new String[cur.getColumnCount()];
		if(cur!=null && cur.getCount() > 0) {
			if (cur.moveToFirst()) {
				do {
					for (i = 0; i < 9; i++) {
						value[i] = cur.getString(i);
						Log.i("DB getResultConfig", "val = " + cur.getString(i));
					}

				} while (cur.moveToNext());
			}
		}
		else
		{
			Log.d("DB getResultConfig","no val");
		}
		db.close();
		return value;
	}
    
    public boolean SaveDB(MySQLiteHelper helper, int primary, String Name, String Role)
    {
		SQLiteDatabase db = null;
		boolean success = false;

		try {
			// Open the database for writing
			db = helper.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(MySQLiteHelper._ID, primary);
			values.put(MySQLiteHelper.usrname, Name);
			values.put(MySQLiteHelper.role, Role);

			// Insert into the database
			long result = db.insertOrThrow(MySQLiteHelper.TABLE, null, values);

			if (result != -1) {
				// Data was successfully inserted
				success = true;
				Log.i("SaveDB", "Data inserted successfully");
			} else {
				// Data insertion failed
				Log.e("SaveDB", "Data insertion failed");
			}
		} catch (SQLException e) {
			Log.e("SaveDB", "Error while inserting data: " + e.getMessage(), e);
		} finally {
			if (db != null) {
				db.close();
			}
		}

		return success;
    }
	public boolean SavePatientDB (MySQLiteHelper helper, String Pid, String PName, String PVisit, String KTP, String Phone,
	String Dates, String Address)
	{
		SQLiteDatabase db = null;
		boolean success = false;

		try {
			// Open the database for writing
			db = helper.getWritableDatabase();

			ContentValues values = new ContentValues();
//			values.put(MySQLiteHelper.PID, primary);
			values.put(MySQLiteHelper.PATIENT_ID, Pid);
			values.put(MySQLiteHelper.PATIENT_NAME, PName);
			values.put(MySQLiteHelper.VISIT, PVisit);
			values.put(MySQLiteHelper.IDENTITY, KTP);
			values.put(MySQLiteHelper.PHONE_NUM, Phone);
			values.put(MySQLiteHelper.IDENTITY, KTP);
			values.put(MySQLiteHelper.DATE, Dates);
			values.put(MySQLiteHelper.ADDRESS, Address);


			// Insert into the database
			long result = db.insertOrThrow(MySQLiteHelper.PATIENTTABLE, null, values);

			if (result != -1) {
				// Data was successfully inserted
				success = true;
				Log.i("SaveDB", "Data inserted successfully");
			} else {
				// Data insertion failed
				Log.e("SaveDB", "Data insertion failed");
			}
		} catch (SQLException e) {
			Log.e("SaveDB", "Error while inserting data: " + e.getMessage(), e);
		} finally {
			if (db != null) {
				db.close();
			}
		}

		return success;
	}
    
    /*public boolean SaveConfigDB(MySQLiteHelper helper, int primary, String username, String password, String server, boolean active,String token,String sessionID)
    {

		boolean success;
		long lReturn;
		int iActive = (active)? 1 : 0;
		
    	// Open the database for writing
    	db = helper.getWritableDatabase();
    	
    	// Loop over the time line and print it out
    	ContentValues values = new ContentValues();
    	
    	// Insert into database
    	values.clear(); 
    	values.put("_ID", primary);
    	values.put("username", username);
    	values.put("password", password);
    	values.put("server", server);
    	values.put("active", iActive);
    	values.put("token",token);
		values.put("sessionID",sessionID);
		values.put("expired", sessionID);
		values.put("loca", sessionID);
    	
    	try {
    		lReturn=db.insertOrThrow("wpconfigmd", null, values); 
	    	//Log.i("dbActivities - SaveConfigDB : Insert into database; primary='"+primary+"'", "Save Configuration");

	    	if (lReturn > 0)
	    		success = true;
	    	else
	    		success = false;
	    	
    	}catch (SQLException e) {
    		//Ignore exception
    		Log.e("Error SaveConfigDB", e.getMessage());
    		success = false;
    	}
    	db.close();
    	return success;
    }*/
    


    public void deletebyId(int id)
    {
    	db = dbHelper.getWritableDatabase();
    	try {
    		db.delete(MySQLiteHelper.TABLE, "_ID="+id, null);
	    	Log.d("Delete from db", "Insert new property");
    	}catch (SQLException e) {
    		//Ignore exception
    		Log.e("Error delete", e.getMessage());
    	}
    	db.close();
    }
    
    public void deleteConfigById(int id)
    {
    	db = dbHelper.getWritableDatabase();
    	try {
    		db.delete("wpconfigmd", "_ID='"+id+"'", null); 
    	}catch (SQLException e) {
    		//Ignore exception
    		Log.e("Error delete", e.getMessage());
    	}
    	db.close();
    }
    
    /*public boolean updateDB(MySQLiteHelper helper, int primary, String Title, String Info, String NType, byte[] Image1, String SynCA, String sDomain, String sImgLoc)
	 {
		
		 db = dbHelper.getWritableDatabase();
		 ContentValues values = new ContentValues();
	    	
		 // Insert into database
		 List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
	     //nameValuePair.add((NameValuePair) new BasicNameValuePair("Author", Author));
	     //nameValuePair.add((NameValuePair) new BasicNameValuePair("AuthorMail", AuthorMail));
	     nameValuePair.add((NameValuePair) new BasicNameValuePair(MySQLiteHelper.Title, Title));
	     nameValuePair.add((NameValuePair) new BasicNameValuePair(MySQLiteHelper.Info, Info));
	     nameValuePair.add((NameValuePair) new BasicNameValuePair(MySQLiteHelper.NType, NType));
	     //nameValuePair.add((NameValuePair) new BasicNameValuePair("SynCB", SynCB));
	     nameValuePair.add((NameValuePair) new BasicNameValuePair(MySQLiteHelper.SynCA, SynCA));
	     nameValuePair.add((NameValuePair) new BasicNameValuePair(MySQLiteHelper.Domain, sDomain));
	     nameValuePair.add((NameValuePair) new BasicNameValuePair(MySQLiteHelper.imgLoc, sImgLoc));
	    	
	     values.clear(); 
	     values.put(MySQLiteHelper.Pic, Image1);
	     
	     for(int index=0; index < nameValuePair.size(); index++) {
	    	 //Log.d("Update "+index+"= "+nameValuePair.get(index).getName(), nameValuePair.get(index).getValue());
	    	 values.put(nameValuePair.get(index).getName(), nameValuePair.get(index).getValue());
	     }
	     
		 try 
		 {
		 	db.update(MySQLiteHelper.TABLE, values, "_ID = "+primary, null);
	    	//Log.i("Update database", "Update the label. Rows updated = "+rowUpdate);
		 	db.close();
	    	return true;
		 }
		 catch (SQLException e)
		 { 
    		//Ignore exception
    		Log.e("Error update", e.getMessage());
    		db.close();
    		return false;
		 }
	 }*/
    
    public boolean updateDB(String Pid, int primary)
	 {
		
		 db = dbHelper.getWritableDatabase();
		 ContentValues values = new ContentValues();
	    	
	    	// Insert into database
	    	values.clear(); 
	    	values.put(MySQLiteHelper.usrname, Pid);
	    	
		
		 try {
			 	@SuppressWarnings("unused")
				int rowUpdate = db.update(MySQLiteHelper.TABLE, values, "_ID = "+primary, null);
		    	//Log.i("Update database", "Update the label.Rows updated = "+rowUpdate);
		    	db.close();
		    	return true;
	    	}catch (SQLException e) {
	    		//Ignore exception
	    		Log.e("Error update", e.getMessage());
	    		db.close();
	    		return false;
	    	}
	 }
    
    public boolean UpdateConfigDB(MySQLiteHelper helper, int primary, String username, String password, String server, boolean active,String token,String sessionID)
    {
		boolean success;
		int iActive = (active)? 1 : 0;

		// Open the database for writing
		db = helper.getWritableDatabase();
		// Loop over the time line and print it out
		ContentValues values = new ContentValues();
		
		// Insert into database
		values.clear(); 
		values.put("_ID", primary);
		values.put("username", username);
		values.put("password", password);
		values.put("server", server);
		values.put("active", iActive);
        values.put("token", iActive);
		values.put("sessionID", sessionID);
		values.put("expired", sessionID);
		values.put("loca", sessionID);
	     
		try 
		{
			db.update("wpconfigmd", values, "_ID = "+primary, null);
			Log.i("UpdateConfigDB", "server='"+server+"'; ID = "+primary);
		 	success = true;
		}
		catch (SQLException e)
		{ 
			//Ignore exception
			Log.e("Error UpdateConfigDB", e.getMessage());
			success = false;
		}
		 
    	db.close();
    	return success;
    }



	public boolean UpdateSetting(MySQLiteHelper helper, int primary,int active)
	{
		boolean success;
		// Open the database for writing
		db = helper.getWritableDatabase();
		// Loop over the time line and print it out
		ContentValues values = new ContentValues();

		// Insert into database
		values.clear();
		values.put("_ID", primary);
		values.put("active", active);

		try {
			db.delete("tblSetting", "_ID="+primary, null);
			db.insertOrThrow("tblSetting", null, values);
			//Log.i("dbActivities - SaveConfigDB : Insert into database", "Insert user configuration");
			success = true;
		}catch (SQLException e) {
			//Ignore exception
			Log.e("Error insert", e.getMessage());
			success = false;
		}
		db.close();
		return success;
	}

	public boolean UpdateConfigDBVersion(MySQLiteHelper helper, int primary,String version)
	{
		boolean success;


		// Open the database for writing
		db = helper.getWritableDatabase();
		// Loop over the time line and print it out
		ContentValues values = new ContentValues();

		// Insert into database
		values.clear();

		values.put("version", version);

		try
		{
			db.update("wpconfigmd", values, "_ID = "+primary, null);

			success = true;
		}
		catch (SQLException e)
		{
			//Ignore exception
			Log.e("Error UpdateConfigDB", e.getMessage());
			success = false;
		}

		db.close();
		return success;
	}

    public boolean UpdateAllUnActive(MySQLiteHelper helper)
    {
		boolean success;
		int iRetVal;

		// Open the database for writing
		db = helper.getWritableDatabase();
		
		// Loop over the time line and print it out
		ContentValues values = new ContentValues();
		
		// Insert into database
		values.clear(); 
		values.put("active", 0);
	     
		try 
		{
			iRetVal = db.update("wpconfigmd", values, "active='1'", null);
			Log.i("UpdateAllUnActive", "iRetVal='"+iRetVal+"'");
			
			if (iRetVal > 0)
				success = true;
			else
				success = false;
		}
		catch (SQLException e)
		{ 
			//Ignore exception
			Log.e("Error UpdateAllUnActive", e.getMessage());
			success = false;
		}
		 
    	db.close();
    	return success;
    }
    
    public boolean UpdateActiveStat(MySQLiteHelper helper, int iprimary)
    {
		boolean success;
		int iRetVal;

		// Open the database for writing
		db = helper.getWritableDatabase();
		
		// Loop over the time line and print it out
		ContentValues values = new ContentValues();
		
		// Insert into database
		values.clear(); 
		values.put("active", 1);
	     
		try 
		{
			iRetVal = db.update("wpconfigmd", values, "_ID='"+iprimary+"'", null);
			Log.i("UpdateActiveStat", "iRetVal='"+iRetVal+"'; _ID='"+iprimary+"'.");
			
			if (iRetVal > 0)
				success = true;
			else
				success = false;
		}
		catch (SQLException e)
		{ 
			//Ignore exception
			Log.e("Error UpdateActiveStat", e.getMessage());
			success = false;
		}
		 
    	db.close();
    	return success;
    }


	public boolean UpdateLocationManagement(MySQLiteHelper helper, int primary,boolean active, String locName, String LocKey,String token)
	{
		boolean success;
		int iActive = (active)? 1 : 0;

		// Open the database for writing
		db = helper.getWritableDatabase();
		// Loop over the time line and print it out
		ContentValues values = new ContentValues();

		// Insert into database
		values.clear();
		values.put("loca", locName);
		values.put("lockey", LocKey);
		values.put("token",token);


		try
		{
			db.update("wpconfigmd", values, "active = 1", null);
			Log.i("UpdateConfigDB", "server='"+primary+"'; ID = "+primary);
			success = true;
		}
		catch (SQLException e)
		{
			//Ignore exception
			Log.e("Error UpdateConfigDB", e.getMessage());
			success = false;
		}

		db.close();
		return success;
	}

	public String getLocationName(MySQLiteHelper helper, String att, String selection)
	{
		String value;
		dbHelper = helper;

		SQLiteDatabase db = this.dbHelper.getReadableDatabase();

		Cursor cur = null ;
		cur = db.query("wpconfigmd",
				new String[] {att},
				selection,
				null,
				null,
				null,
				null);
		if (cur.moveToFirst())
		{
			do
			{
				value= cur.getString(0);
				//Log.i("DB Get Icon name",cur.getString(0));
			} while (cur.moveToNext());
		}
		else
		{
			value=null;
			//insertLabel();
		}
		db.close();
		return value;
	}

}
