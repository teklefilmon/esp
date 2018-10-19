package com.nice.esp.service.calculations.functions;

import com.nice.esp.domain.DailyForecastStat;
import com.nice.esp.dto.PlanParam;
import com.nice.esp.service.calculations.WfmCalculations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

public class AverageHandleTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);


    public static Function<DailyForecastStat, PlanParam> calculateDaily = dailyStat -> new PlanParam(dailyStat.getDate().format(formatter), dailyStat.getAverageHandleTime().toString());

    public static Function<List<DailyForecastStat>, PlanParam> calculateWeeklyOrMonthly =
            dailyStatList -> {
                BigDecimal contactsReceived =
                        dailyStatList.stream()
                                .map(stat -> stat.getContactsReceived().add(stat.getAverageHandleTime()))
                                .reduce(BigDecimal::add).orElseThrow(RuntimeException::new);
                BigDecimal averageHandleTime = dailyStatList.stream().map(DailyForecastStat::getContactsReceived)
                        .reduce(BigDecimal::add).orElseThrow(RuntimeException::new);

                return new PlanParam(WfmCalculations.getWeeklyOrMonthlyInterval(dailyStatList, formatter),
                        contactsReceived.divide(averageHandleTime, 2, RoundingMode.HALF_UP).toString());
            };
}
