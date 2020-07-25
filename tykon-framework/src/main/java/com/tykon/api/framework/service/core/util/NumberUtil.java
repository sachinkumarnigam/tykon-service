package com.tykon.api.framework.service.core.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class NumberUtil {

	private static DecimalFormat df = new DecimalFormat("###.##");

	public static double roundToTwoDigits(double number) {
		return Double.parseDouble(df.format(number));
	}

	// Function to Generate Five Digits Random Number for MDC
	public static int genrateRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}
	// End Here

	// Function to Generate Random Number for Mongodb
	public static String genrateMongodbUid() {
		long ms = System.currentTimeMillis();
		long randomPIN = (long) (Math.random() * 900000) + 100000;
		String PINString = String.valueOf(randomPIN);
		String randNum = ms + "_" + PINString;
		return randNum;
	}
	// End Here
	// split a large list based on size
	public static <T> Collection<List<T>> partitionBasedOnSize(List<T> inputList, int size) {
		final AtomicInteger counter = new AtomicInteger(0);
		return inputList.stream().collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size)).values();
	}

	public static void main(String[] args) {
		List<Integer> stringList = new ArrayList<>();
		stringList.add(0);
		stringList.add(1);
		stringList.add(2);
		stringList.add(3);
		stringList.add(4);
		stringList.add(5);
		stringList.add(6);
		stringList.add(7);
		stringList.add(8);
		stringList.add(9);

		Collection<List<Integer>> yy = partitionBasedOnSize(stringList, 4);

			long start = System.currentTimeMillis();
			

	}
}
