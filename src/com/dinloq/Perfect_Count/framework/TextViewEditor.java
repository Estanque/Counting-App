package com.dinloq.Perfect_Count.framework;

import android.widget.TextView;

public class TextViewEditor {

	public static void addText(TextView tv,String number,boolean rightDirect){
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
}
