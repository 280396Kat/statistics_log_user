package com.example.statistics_log_user.service;

import com.example.statistics_log_user.dao.StatisticDao;
import com.example.statistics_log_user.dto.StatisticDto;
import com.example.statistics_log_user.entity.Statistic;
import com.example.statistics_log_user.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    private final StatisticDao statisticDao;

    @Override
    public List<StatisticDto> findStatisticByPeriod(String fromDate, String toDate, String userName) {
        if (isValidDate(fromDate) && isValidDate(toDate)) {
            return statisticDao.findByPeriod(fromDate, toDate, userName);
        }
        return new ArrayList<>();
    }

    private boolean isValidDate(String date) {
        if (date != null || !date.equals("")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            try {
                simpleDateFormat.parse(date);
                return true;
            } catch (ParseException parseException) {
                throw new IllegalArgumentException("Дата должна быть формата dd.MM.yyyy");
            }
        }
        return false;
    }

    @KafkaListener(topics = "statistic")
    public void receiveStatistic(StatisticDto statisticDto) {
        Statistic statistic = new Statistic();
        statistic.setUserName(statisticDto.getName());
        statistic.setDateSession(LocalDateTime.now());
        statisticRepository.save(statistic);
    }
}
