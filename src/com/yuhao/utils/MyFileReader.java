package com.yuhao.utils;

import com.yuhao.config.Constant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class MyFileReader {
	/**
	 * Returns a list of informations from the file
	 * 
	 * @return a 2-d linked list {@code returnValue} where {@code returnValue[0][0]}
	 *         is numberOfItems, {@code returnValue[1][0]} is knapsackCapacity,
	 *         {@code returnValue[2]} is profits, {@code returnValue[3]} is weights,
	 * @throws IOException
	 */
	public LinkedList<LinkedList<Double>> readFromFile() throws IOException {
		File file = new File(Constant.FILE_TO_READ);
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = br.readLine();
		String[] lineComponent = line.split(" ");

		LinkedList<Double> numberOfItems = new LinkedList<>();
		numberOfItems.add(Double.parseDouble(lineComponent[0]));
		LinkedList<Double> knapsackCapacity = new LinkedList<>();
		knapsackCapacity.add(Double.parseDouble(lineComponent[1]));

		LinkedList<Double> profits = new LinkedList<>();
		LinkedList<Double> weights = new LinkedList<>();
		while ((line = br.readLine()) != null) {
			lineComponent = line.split(" ");
			profits.add(Double.parseDouble(lineComponent[0]));
			weights.add(Double.parseDouble(lineComponent[1]));
		}
		br.close();

		LinkedList<LinkedList<Double>> returnValue = new LinkedList<>();
		returnValue.add(numberOfItems);
		returnValue.add(knapsackCapacity);
		returnValue.add(profits);
		returnValue.add(weights);
		return returnValue;
	}
}