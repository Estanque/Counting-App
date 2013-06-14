package com.dinloq.Counting_App;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.dinloq.Counting_App.framework.NumberGenerator;
import com.dinloq.Counting_App.framework.TextViewEditor;

public class MainActivity extends Activity
{
	Boolean directionRight = true;

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

	private int Num1 = 1;
	private int Num2 = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setupWidgets();
		initialize();
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

	public void onNumClick(View v){
		switch (v.getId()){
			case R.id.button0:
				TextViewEditor.addText(tvResult,0,directionRight);
			break;
			case R.id.button1:
				TextViewEditor.addText(tvResult,1,directionRight);
			break;
			case R.id.button2:
				TextViewEditor.addText(tvResult,2,directionRight);
			break;
			case R.id.button3:
				TextViewEditor.addText(tvResult,3,directionRight);
			break;
			case R.id.button4:
				TextViewEditor.addText(tvResult,4,directionRight);
			break;
			case R.id.button5:
				TextViewEditor.addText(tvResult,5,directionRight);
			break;
			case R.id.button6:
				TextViewEditor.addText(tvResult,6,directionRight);
			break;
			case R.id.button7:
				TextViewEditor.addText(tvResult,7,directionRight);
			break;
			case R.id.button8:
				TextViewEditor.addText(tvResult,8,directionRight);
			break;
			case R.id.button9:
				TextViewEditor.addText(tvResult,9,directionRight);
			break;
		}
	}

	//TODO допилить правильное отображение при нажатиях(Особенно первом, иконка не меняется)
	public void onRevClick(View v){
		directionRight = !directionRight;
		if (directionRight) {
			buttonReverse.setText("->");
		} else {
			buttonReverse.setText("<-");
		}
	}

	public void onCheckClick(View v){
		int toCheck = Integer.parseInt(tvResult.getText().toString());
		int result = NumberGenerator.getAnswer(Num1,Num2,0);
		if (toCheck==result){
			initialize();
		} else {

		}
			tvResult.setText("");
	}

}
