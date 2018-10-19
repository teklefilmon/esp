package com.nice.esp.service;

import com.nice.esp.domain.DailyForecastStat;
import com.nice.esp.dto.PlanParamsList;
import com.nice.esp.dto.PlanParam;
import com.nice.esp.dto.PlanParamsResponse;
import com.nice.esp.service.calculations.functions.AverageHandleTime;
import com.nice.esp.service.calculations.functions.ContactsReceived;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

import static com.nice.esp.service.calculations.WfmCalculations.*;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

@Service
public class ParamsService {


    public PlanParamsResponse calculateDailyParams(List<DailyForecastStat> data) {
        List<PlanParam> dailyContactsReceivedEntries = calculateParamsDaily(data.stream(), ContactsReceived.calculateDaily);
        List<PlanParam> dailyAverageHandleTimeEntries = calculateParamsDaily(data.parallelStream(), AverageHandleTime.calculateDaily);

        PlanParamsList dailyContactsReceived = new PlanParamsList(PlanParamsList.Type.CONTACTS_RECEIVED, dailyContactsReceivedEntries);
        PlanParamsList dailyAverageHandleTime = new PlanParamsList(PlanParamsList.Type.AVERAGE_HANDLE_TIME, dailyAverageHandleTimeEntries);

        return new PlanParamsResponse(dailyContactsReceived, dailyAverageHandleTime);
    }

    public PlanParamsResponse calculateWeeklyParams(List<DailyForecastStat> data, DayOfWeek dayOfWeek) {
        List<PlanParam> weeklyContactsReceivedEntries = calculateParamsWeekly(data.parallelStream(), ContactsReceived.calculateWeeklyOrMonthly, dayOfWeek);
        List<PlanParam> weeklyAverageHandleTimeEntries = calculateParamsWeekly(data.parallelStream(), AverageHandleTime.calculateWeeklyOrMonthly, dayOfWeek);

        PlanParamsList weeklyContactsReceived = new PlanParamsList(PlanParamsList.Type.CONTACTS_RECEIVED, weeklyContactsReceivedEntries);
        PlanParamsList weeklyAverageHandleTime = new PlanParamsList(PlanParamsList.Type.AVERAGE_HANDLE_TIME, weeklyAverageHandleTimeEntries);

        return new PlanParamsResponse(weeklyContactsReceived, weeklyAverageHandleTime);
    }

    public PlanParamsResponse calculateMonthlyParams(List<DailyForecastStat> data) {
        List<PlanParam> monthlyContactsReceivedEntries = calculateParamsMonthly(data.parallelStream(), ContactsReceived.calculateWeeklyOrMonthly);
        List<PlanParam> monthlyAverageHandleTimeEntries = calculateParamsMonthly(data.parallelStream(), AverageHandleTime.calculateWeeklyOrMonthly);

        PlanParamsList monthlyContactsReceived = new PlanParamsList(PlanParamsList.Type.CONTACTS_RECEIVED, monthlyContactsReceivedEntries);
        PlanParamsList monthlyAverageHandleTime = new PlanParamsList(PlanParamsList.Type.AVERAGE_HANDLE_TIME, monthlyAverageHandleTimeEntries);

        return new PlanParamsResponse(monthlyContactsReceived, monthlyAverageHandleTime);
    }


}
