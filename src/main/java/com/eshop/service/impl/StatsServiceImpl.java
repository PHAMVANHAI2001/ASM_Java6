package com.eshop.service.impl;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.jpaRepository.StatsRepository;
import com.eshop.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {
	@Autowired
	StatsRepository statsRepo;

	@Override
	public Double getTotalPricePerMonth(String month, String year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getTotalPriceLast12Months() {
		String[][] result = new String[2][12];
		YearMonth currentTimes = YearMonth.now();
		for (int i = 0; i < 12; i++) {
			String month = currentTimes.minusMonths(i).getMonthValue() + "";
			String year = currentTimes.minusMonths(i).getYear() + "";
			result[0][11 - i] = month + "/" + year;
			result[1][11 - i] =  statsRepo.getTotalPricePerMonth(month, year);
		}
		return result;
	}

}
