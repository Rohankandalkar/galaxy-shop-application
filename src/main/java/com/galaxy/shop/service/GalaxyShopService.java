package com.galaxy.shop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxy.shop.constants.GalaxyShopConstants;
import com.galaxy.shop.util.RomanNumeralsConverter;

/**
 * Service layer for business logic process transaction statements according to
 * conditions provided in initial stages.
 * 
 * @author kandalakar.r
 *
 */
public class GalaxyShopService {

	private Map<String, String> currencyDetailsMap;
	private Map<String, Double> metalDetailsMap;

	public GalaxyShopService() {

		this.currencyDetailsMap = new HashMap<String, String>();
		this.metalDetailsMap = new HashMap<String, Double>();
	}

	/**
	 * method traverse list of Transactions statements and call appropriate methods
	 * to process transactions according to given conditions
	 * 
	 * @param transactionDetailsList - input list of transaction statements
	 */
	public List<String> processTranding(List<String> transactionDetailsList) {

		List<String> transactionProcessDetails = new ArrayList<String>();

		transactionDetailsList.stream().forEach(transactionStatment -> {

			String[] transactionsArray = transactionStatment.split(" ");

			// identifying method according to statements parameters
			if (transactionsArray.length == 3 && transactionsArray[1].equals("is")) {
				currencyCalculationProcess(transactionsArray);
			} else if (transactionsArray.length == 6
					&& transactionsArray[5].equalsIgnoreCase(GalaxyShopConstants.CREDITS)) {
				metalDetailsCalculationProcess(transactionStatment);
			} else if (transactionsArray.length == 8) {
				String result = totalCreditCalculationDetails(transactionStatment);
				transactionProcessDetails.add(result);
			} else {
				transactionProcessDetails.add("I have no idea what you are talking about");
			}

		});
		return transactionProcessDetails;

	}

	/**
	 * method calculates total credits and returns processed output result
	 * 
	 * @param transactionStatment - Input parameter is transactionStatment
	 * @return result - Result of processed statement.
	 */
	private String totalCreditCalculationDetails(String transactionStatment) {

		String[] statments = transactionStatment.split(" ");

		String result = "";

		int start = Arrays.asList(statments).indexOf("is") + 1;
		String romanNumearalValue = "";
		double metalValue = 0;

		for (int i = start; i < 7; i++) {

			if (currencyDetailsMap.containsKey(statments[i])) {
				romanNumearalValue = romanNumearalValue + currencyDetailsMap.get(statments[i]);
			} else if (metalDetailsMap.containsKey(statments[i])) {
				metalValue = metalValue + metalDetailsMap.get(statments[i]);
			}

		}

		int romanToIntgerValue = 0;
		if (!romanNumearalValue.isEmpty()) {
			romanToIntgerValue = RomanNumeralsConverter.convertRomanNumeralsToInteger(romanNumearalValue);
		}

		int totalCredit = 0;
		if (romanToIntgerValue != 0 && metalValue != 0) {
			totalCredit = (int) (romanToIntgerValue * metalValue);

			result = statments[start] + " " + statments[start + 1] + " " + statments[start + 2] + " is " + totalCredit;
		} else if (romanToIntgerValue != 0 && metalValue == 0) {
			totalCredit = (int) (romanToIntgerValue);
			result = statments[start] + " " + statments[start + 1] + " " + statments[start + 2] + " "
					+ statments[start + 3] + " is " + totalCredit;
		}

		return result;

	}

	/**
	 * mapping of galaxy currency into Roman numeral according to specification
	 * given in input by user *
	 * 
	 * @param transactionsArray - array of statements which will process
	 */
	public void currencyCalculationProcess(String[] transactionsArray) {

		String galaxyCurrencyName = transactionsArray[0];
		String galaxyCurrencyRomanValueSymbol = transactionsArray[2];
		currencyDetailsMap.put(galaxyCurrencyName, galaxyCurrencyRomanValueSymbol);

	}

	/**
	 * calculates metals value according to specifications provide by user in
	 * transaction
	 * 
	 * @param transactionStatment - statement which consists data regarding metals
	 */
	private void metalDetailsCalculationProcess(String transactionStatment) {

		String[] transactionArray = transactionStatment.split(" ");
		String romanNumericSymbol = "";

		for (int i = 0; i < 2; i++) {
			romanNumericSymbol = romanNumericSymbol + currencyDetailsMap.get(transactionArray[i]);
		}

		int romanNumeralInteger = RomanNumeralsConverter.convertRomanNumeralsToInteger(romanNumericSymbol);

		metalDetailsMap.put(transactionArray[2], Double.parseDouble(transactionArray[4]) / romanNumeralInteger);

	}

}
