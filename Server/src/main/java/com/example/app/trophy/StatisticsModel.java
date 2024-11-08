package com.example.app.trophy;

import java.util.LinkedHashMap;

public class StatisticsModel {

	LinkedHashMap<String, Double> statistic;
	
	public StatisticsModel()
	{
		super();
	}

	public StatisticsModel(LinkedHashMap<String,Double> statistic) {
		super();
		this.statistic = statistic;
	}

	public LinkedHashMap<String,Double> getStatistic() {
		return statistic;
	}

	public void setStatistic(LinkedHashMap<String,Double> statistic) {
		this.statistic = statistic;
	}
	
}
