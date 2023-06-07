package com.fdmgroup.varCalculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValueAtRiskCalculatorTest {
	
	private ValueAtRiskCalculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new ValueAtRiskCalculator();
	}

	@Test
	void test_singleTradeValueAtRiskCalculationReturnsCorrectValue() {
		//set up trade, returns and confidence level
		List<Double> returns = new ArrayList<>(Arrays.asList(0.1, -0.2, 0.05, -0.15));
		Trade trade = new Trade(returns, 0.4);
		
		double confidenceLevel = 0.99;
		double totalInvestment = 100000;
	
		double expectedValueAtRisk = 15000;
		double actualValueAtRisk = calculator.calculateVarForSingleTrade(trade, confidenceLevel, totalInvestment);
		
		assertEquals(expectedValueAtRisk, actualValueAtRisk);
	}

	
	@Test
	void test_portfolioValueAtRiskCalculationReturnsCorrectValueThreeTrades() {
	    // Create a list of trades with sample returns and weights
	    List<Trade> trades = new ArrayList<>();
	    
	    List<Double> returns1 = new ArrayList<>(Arrays.asList(0.02, 0.03, -0.01, -0.04, 0.06));
	    double weight1 = 0.4;
	    Trade trade1 = new Trade(returns1, weight1);

	    List<Double> returns2 = new ArrayList<>(Arrays.asList(-0.03, 0.01, -0.02, -0.05, 0.04));
	    double weight2 = 0.3;
	    Trade trade2 = new Trade(returns2, weight2);

//	    List<Double> returns3 = new ArrayList<>(Arrays.asList(0.04, 0.05, -0.01, -0.03, 0.07));
//	    double weight3 = 0.3;
//	    Trade trade3 = new Trade(returns3, weight3);
	    
	    trades.add(trade1);
	    trades.add(trade2);
//	    trades.add(trade3);

	    double confidenceLevel = 0.95;
	    double totalInvestment = 1000000;

	    double expectedVar = 13000.0; // Expected VaR for the given sample data and confidence level

	    // Test the method
	    double var = calculator.calculateVarForPortfolio(trades, confidenceLevel, totalInvestment);

	    // Verify the result
	    assertEquals(expectedVar, var);
	}
}
