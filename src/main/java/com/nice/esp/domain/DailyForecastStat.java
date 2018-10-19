package com.nice.esp.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Nice Systems Ltd.
 */


public class DailyForecastStat {

    private LocalDate date;
    private BigDecimal contactsReceived;
    private BigDecimal averageHandleTime;


    public DailyForecastStat(LocalDate date, BigDecimal contactsReceived, BigDecimal averageHandleTime) {
        this.date = date;
        this.contactsReceived = contactsReceived;
        this.averageHandleTime = averageHandleTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getContactsReceived() {
        return contactsReceived;
    }

    public BigDecimal getAverageHandleTime() {
        return averageHandleTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyForecastStat that = (DailyForecastStat) o;
        return Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate());
    }

    @Override
    public String toString() {
        return "{" + date +
                "}";
    }
}
