package com.benjamin.currency_converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.mockito.MockitoAnnotations;

import com.benjamin.currency_converter.Controller;
import com.benjamin.currency_converter.Model;
import com.benjamin.currency_converter.View;

class CurrencyConverterAppTest {

	Controller controller;

	View view;

	Model model;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		controller = new Controller();
		view = new View();
	}

	@Test
	void test_GetInput_ReturnsListContainingStringOneHundredUSDAndJPY_WhenStringOneHundredUSDAndJPYIsCalledIn() {
		List<String> expected = Arrays.asList("100USD", "JPY");
		assertEquals(expected, view.getInput());
	}

	@Test
	void test_Convert_ReturnsStringOneHundredUSDAndJPY_WhenStringOneHundredUSDAndJPYIsCalledIn() {
		List<String> list = Arrays.asList("100USD", "JPY");
		model = new Model(list);
		String expected = "100.0USD = 10653.0JPY";
		assertEquals(expected, model.convert());
	}

}
