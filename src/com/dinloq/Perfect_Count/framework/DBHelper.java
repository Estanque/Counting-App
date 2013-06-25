package com.dinloq.Perfect_Count.framework;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE = "app_db";
	public static final String TABLE_STATISTIC = "table_stat";

	public DBHelper(Context context) {
		super(context, DATABASE, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String name = "mytable";
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_STATISTIC + " ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "right INTEGER," + "wrong INTEGER,"
				+ "date INTEGER PRIMARY KEY" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*db.execSQL("CREATE TABLE " + TABLE_STATISTIC + " ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "right INTEGER," + "wrong INTEGER,"
				+ "date INTEGER" + ");");*/
	}

	public static void addDayRecord(String right, String wrong, String time, Context context){
		DBHelper dbHelper = new DBHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("right", Integer.parseInt(right));
		cv.put("wrong", Integer.parseInt(wrong));
		cv.put("time", Integer.parseInt(time));

		db.insert(DBHelper.TABLE_STATISTIC, null, cv);

		dbHelper.close();
	}

	public static void clearDB(Context context){
		DBHelper dbHelper = new DBHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete(DBHelper.TABLE_STATISTIC, null, null);
	}

	public static String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		String date = sdf.format(new Date(System.currentTimeMillis()));
		return date;
	}
}
