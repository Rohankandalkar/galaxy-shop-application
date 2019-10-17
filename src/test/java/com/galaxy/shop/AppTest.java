package com.galaxy.shop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.galaxy.shop.service.GalaxyShopService;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void processTrandingTest() {

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

		List<String> expectedOutPutList = new ArrayList<String>();
		inputList.add("pish tegj groot groot is 42");
		inputList.add("groot kree Silver is 68 Credits");
		inputList.add("groot kree Gold is 57800 Credits");
		inputList.add("groot kree Iron is 782 Credits");
		inputList.add("I have no idea what you are talking about");

		GalaxyShopService galaxyShopService = new GalaxyShopService();

		List<String> outPutList = galaxyShopService.processTranding(inputList);

		assert (outPutList.containsAll(expectedOutPutList));

	}

}
