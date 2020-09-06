package com.benjamin.currency_converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

	List<String> input;
	Scanner scanner;

	public List<String> getInput() {

		scanner = new Scanner(System.in);
		input = new ArrayList<>();
		System.out.println("Please input amount to convert and currency (eg. 100USD): ");
		System.out.print("> ");
		input.add(scanner.nextLine());

		System.out.println("Please input which currency to convert into (eg. JPY): ");
		System.out.print("> ");
		input.add(scanner.nextLine());

		return input;
	}

	public void display(String string) {
		System.out.println(string);
	}

}
