package com.yuhao.data;

import java.util.LinkedList;

public class Problem {
	private int m_numOfItems;
	private int m_knapsackCapacity;
	private LinkedList<Integer> m_profits;
	private LinkedList<Integer> m_weights;
	
	private int m_minProfit;

	public int getNumOfItems() {
		return m_numOfItems;
	}
	
	public int getKnapsackCapacity() {
		return m_knapsackCapacity;
	}
	
	public LinkedList<Integer> getProfits() {
		return m_profits;
	}
	
	public int getProfit(int id) {
		return m_profits.get(id);
	}
	
	public LinkedList<Integer> getWeights() {
		return m_weights;
	}
	
	public int getWeight(int id) {
		return m_weights.get(id);
	}
	
	public int getMinProfit() {
		return m_minProfit;
	}

	public Problem(int numOfItems, int knapsackCapacity, LinkedList<Integer> list, LinkedList<Integer> list2) {
		m_numOfItems = numOfItems;
		m_knapsackCapacity = knapsackCapacity;
		m_profits = list;
		m_weights = list2;
		
		m_minProfit = Integer.MAX_VALUE;
		for (Integer weight : m_profits) {
			m_minProfit = Math.min(m_minProfit, weight);
		}
	}
}
