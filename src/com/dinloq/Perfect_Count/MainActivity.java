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
import android.widget.Toast;
import com.dinloq.Perfect_Count.framework.DBHelper;
import com.dinloq.Perfect_Count.framework.NumberGenerator;
import com.dinloq.Perfect_Count.framework.TextViewEditor;

public class MainActivity extends Activity
{
	Boolean directionRight;
	//Timer
	//TODO Catch all values of Timer in ArrayList, after closing Activity calculate the average and write into database
	// (avg.database + avg.this /2)

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
	private int scaleToRound = 2;

	private int Num1 = 1;
	private int Num2 = 1;
	private int TRAIN_MODE = 0;

	private int rightAnswers = 0;
	private int wrongAnswers = 0;

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
			tvRel.setText(NumberGenerator.countRelation(rightAnswers, wrongAnswers, scaleToRound) + "%");
		} else
			tvRel.setText("-");
	}

	private void loadDataFromDB() {
		ContentValues cv = DBHelper.loadDataFromDB(this,DBHelper.getCurrentDate());
		if (cv != null) {
			rightAnswers = cv.getAsInteger(DBHelper.TABLE_RIGHT_FLD);
			wrongAnswers = cv.getAsInteger(DBHelper.TABLE_WRONG_FLD);
		}
	}

	@Override
	protected void onDestroy() {
		DBHelper.addDayRecord(rightAnswers + "", wrongAnswers + "", DBHelper.getCurrentDate(), this);
		//TODO count average of time
		//TODO Save changes to database
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
		//TODO reading sql database
	}

	//TODO add timer
	private void initialize() {
		//Timer = start value
		displayResults();
		Num1 = NumberGenerator.getRandomNumber();
		Num2 = NumberGenerator.getRandomNumber();
		tvNum1.setText(String.valueOf(Num1));
		tvNum2.setText(String.valueOf(Num2));
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
		TextViewEditor.addText(tvResult,str,directionRight);
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
		int result = NumberGenerator.getAnswer(Num1,Num2,TRAIN_MODE);
		if (toCheck==result){
			//total_time = this.time - timer; write result
			rightAnswers++;
			initialize();
			Toast.makeText(getApplicationContext(),"Right!",Toast.LENGTH_SHORT).show();
		} else {
			wrongAnswers++;
			displayResults();
			Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_SHORT).show();
		}
		tvResult.setText("");
	}
}
