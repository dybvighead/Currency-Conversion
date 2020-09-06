package com.benjamin.currency_converter;

import java.net.URL;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class Model {

	String currencyFrom;
	double currencyFromAmount;
	String currencyTo;

	public Model(List<String> input) {
		try {
			this.currencyFrom = input.get(0).replaceAll("[0-9]", "").toUpperCase();
			this.currencyFromAmount = Double.parseDouble(input.get(0).replaceAll("[A-Za-z]", ""));
			this.currencyTo = input.get(1).toUpperCase();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input!");
			System.exit(0);
		}
	}

	public List<Element> readExchangeRates() {

		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml\n");
			Document doc = saxBuilder.build(url);
			Namespace ns = Namespace.getNamespace("http://www.ecb.int/vocabulary/2002-08-01/eurofxref");

			Element rootElement = doc.getRootElement();
			Element cube1 = rootElement.getChild("Cube", ns);
			Element cube2 = cube1.getChild("Cube", ns);
			List<Element> currencyList = cube2.getChildren("Cube", ns);
			return currencyList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String convert() {

		double rateFrom = 0;
		double rateTo = 0;
		double currencyConvertedAmount = 0;

		List<Element> currencyList = readExchangeRates();
		for (Element e : currencyList) {
			if (e.getAttributeValue("currency").equals(currencyFrom)) {
				rateFrom = Double.parseDouble(e.getAttributeValue("rate"));
			}
			if (e.getAttributeValue("currency").equals(currencyTo)) {
				rateTo = Double.parseDouble(e.getAttributeValue("rate"));
			}
		}

		currencyConvertedAmount = rateTo / rateFrom * this.currencyFromAmount;

		// Rounding
		currencyConvertedAmount = Math.round(currencyConvertedAmount * 100) / 100;
		currencyFromAmount = Math.round(currencyFromAmount * 100) / 100;

		return Double.toString(currencyFromAmount) + this.currencyFrom + " = "
				+ Double.toString(currencyConvertedAmount) + this.currencyTo;
	}

}
