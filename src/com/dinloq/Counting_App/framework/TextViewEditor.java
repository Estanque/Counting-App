package com.dinloq.Counting_App.framework;

import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 10.06.13
 * Time: 9:50
 * To change this template use File | Settings | File Templates.
 */
public class TextViewEditor {

	public static void addText(TextView tv,int number,boolean rightDirect){

		if (rightDirect) {
			tv.setText(tv.getText() + String.valueOf(number));
		} else {
			tv.setText(String.valueOf(number) + tv.getText());
		}
	}
}
