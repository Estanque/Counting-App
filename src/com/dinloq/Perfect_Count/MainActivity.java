package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.dinloq.Perfect_Count.framework.DBHelper;
import com.dinloq.Perfect_Count.framework.Helper;
import com.dinloq.Perfect_Count.framework.WidgetHelper;

import java.util.ArrayList;

public class MainActivity extends Activity
{
	Boolean directionRight;

	TextView tvNum1;
	TextView tvNum2;
	TextView tvResult;
	TextView tvRight;
	TextView tvWrong;
	TextView tvRel;
	TextView tvTime;

	Button buttonCheck;
	Button buttonReverse;

	// Точность округления
	public static int scaleToRound = 2;

	private int Num1 = 1;
	private int Num2 = 1;
	private int TRAIN_MODE = 0;
	private int COUNT_RANGE = 0;

	private int rightAnswers = 0;
	private int wrongAnswers = 0;
	private ArrayList<Float> timerList = new ArrayList<Float>();
	private long timerStartValue;
	private long timerEndValue;

	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setupWidgets();
	    loadSettings();
	    loadDataFromDB();
	    waitForReady();
    }

	private void displayResults() {
		tvRight.setText(rightAnswers + "");
		tvWrong.setText(wrongAnswers + "");
		if (rightAnswers + wrongAnswers != 0) {
			//float rel = (float) rightAnswers / (float)(rightAnswers + wrongAnswers);
			tvRel.setText(Helper.round(rightAnswers, wrongAnswers, scaleToRound) + "%");
		} else
			tvRel.setText("-");
		tvTime.setText(Helper.getAvg(timerList) + "");
	}

	private void loadDataFromDB() {
		ContentValues cv = DBHelper.loadDataFromDB(this,DBHelper.getDate());
		if (cv != null) {
			rightAnswers = cv.getAsInteger(DBHelper.TABLE_RIGHT_FLD);
			wrongAnswers = cv.getAsInteger(DBHelper.TABLE_WRONG_FLD);
			timerList.add(cv.getAsFloat("time"));
		}
	}

	@Override
	protected void onDestroy() {
		float avgTime = Helper.getAvg(timerList);
		DBHelper.addDayRecord(rightAnswers + "", wrongAnswers + "", avgTime, DBHelper.getDate(), this);
		super.onDestroy();
	}

	private void waitForReady() {
		AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
		ad.setTitle(R.string.main_ready);
		ad.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				initialize();
			}
		});
		ad.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				finish();
			}
		});
		ad.setCancelable(false);
		ad.show();
	}

	private void loadSettings() {
		//Get mode of training
		TRAIN_MODE = getIntent().getIntExtra(MenuActivity.CHOOSE_MODE_STRING_EXTRA, 0);
		//Setting operation TextView icon of operation
		String[] operationIcons = getResources().getStringArray(R.array.operation_icon);
		TextView tvOperation = (TextView) findViewById(R.id.tvOperation);
		tvOperation.setText(operationIcons[TRAIN_MODE]);
		//Get preferences
		SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
		directionRight = !sPref.getBoolean("set_reverse", false);
		setReverseButtonText();

		COUNT_RANGE = Helper.getRange(sPref, TRAIN_MODE);
	}

	private void initialize() {
		displayResults();
		Num1 = Helper.getRandomNumber(COUNT_RANGE);
		Num2 = Helper.getRandomNumber(COUNT_RANGE);
		tvNum1.setText(String.valueOf(Num1));
		tvNum2.setText(String.valueOf(Num2));
		timerStartValue = System.currentTimeMillis();
	}


	private void setupWidgets () {
		tvNum1      = (TextView)    findViewById(R.id.textViewCh01);
		tvNum2      = (TextView)    findViewById(R.id.textViewNum2);
		tvResult    = (TextView)    findViewById(R.id.textViewResult);
		buttonCheck = (Button)      findViewById(R.id.btnCheck);
		buttonReverse     = (Button)      findViewById(R.id.btnReverse);

		tvRight = (TextView) findViewById(R.id.main_right);
		tvWrong = (TextView) findViewById(R.id.main_wrong);
		tvRel = (TextView) findViewById(R.id.main_rel);
		tvTime = (TextView) findViewById(R.id.main_time);
	}

	public void onNumClick(View v){
		// number of dial button gets from tag field in xml
		Button temp = (Button) findViewById(v.getId());
		String str = temp.getTag().toString();
		WidgetHelper.addTVText(tvResult, str, directionRight);
	}

	//TODO Why the btnReverse is so necessary? so many excess code...
	public void onClick(View v){
		switch (v.getId()){
			case R.id.btnReverse:
				directionRight = !directionRight;
				setReverseButtonText();
				break;
			case R.id.btnCheck:
				checkResult();
				break;
			case R.id.btnClear:
				tvResult.setText("");
				break;
		}
	}

	private void setReverseButtonText(){
		if (directionRight) {
			buttonReverse.setText("->");
		} else {
			buttonReverse.setText("<-");
		}
	}

	private void checkResult(){
		int toCheck = 0;
		if (tvResult.getText().toString().length()>0){
			toCheck = Integer.parseInt(tvResult.getText().toString());
		}
		int result = Helper.getAnswer(Num1, Num2, TRAIN_MODE);
		if (toCheck==result){
			timerEndValue = System.currentTimeMillis();
			float temp = (float) (timerEndValue - timerStartValue)/1000;
			timerList.add(temp);
			rightAnswers++;
			initialize();
			Helper.showToast("Right! Time - " + temp, getApplicationContext());
		} else {
			wrongAnswers++;
			displayResults();
			Helper.showToast("Wrong!", getApplicationContext());
		}
		tvResult.setText("");
	}
}
