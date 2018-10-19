package com.nice.esp.service;

import com.nice.esp.domain.DailyForecastStat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Apple Inc.
 */

@Service
public class PlanService {

    public List<DailyForecastStat> getDailyForecastStats(LocalDate start, LocalDate end) {
        return Stream
                .iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end))
                .map(date -> new DailyForecastStat(date, new BigDecimal(date.getDayOfMonth()), new BigDecimal(date.getDayOfYear())))
                .collect(Collectors.toList());
    }
}
