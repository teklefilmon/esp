package com.nice.esp.service.calculations.impl;

import com.nice.esp.domain.DailyForecastStat;
import com.nice.esp.dto.PlanParamEntry;
import com.nice.esp.service.calculations.WfmCalculations;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Apple Inc.
 */

public class ContactsReceived {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);


    public static Function<DailyForecastStat, PlanParamEntry> calculateDaily = dailyStat -> new PlanParamEntry(dailyStat.getDate().format(formatter), dailyStat.getContactsReceived().toString());

    public static Function<List<DailyForecastStat>, PlanParamEntry> calculateWeeklyOrMonthly =
            dailyStatList ->
                    new PlanParamEntry(WfmCalculations.getWeeklyOrMonthlyInterval(dailyStatList, formatter),
                            dailyStatList.stream().map(DailyForecastStat::getContactsReceived)
                                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO).toString());


}
