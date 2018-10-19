package com.nice.esp.dto;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Apple Inc.
 */

public class PlanParamsDto {

    private ParamDto contactsReceived;
    private ParamDto averageHandleTime;

    public PlanParamsDto(ParamDto contactsReceived, ParamDto averageHandleTime) {
        this.contactsReceived = contactsReceived;
        this.averageHandleTime = averageHandleTime;
    }

    public ParamDto getContactsReceived() {
        return contactsReceived;
    }

    public void setContactsReceived(ParamDto contactsReceived) {
        this.contactsReceived = contactsReceived;
    }

    public ParamDto getAverageHandleTime() {
        return averageHandleTime;
    }

    public void setAverageHandleTime(ParamDto averageHandleTime) {
        this.averageHandleTime = averageHandleTime;
    }
}
