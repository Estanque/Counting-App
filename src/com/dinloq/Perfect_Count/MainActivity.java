package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.dinloq.Perfect_Count.framework.NumberGenerator;
import com.dinloq.Perfect_Count.framework.TextViewEditor;

public class MainActivity extends Activity
{
	Boolean directionRight;

	TextView tvNum1;
	TextView tvNum2;
	TextView tvResult;
	Button buttonCheck;
	Button buttonReverse;

	private int Num1 = 1;
	private int Num2 = 1;
	private int TRAIN_MODE = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setupWidgets();
	    loadSettings();
		initialize();
    }

	private void loadSettings() {
		//Get mode of training
		TRAIN_MODE = getIntent().getIntExtra(MenuActivity.CHOOSE_MODE_STRING_EXTRA, 0);
		String[] operationIcons = getResources().getStringArray(R.array.operation_icon);
		TextView tvOperation = (TextView) findViewById(R.id.tvOperation);
		tvOperation.setText(operationIcons[TRAIN_MODE]);
		//Get preferences
		SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
		directionRight = !sPref.getBoolean("set_reverse", false);
		setReverseButtonText();
	}

	//TODO add timer
	private void initialize() {
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

	public void setReverseButtonText(){
		if (directionRight) {
			buttonReverse.setText("->");
		} else {
			buttonReverse.setText("<-");
		}
	}

	public void checkResult(){
		int toCheck = 0;
		if (tvResult.getText().toString().length()>0){
			toCheck = Integer.parseInt(tvResult.getText().toString());
		}
		int result = NumberGenerator.getAnswer(Num1,Num2,TRAIN_MODE);
		if (toCheck==result){
			initialize();
			Toast.makeText(getApplicationContext(),"Right!",Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_SHORT).show();
		}
		tvResult.setText("");
	}
}
