package com.galaxy.shop;

import java.util.ArrayList;
import java.util.List;

import com.galaxy.shop.service.GalaxyShopService;

/**
 * 
 * Application to process transactions statements provided by user this app will
 * use for processing transactions and show its calculated data.
 * 
 * @author kandalakar.r
 *
 */
public class GalaxyShopApp {

	public static void main(String[] args) {

		// list used to test business logic.This list is dummy input we can use as
		// input provided from user
		// we can read this input from local file also
		List<String> inputList = new ArrayList<String>();

		inputList.add("groot is I");
		inputList.add("kree is V");
		inputList.add("pish is X");
		inputList.add("tegj is L");
		inputList.add("groot groot Silver is 34 Credits");
		inputList.add("groot kree Gold is 57800 Credits");
		inputList.add("pish pish Iron is 3910 Credits");
		inputList.add("how much is pish tegj groot groot ?");
		inputList.add("how many Credits is groot kree Silver ?");
		inputList.add("how many Credits is groot kree Gold ?");
		inputList.add("how many Credits is groot kree Iron ?");
		inputList.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		GalaxyShopService galaxyShopService = new GalaxyShopService();
		List<String> processedResult = galaxyShopService.processTranding(inputList);
		processedResult.forEach(System.out::println);

	}
}
