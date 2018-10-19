package com.nice.esp.dto;

/**
 * Created on 10/18/18.
 * Author: filmon
 * Nice Systems Ltd.
 */

public class PlanParamsResponse
{

    private PlanParamsList contactsReceived;
    private PlanParamsList averageHandleTime;

    public PlanParamsResponse(PlanParamsList contactsReceived, PlanParamsList averageHandleTime) {
        this.contactsReceived = contactsReceived;
        this.averageHandleTime = averageHandleTime;
    }

    public PlanParamsList getContactsReceived() {
        return contactsReceived;
    }

    public void setContactsReceived(PlanParamsList contactsReceived) {
        this.contactsReceived = contactsReceived;
    }

    public PlanParamsList getAverageHandleTime() {
        return averageHandleTime;
    }

    public void setAverageHandleTime(PlanParamsList averageHandleTime) {
        this.averageHandleTime = averageHandleTime;
    }
}
