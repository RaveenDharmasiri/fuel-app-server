package com.example.springTutorial.service;

import com.example.springTutorial.model.PersonVehicles;
import com.example.springTutorial.model.QRCodeDetails;

import java.util.Optional;

public interface PersonVehicleService {
    String addPerson(PersonVehicles personVehicles);
    Optional<PersonVehicles> getPersonByMobileNumber(String mobileNumber);
    int deletePerson(String mobileNumber);
    Optional<QRCodeDetails> getQRCodeDetails(String mobileNumber);
    String adjustWeeklyBalanceQuota(String consumedUnit, String id);
}
