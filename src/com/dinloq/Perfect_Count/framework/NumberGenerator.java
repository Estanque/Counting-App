package com.dinloq.Perfect_Count.framework;

import java.util.Random;

public class NumberGenerator {

	private static Random random = new Random();

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
}
