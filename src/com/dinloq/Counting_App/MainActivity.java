package com.dinloq.Counting_App;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{

	TextView tvNum1;
	TextView tvNum2;
	TextView tvResult;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	Button button0;
	Button buttonCheck;
	Button buttonReverse;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setupWidgets();
    }

	private void setupWidgets () {
		tvNum1      = (TextView)    findViewById(R.id.textViewNum1);
		tvNum2      = (TextView)    findViewById(R.id.textViewNum2);
		tvResult    = (TextView)    findViewById(R.id.textViewResult);
		button0     = (Button)      findViewById(R.id.button0);
		button1     = (Button)      findViewById(R.id.button1);
		button2     = (Button)      findViewById(R.id.button2);
		button3     = (Button)      findViewById(R.id.button3);
		button4     = (Button)      findViewById(R.id.button4);
		button5     = (Button)      findViewById(R.id.button5);
		button6     = (Button)      findViewById(R.id.button6);
		button7     = (Button)      findViewById(R.id.button7);
		button8     = (Button)      findViewById(R.id.button8);
		button9     = (Button)      findViewById(R.id.button9);
		buttonCheck = (Button)      findViewById(R.id.buttonCheck);
		buttonReverse     = (Button)      findViewById(R.id.switchReverse);
	}

	private void onNumClick(View v){
		switch (v.getId()){
			case R.id.button0:

			break;
		}

	}
}
