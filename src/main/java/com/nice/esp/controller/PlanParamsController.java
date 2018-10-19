package com.nice.esp.controller;

import com.nice.esp.domain.DailyForecastStat;
import com.nice.esp.domain.Interval;
import com.nice.esp.dto.PlanParamsResponse;
import com.nice.esp.service.ParamsService;
import com.nice.esp.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

@RestController
public class PlanParamsController {

    private PlanService planService;
    private ParamsService paramsService;

    public PlanParamsController(PlanService planService, ParamsService paramsService) {
        this.planService = planService;
        this.paramsService = paramsService;
    }

    @GetMapping("/params")
    public ResponseEntity<PlanParamsResponse> getParameters(@RequestParam("interval") Interval interval) {

        List<DailyForecastStat> dailyForecastStatsList = planService.getDailyForecastStats(LocalDate.now(), LocalDate.now().plusYears(5));

        if (Interval.DAY.equals(interval)) {
            return ResponseEntity.ok(paramsService.calculateDailyParams(dailyForecastStatsList));
        }
        if (Interval.MONTH.equals(interval)) {
            return ResponseEntity.ok(paramsService.calculateMonthlyParams(dailyForecastStatsList));
        }
        if (Objects.isNull(interval))
            return ResponseEntity.ok(paramsService.calculateWeeklyParams(dailyForecastStatsList, DayOfWeek.WEDNESDAY));
        throw new RuntimeException("Invalid interval");
    }
}
