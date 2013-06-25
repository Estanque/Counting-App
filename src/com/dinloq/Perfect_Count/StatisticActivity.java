package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;

public class StatisticActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistic);

		CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
		calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
				//TODO Make statistic on selection date
				//TODO SQL Database
			}
		});
	}
}