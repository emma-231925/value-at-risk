package com.fdmgroup.varCalculator;

import java.util.*;

public class Runner {

	public static void main(String[] args) {
		ValueAtRiskCalculator calculator = new ValueAtRiskCalculator();
		
		List<Double> returns1 = new ArrayList<>(Arrays.asList(0.02, 0.03, -0.01, -0.04, 0.06));
		List<Double> returns2 = new ArrayList<>(Arrays.asList(-0.03, 0.01, -0.02, -0.05, 0.04));
//		List<Double> returns3 = new ArrayList<>(Arrays.asList(0.04, 0.05, -0.01, -0.03, 0.07));
		
		Trade trade1 = new Trade(returns1, 0.4);
		Trade trade2 = new Trade(returns2, 0.3);
//		Trade trade3 = new Trade(returns3, 0.3);
		
		calculator.calculateVarForSingleTrade(trade1, 0.95, 500000);
		
		List<Trade> listOfTrades = new ArrayList<>();
		listOfTrades.add(trade1);
		listOfTrades.add(trade2);
//		listOfTrades.add(trade3);
		
		calculator.calculateVarForPortfolio(listOfTrades, 0.95, 1000000);
	}
}
