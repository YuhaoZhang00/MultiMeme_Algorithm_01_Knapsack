package com.yuhao.data;

import java.util.LinkedList;

public class Problem {
	private int m_numOfItems;
	private double m_knapsackCapacity;
	private LinkedList<Double> m_profits;
	private LinkedList<Double> m_weights;
	
	private double m_minProfit;

	public int getNumOfItems() {
		return m_numOfItems;
	}
	
	public double getKnapsackCapacity() {
		return m_knapsackCapacity;
	}
	
	public LinkedList<Double> getProfits() {
		return m_profits;
	}
	
	public double getProfit(int id) {
		return m_profits.get(id);
	}
	
	public LinkedList<Double> getWeights() {
		return m_weights;
	}
	
	public double getWeight(int id) {
		return m_weights.get(id);
	}
	
	public double getMinProfit() {
		return m_minProfit;
	}

	public Problem(int numOfItems, double knapsackCapacity, LinkedList<Double> profits, LinkedList<Double> weights) {
		m_numOfItems = numOfItems;
		m_knapsackCapacity = knapsackCapacity;
		m_profits = profits;
		m_weights = weights;
		
		m_minProfit = Double.MAX_VALUE;
		for (double weight : m_profits) {
			m_minProfit = Math.min(m_minProfit, weight);
		}
	}
}
