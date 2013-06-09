package com.dinloq.Counting_App.framework;

import java.util.Random;

public class Randomizer {

	private static Random random = new Random();

	public static int getRandomNumber(){
		int result = random.nextInt(8999) + 1000;
		return result;
	}
}
