package com.nice.esp.tasks;

import com.nice.esp.domain.DailyForecastStat;
import com.nice.esp.dto.PlanParamEntry;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Apple Inc.
 */

public interface WfmCalculations {

    static List<PlanParamEntry> calculateParamsDaily(Stream<DailyForecastStat> data, Function<DailyForecastStat, PlanParamEntry> function) {
        return data.parallel().map(function).collect(Collectors.toList());
    }

    static List<PlanParamEntry> calculateParamsMonthly(Stream<DailyForecastStat> data, Function<List<DailyForecastStat>, PlanParamEntry> function) {
        Map<LocalDate, List<DailyForecastStat>> groupsByMonth =
                data.parallel()
                        .collect(Collectors.groupingBy(stat -> stat.getDate().with(firstDayOfNextMonth())));

        return new TreeMap<>(groupsByMonth).values().parallelStream()
                .map(function).collect(Collectors.toList());
    }

    static List<PlanParamEntry> calculateParamsWeekly(Stream<DailyForecastStat> data, Function<List<DailyForecastStat>, PlanParamEntry> function, DayOfWeek dayOfWeek) {
        Map<LocalDate, List<DailyForecastStat>> groupsByWeek =
                data.parallel()
                        .collect(Collectors.groupingBy(stat -> stat.getDate().with(previousOrSame(dayOfWeek))));
        return new TreeMap<>(groupsByWeek).values().parallelStream().map(function).collect(Collectors.toList());
    }

    static String getWeeklyOrMonthlyInterval(List<DailyForecastStat> dailyStatList, DateTimeFormatter formatter) {
        String lastDate = dailyStatList
                .stream()
                .reduce((first, second) -> second)
                .orElseThrow(RuntimeException::new)
                .getDate()
                .format(formatter);

        String firstDate = dailyStatList
                .stream()
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getDate()
                .format(formatter);

        return firstDate + " - " + lastDate;
    }


}
