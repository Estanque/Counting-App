package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import com.dinloq.Perfect_Count.framework.DBHelper;
import com.dinloq.Perfect_Count.framework.NumberGenerator;

public class StatisticActivity extends Activity {

	TextView tvRight;
	TextView tvWrong;
	TextView tvRel;

	public static final int SCALE = 2;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistic);

		initialize();

		CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
		calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
				ContentValues cv = DBHelper.loadDataFromDB(getBaseContext(), String.format("%d%d%d", day, month, year));
				//TODO check string.format
				Toast.makeText(getBaseContext(), String.format("%2$d%2$d%4$d", day, month, year), Toast.LENGTH_SHORT).show();
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

				//TODO Make statistic on selection date
				//TODO SQL Database
			}
		});
	}

	private void initialize() {
		tvRight = (TextView) findViewById(R.id.stat_right);
		tvWrong= (TextView) findViewById(R.id.stat_wrong);
		tvRel = (TextView) findViewById(R.id.stat_rel);
	}
}