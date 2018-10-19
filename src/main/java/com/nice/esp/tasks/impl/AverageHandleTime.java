package com.nice.esp.tasks.impl;

import com.nice.esp.domain.DailyForecastStat;
import com.nice.esp.dto.PlanParamEntry;
import com.nice.esp.tasks.WfmCalculations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Apple Inc.
 */

public class AverageHandleTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);


    public static Function<DailyForecastStat, PlanParamEntry> calculateDaily = dailyStat -> new PlanParamEntry(dailyStat.getDate().format(formatter), dailyStat.getAverageHandleTime().toString());

    public static Function<List<DailyForecastStat>, PlanParamEntry> calculateWeeklyOrMonthly =
            dailyStatList -> {
                BigDecimal contactsReceived =
                        dailyStatList.stream()
                                .map(stat -> stat.getContactsReceived().add(stat.getAverageHandleTime()))
                                .reduce(BigDecimal::add).orElseThrow(RuntimeException::new);
                BigDecimal averageHandleTime = dailyStatList.stream().map(DailyForecastStat::getContactsReceived)
                        .reduce(BigDecimal::add).orElseThrow(RuntimeException::new);

                return new PlanParamEntry(WfmCalculations.getWeeklyOrMonthlyInterval(dailyStatList, formatter),
                        contactsReceived.divide(averageHandleTime, 2, RoundingMode.HALF_UP).toString());
            };
}
