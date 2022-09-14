package com.example.springTutorial.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PersonVehicles {
    private String id;
    private String idType;
    private int mobileNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String vehicleType;
    private String vehicleNumber;
    private String chassisNumber;
    private int eligibleWeekQuota;
    private int eligibleWeekBalance;
    private Date joinDate;

    public PersonVehicles(@JsonProperty(value = "id") String id,
                          @JsonProperty(value = "idType") String idType,
                          @JsonProperty(value = "mobileNumber") int mobileNumber,
                          @JsonProperty(value = "firstName") String firstName,
                          @JsonProperty(value = "lastName") String lastName,
                          @JsonProperty(value = "address") String address,
                          @JsonProperty(value = "vehicleType") String vehicleType,
                          @JsonProperty(value = "vehicleNumber") String vehicleNumber,
                          @JsonProperty(value = "chassisNumber") String chassisNumber,
                          @JsonProperty(value = "eligibleWeekQuota") int eligibleWeekQuota,
                          @JsonProperty(value = "eligibleWeekBalance") int eligibleWeekBalance,
                          @JsonProperty(value = "joinDate") Date joinDate) {
        this.id = id;
        this.idType = idType;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.chassisNumber = chassisNumber;
        this.eligibleWeekQuota = eligibleWeekQuota;
        this.eligibleWeekBalance = eligibleWeekBalance;
        this.joinDate = joinDate;
    }

    public String getId() {
        return id;
    }
    public String getIdType() {
        return idType;
    }
    public int getMobileNumber() {
        return mobileNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public String getChassisNumber() {
        return chassisNumber;
    }
    public int getEligibleWeekQuota() {
        return eligibleWeekQuota;
    }
    public int getEligibleWeekBalance() {
        return eligibleWeekBalance;
    }
    public Date getJoinDate() {
        return joinDate;
    }
}
