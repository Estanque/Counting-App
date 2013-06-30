package com.dinloq.Perfect_Count.framework;

import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.dinloq.Perfect_Count.R;

public class WidgetHelper {

	public static void addTVText(TextView tv, String number, boolean rightDirect){
		//Добавление числа в строку в зависимости от направления
		if (rightDirect) {
			tv.setText(tv.getText() + number);
		} else {
			tv.setText(number + tv.getText());
		}
	}
	public static void setTextView(TextView tv, int number){
		if (tv!=null){
			tv.setText( String.valueOf(number));
		}
	}

	public static void setSpinnerDate(Spinner spinner, Context context, int textArrayResId, int startSelection){
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, textArrayResId, R.layout.spinner_item);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		spinner.setPrompt("Choose range");
		spinner.setAdapter(adapter);
		if (startSelection > -1) {
			spinner.setSelection(startSelection);//TODO read from settings
		} else {
			spinner.setSelection(0);
		}
	}
}
