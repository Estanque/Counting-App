package com.dinloq.Perfect_Count.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.Toast;
import com.dinloq.Perfect_Count.MainActivity;

import java.util.ArrayList;
import java.util.Random;

public class Helper {

	private static Random random = new Random();

	// 0 - 10, 1 - 100, 2 - 1000, 3 - 10000
	public static int getRandomNumber(int num){
		int pow = 10;
		for (int i = 0; i < num; i++) {
			pow *= 10;
		}
		//int result = (pow * 10) - 1 - pow;
		int result = random.nextInt((pow * 10) - 1 - pow) + pow;
		return result;
	}

	//TODO make different random numbers (10,100,1000,10000) is settings
	public static int getRandomNumber(){
		int result = random.nextInt(8999) + 1000;
		return result;
	}

	//TODO Различные операции(Умножение вычитание деление)
	public static int getAnswer(int firstNum, int secondNum, int operation){
		switch (operation){
			case 0:
				return firstNum+secondNum;
			case 1:
				return firstNum*secondNum;
			//case 2:
			//	return Math.abs(firstNum-secondNum);
		}
		return 0;
	}

	public static float round(float number, int scale){
		int pow = 10;
		for (int i = 1; i < scale; i++)
			pow *= 10;
		float tmp = number * pow;
		return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
	}

	public static float round(int right, int wrong, int scale) {
		if (right + wrong == 0) {
			return 0;
		}
		float sum = (float) right / (right + wrong) * 100;
		return round(sum, scale);
	}

	public static float getAvg(ArrayList<Float> list){
		float avgTimer = 0;
		if (!list.isEmpty()) {
			float sum = 0;
			for (float t : list){
				sum += t;
			}
			avgTimer = Helper.round(sum / list.size(), MainActivity.scaleToRound);
		}
		return avgTimer;
	}

	public static void showToast(String message, Context context){
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, 0);
		toast.show();
	}

	public static int getRange(SharedPreferences sp, int operation) {
		switch (operation){
			case 0:
				return sp.getInt("set_add_range", 0);
			case 1:
				return sp.getInt("set_multi_range", 0);
			case 2:
				return sp.getInt("set_sub_range", 0);
		}
		return -1;
	}
}
