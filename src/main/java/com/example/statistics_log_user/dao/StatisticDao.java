package com.example.statistics_log_user.dao;

import com.example.statistics_log_user.dto.StatisticDto;
import com.example.statistics_log_user.mapper.StatisticMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class StatisticDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<StatisticDto> findByPeriod(String fromDate, String toDate, String userName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fromDate", fromDate, Types.TIMESTAMP);
        params.addValue("toDate", toDate, Types.TIMESTAMP);
        params.addValue("userName", userName, Types.VARCHAR);
        return namedParameterJdbcTemplate.query(getQuery(), params, new StatisticMapper.StatisticInfoMapper());
    }

    private String getQuery() {
        return "select count(count_user), user_name from statistic where date_session between :fromDate and :toDate and user_name = :userName groupBy user_name";
    }

}
