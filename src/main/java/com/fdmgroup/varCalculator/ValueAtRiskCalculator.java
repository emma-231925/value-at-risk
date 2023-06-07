package com.fdmgroup.varCalculator;

import java.util.*;

public class ValueAtRiskCalculator {
	
	//single trade
	public double calculateVarForSingleTrade(Trade trade, double confidenceLevel, double totalInvestment) {
		//create a list of returns from the trade
		List<Double> historicalReturns = trade.getReturns();
		
		//sort by worst return
		Collections.sort(historicalReturns);

		//find the correct index for finding the return value we need e.g. 1 - confidenceLevel
		//Math.ceil to round up
		double index = Math.ceil((1 - confidenceLevel) * historicalReturns.size());
		
		//calculate value at risk -1 returns a positive value for the amount at risk
		double valueAtRisk = -1 * historicalReturns.get((int) index) * totalInvestment;
		long roundedValue = Math.round((valueAtRisk*100) / 100);
		
		System.out.println("Single Trade Value at Risk: $" + roundedValue);
		return roundedValue;
	}
	
	//portfolio of trades
	public double calculateVarForPortfolio(List<Trade> listOfTrades, double confidenceLevel, double totalInvestment) {
		//weightedReturns will contain lists of weighted returns from each trade
		List<List<Double>> weightedReturns = new ArrayList<>();
		
		//loop through trades and find weighted returns
		for (Trade trade : listOfTrades) {
			//create list of returns
			List<Double> listOfReturns = trade.getReturns();
			double weight = trade.getWeight();
			System.out.println(weight);
			//loop through list of returns for each trade and add the weighted returns to a new list
			List<Double> tradeWeightedReturns = new ArrayList<>();
			for (Double historicalReturn : listOfReturns) {
				tradeWeightedReturns.add(historicalReturn * weight);
			}
			weightedReturns.add(tradeWeightedReturns);
			System.out.println(weightedReturns);
		}
		
		//sum the weighted returns for each time period
		int numberOfTimePeriods = weightedReturns.get(0).size();
		List<Double> sumWeightedReturns = new ArrayList<>();
		
		//loop for number of time periods
		double summedReturn;
		
		for(int i = 0; i < numberOfTimePeriods; i++) {
			summedReturn = 0;
			//for each list in weighted returns, get the index and add to sum
	        for (List<Double> returns : weightedReturns) {
	        	//i related to the time period from the outer loop
	            summedReturn += returns.get(i);
	        }
	        sumWeightedReturns.add(summedReturn);
		}
		
		//sort by worst weighted return
		Collections.sort(sumWeightedReturns);
		System.out.println(sumWeightedReturns);

		//matching logic to single trade VaR
		double index = Math.ceil((1 - confidenceLevel) * sumWeightedReturns.size());
				
		double valueAtRisk = -1 * sumWeightedReturns.get((int) index) * totalInvestment;
		long roundedValue = Math.round((valueAtRisk*100) / 100);
				
		System.out.println("Portfolio Value at Risk: $" + roundedValue);
		return roundedValue;
		

	}
}
