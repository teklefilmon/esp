package com.nice.esp.service;

import com.nice.esp.domain.DailyForecastStat;
import com.nice.esp.dto.ParamDto;
import com.nice.esp.dto.PlanParamEntry;
import com.nice.esp.dto.PlanParamsDto;
import com.nice.esp.service.calculations.impl.AverageHandleTime;
import com.nice.esp.service.calculations.impl.ContactsReceived;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

import static com.nice.esp.service.calculations.WfmCalculations.*;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Apple Inc.
 */

@Service
public class ParamsService {


    public PlanParamsDto calculateDailyParams(List<DailyForecastStat> data) {
        List<PlanParamEntry> dailyContactsReceivedEntries = calculateParamsDaily(data.stream(), ContactsReceived.calculateDaily);
        List<PlanParamEntry> dailyAverageHandleTimeEntries = calculateParamsDaily(data.parallelStream(), AverageHandleTime.calculateDaily);

        ParamDto dailyContactsReceived = new ParamDto(ParamDto.Type.CONTACTS_RECEIVED, dailyContactsReceivedEntries);
        ParamDto dailyAverageHandleTime = new ParamDto(ParamDto.Type.AVERAGE_HANDLE_TIME, dailyAverageHandleTimeEntries);

        return new PlanParamsDto(dailyContactsReceived, dailyAverageHandleTime);
    }

    public PlanParamsDto calculateWeeklyParams(List<DailyForecastStat> data, DayOfWeek dayOfWeek) {
        List<PlanParamEntry> weeklyContactsReceivedEntries = calculateParamsWeekly(data.parallelStream(), ContactsReceived.calculateWeeklyOrMonthly, dayOfWeek);
        List<PlanParamEntry> weeklyAverageHandleTimeEntries = calculateParamsWeekly(data.parallelStream(), AverageHandleTime.calculateWeeklyOrMonthly, dayOfWeek);

        ParamDto weeklyContactsReceived = new ParamDto(ParamDto.Type.CONTACTS_RECEIVED, weeklyContactsReceivedEntries);
        ParamDto weeklyAverageHandleTime = new ParamDto(ParamDto.Type.AVERAGE_HANDLE_TIME, weeklyAverageHandleTimeEntries);

        return new PlanParamsDto(weeklyContactsReceived, weeklyAverageHandleTime);
    }

    public PlanParamsDto calculateMonthlyParams(List<DailyForecastStat> data) {
        List<PlanParamEntry> monthlyContactsReceivedEntries = calculateParamsMonthly(data.parallelStream(), ContactsReceived.calculateWeeklyOrMonthly);
        List<PlanParamEntry> monthlyAverageHanldeTimeEntries = calculateParamsMonthly(data.parallelStream(), AverageHandleTime.calculateWeeklyOrMonthly);

        ParamDto monthlyContactsReceived = new ParamDto(ParamDto.Type.CONTACTS_RECEIVED, monthlyContactsReceivedEntries);
        ParamDto monthlyAverageHandleTime = new ParamDto(ParamDto.Type.AVERAGE_HANDLE_TIME, monthlyAverageHanldeTimeEntries);

        return new PlanParamsDto(monthlyContactsReceived, monthlyAverageHandleTime);
    }


}
