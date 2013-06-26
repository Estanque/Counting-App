package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import com.dinloq.Perfect_Count.framework.DBHelper;
import com.dinloq.Perfect_Count.framework.NumberGenerator;

import java.util.GregorianCalendar;

public class StatisticActivity extends Activity {

	public static final int SCALE = 2;

	TextView tvRight;
	TextView tvWrong;
	TextView tvRel;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistic);
		initialize();

		CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
		calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
				GregorianCalendar gc = new GregorianCalendar(year, month, day);
				String date = DBHelper.getDate(gc);
				showStatistic(date);
			}
		});
		showStatistic(DBHelper.getDate());
	}

	private void initialize() {
		tvRight = (TextView) findViewById(R.id.stat_right);
		tvWrong= (TextView) findViewById(R.id.stat_wrong);
		tvRel = (TextView) findViewById(R.id.stat_rel);
	}

	private void showStatistic(String date){
		ContentValues cv = DBHelper.loadDataFromDB(getBaseContext(), date);
		int right = 0;
		int wrong = 0;
		if (cv != null) {
			right = cv.getAsInteger(DBHelper.TABLE_RIGHT_FLD);
			wrong = cv.getAsInteger(DBHelper.TABLE_WRONG_FLD);
		}
		Float rel = NumberGenerator.countRelation(right,wrong, SCALE);
		tvRight.setText(right + "");
		tvWrong.setText(wrong + "");
		tvRel.setText(rel + "");
	}
}