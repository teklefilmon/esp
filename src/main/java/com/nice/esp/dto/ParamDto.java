package com.nice.esp.dto;

import lombok.Getter;

import java.util.List;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Apple Inc.
 */

@Getter
public class ParamDto {
    private Type type;
    private List<PlanParamEntry> entries;

    public ParamDto(Type type, List<PlanParamEntry> entries) {
        this.type = type;
        this.entries = entries;
    }


    public Type getType() {
        return type;
    }

    public List<PlanParamEntry> getEntries() {
        return entries;
    }

    public enum Type {
        CONTACTS_RECEIVED,
        AVERAGE_HANDLE_TIME
    }
}
