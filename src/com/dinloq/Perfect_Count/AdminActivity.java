package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.dinloq.Perfect_Count.framework.DBHelper;
import com.dinloq.Perfect_Count.framework.NumberGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		}
	}

	private void addRandom() {
		String right = String.valueOf(NumberGenerator.getRandomNumber());
		String wrong = String.valueOf(NumberGenerator.getRandomNumber());
		String time = DBHelper.getCurrentDate();
		String test = DBHelper.addDayRecord(right, wrong, time, this);
		Toast.makeText(this, "added "+ test, Toast.LENGTH_LONG).show();
	}

	private void viewAllRecords(){
		DBHelper dbHelper = new DBHelper(this);
		SQLiteDatabase db;
		db = dbHelper.getReadableDatabase();//TODO change to readable

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
		DBHelper dbHelper = new DBHelper(this);
		SQLiteDatabase db;

		db = dbHelper.getWritableDatabase();//TODO change to readable

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		String date = sdf.format(new Date(System.currentTimeMillis()));

		String selection = "date = ?";
		String[] selectionArgs = new String[] { date };

		Cursor c = db.query(DBHelper.TABLE_STATISTIC, null, selection, selectionArgs, null, null, null );

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
}