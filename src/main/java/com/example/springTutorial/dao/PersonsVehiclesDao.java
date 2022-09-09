package com.example.springTutorial.dao;

import com.example.springTutorial.model.PersonVehicles;
import com.example.springTutorial.model.QRCodeDetails;

import java.util.Optional;

public interface PersonsVehiclesDao {
    int insertPerson(PersonVehicles personVehicles);

    Optional<PersonVehicles> selectPersonByMobileNumber(String mobileNumber);

    int deletePersonById(String mobileNumber);

    Optional<QRCodeDetails> getQRScannedWeeklyQuota(String mobileNumber);

    int adjustWeeklyBalanceQuota(int balanceUnit, String id);

    int getWeeklyBalanceQuota(String id);
}