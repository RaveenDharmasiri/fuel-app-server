package com.example.springTutorial.dao;

import com.example.springTutorial.model.PersonVehicles;
import java.util.Optional;

public interface PersonsVehiclesDao {
    int insertPerson(PersonVehicles personVehicles);

    Optional<PersonVehicles> selectPersonByMobileNumber(String mobileNumber);

    int deletePersonById(String mobileNumber);

    Optional<PersonVehicles> getQRScannedWeeklyQuota(String mobileNumber);

    int adjustWeeklyBalanceQuota(int balanceUnit, String id);

    int getWeeklyBalanceQuota(String id);
}