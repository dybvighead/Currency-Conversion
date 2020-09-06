package com.benjamin.currency_converter;

import java.util.List;

public class Controller {

	View view;
	Model model;

	public Controller() {
		this.view = new View();
	}

	public void convertCurrency() {

		List<String> input = view.getInput();
		model = new Model(input);
		String result = model.convert();
		view.display(result);

	}

}
