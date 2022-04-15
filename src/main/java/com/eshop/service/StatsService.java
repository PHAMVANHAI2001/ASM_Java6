package com.eshop.service;

public interface StatsService {
	Double getTotalPricePerMonth(String month, String year);
	String[][] getTotalPriceLast12Months();
}
