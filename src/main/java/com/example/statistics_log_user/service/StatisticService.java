package com.example.statistics_log_user.service;

import com.example.statistics_log_user.dto.StatisticDto;

import java.util.List;

public interface StatisticService {

    List<StatisticDto> findStatisticByPeriod(String fromDate, String toDate, String userName);
}
