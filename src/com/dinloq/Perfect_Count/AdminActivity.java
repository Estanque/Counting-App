package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.dinloq.Perfect_Count.framework.DBHelper;
import com.dinloq.Perfect_Count.framework.Helper;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AdminActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin);
	}

	public void onClick(View v){
		switch (v.getId()){
			case R.id.btnCheck1:
				checkSQLday();
				break;
			case R.id.btnCheck2:
				DBHelper.clearDB(this);
				break;
			case R.id.btnCheck3:
				addRandom();
				break;
			case R.id.btnCheckAll:
				viewAllRecords();
				break;
			case R.id.btnCheckNew:
				checkDB();
				break;
		}
	}

	private void addRandom() {
		String right = String.valueOf(Helper.getRandomNumber());
		String wrong = String.valueOf(Helper.getRandomNumber());
		String time = DBHelper.getDate();
		String test = DBHelper.addDayRecord(right, wrong, 10f, time, this);
		Toast.makeText(this, "added "+ test, Toast.LENGTH_LONG).show();
	}

	private void viewAllRecords(){
		DBHelper dbHelper = new DBHelper(this);
		SQLiteDatabase db;
		db = dbHelper.getReadableDatabase();

		Cursor c = db.query(DBHelper.TABLE_STATISTIC, null, null, null, null, null, null);

		if (c != null) {
			if (c.moveToFirst()) {
				String str;
				do {
					str = "R: ";
					for (String cn : c.getColumnNames()){
						str = str.concat(cn + " = "
								+ c.getString(c.getColumnIndex(cn)) + " ");
					}
					Toast.makeText(this, str, Toast.LENGTH_LONG).show();
				} while (c.moveToNext());
			} else
				Toast.makeText(this, "no one record", Toast.LENGTH_SHORT).show();
			c.close();
		} else
			Toast.makeText(this, "Cursor is null", Toast.LENGTH_SHORT).show();
		dbHelper.close();
		Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();

	}

	private void checkSQLday() {
		int temp = Helper.getRandomNumber(0);
		Toast.makeText(this, temp + "", Toast.LENGTH_SHORT).show();
	}

	private void checkDB(){
		ContentValues cv = DBHelper.loadDataFromDB(this, DBHelper.getDate());
		if (cv != null)
			Toast.makeText(this, cv.get("date").toString(), Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "cv is null", Toast.LENGTH_SHORT).show();
	}
}