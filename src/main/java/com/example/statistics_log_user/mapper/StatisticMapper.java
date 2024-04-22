package com.example.statistics_log_user.mapper;

import com.example.statistics_log_user.dto.StatisticDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticMapper {
    public static class StatisticInfoMapper implements RowMapper<StatisticDto> {
        @Override
        public StatisticDto mapRow(ResultSet rs, int i) throws SQLException {
            return StatisticDto.builder()
                    .countSession(rs.getInt("count_user"))
                    .name(rs.getString("user_name"))
                    .build();
        }
    }
}


