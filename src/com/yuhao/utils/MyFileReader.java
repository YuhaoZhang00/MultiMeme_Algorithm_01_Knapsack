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
	public LinkedList<LinkedList<Integer>> readFromFile() throws IOException {
		File file = new File(Constant.FILE_TO_READ);
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = br.readLine();
		String[] lineComponent = line.split(" ");

		LinkedList<Integer> numberOfItems = new LinkedList<Integer>();
		numberOfItems.add(Integer.parseInt(lineComponent[0]));
		LinkedList<Integer> knapsackCapacity = new LinkedList<Integer>();
		knapsackCapacity.add(Integer.parseInt(lineComponent[1]));

		LinkedList<Integer> profits = new LinkedList<Integer>();
		LinkedList<Integer> weights = new LinkedList<Integer>();
		while ((line = br.readLine()) != null) {
			lineComponent = line.split(" ");
			profits.add(Integer.parseInt(lineComponent[0]));
			weights.add(Integer.parseInt(lineComponent[1]));
		}
		br.close();

		LinkedList<LinkedList<Integer>> returnValue = new LinkedList<LinkedList<Integer>>();
		returnValue.add(numberOfItems);
		returnValue.add(knapsackCapacity);
		returnValue.add(profits);
		returnValue.add(weights);
		return returnValue;
	}

	// TODO
	public void writeToFile() throws IOException {
		File file = new File(Constant.FILE_TO_WRITE);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write("aaa");
		bw.newLine();
		bw.close();
	}

//    public static void main(String[] args) {
//    	LinkedList<LinkedList<Integer>> returnValue = new LinkedList<LinkedList<Integer>>();
//    	
//    	MyFileReader aa = new MyFileReader();
//    	try {
//    		returnValue = aa.readFromFile();
//    	} catch (IOException e) {
//    		System.out.println(e);
//    	}
//    	
//    	for (int i = 0; i < returnValue.size(); i++) {
//    		for (int j = 0; j < returnValue.get(i).size(); j++) {
//    			System.out.print(returnValue.get(i).get(j) + "  ");
//    		}
//    		System.out.println(";");
//    	}
//	}
}