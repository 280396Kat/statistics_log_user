package com.example.statistics_log_user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticDto {

    private String name;

    private Integer countSession;
}
