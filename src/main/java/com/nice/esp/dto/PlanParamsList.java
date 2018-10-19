package com.nice.esp.dto;

import lombok.Getter;

import java.util.List;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

@Getter
public class PlanParamsList
{
    private Type type;
    private List<PlanParam> entries;

    public PlanParamsList(Type type, List<PlanParam> entries) {
        this.type = type;
        this.entries = entries;
    }


    public Type getType() {
        return type;
    }

    public List<PlanParam> getEntries() {
        return entries;
    }

    public enum Type {
        CONTACTS_RECEIVED,
        AVERAGE_HANDLE_TIME
    }
}
