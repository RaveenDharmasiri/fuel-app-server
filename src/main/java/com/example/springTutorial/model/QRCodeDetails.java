package com.example.springTutorial.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QRCodeDetails {
    private String id;
    private int eligibleWeekQuota;
    private int eligibleWeekBalance;

    public QRCodeDetails(String id,
                         int eligibleWeekQuota,
                         int eligibleWeekBalance) {
        this.id = id;
        this.eligibleWeekQuota = eligibleWeekQuota;
        this.eligibleWeekBalance = eligibleWeekBalance;
    }

    public String getId() {
        return this.id;
    }

    public int getEligibleWeekQuota() {
        return this.eligibleWeekQuota;
    }

    public int getEligibleWeekBalance() {
        return this.eligibleWeekBalance;
    }
}
