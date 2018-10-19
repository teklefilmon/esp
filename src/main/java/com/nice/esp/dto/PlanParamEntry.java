package com.nice.esp.dto;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Apple Inc.
 */

public class PlanParamEntry {

    private String date;
    private String value;

    public PlanParamEntry(String date, String value) {
        this.date = date;
        this.value = value;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return date + " - " +
                value;
    }
}
