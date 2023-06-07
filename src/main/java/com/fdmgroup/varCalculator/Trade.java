package com.fdmgroup.varCalculator;

import java.util.List;

public class Trade {
	private List<Double> returns;
	private double weight;
	
	public Trade(List<Double> returns, double weight) {
		super();
		this.returns = returns;
		this.weight = weight;
	}

	public List<Double> getReturns() {
		return returns;
	}

	public void setReturns(List<Double> returns) {
		this.returns = returns;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
