package com.dinloq.Counting_App.framework;

import android.widget.TextView;

public class TextViewEditor {

	public static void addText(TextView tv,int number,boolean rightDirect){
		//Добавление числа в строку в зависимости от направления
		if (rightDirect) {
			tv.setText(tv.getText() + String.valueOf(number));
		} else {
			tv.setText(String.valueOf(number) + tv.getText());
		}
	}
	public static void setTextView(TextView tv, int number){
		if (tv!=null){
			tv.setText( String.valueOf(number));

		}
	}
}
