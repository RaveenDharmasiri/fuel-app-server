package com.example.springTutorial.service;

import com.example.springTutorial.dao.PersonsVehiclesDao;
import com.example.springTutorial.model.PersonVehicles;
import com.example.springTutorial.qrCodeGenerator.QRController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonVehicleService {

    private final PersonsVehiclesDao personsVehiclesDao;

    @Autowired
    public PersonVehicleService(@Qualifier("mysql") PersonsVehiclesDao personsVehiclesDao) {
        this.personsVehiclesDao = personsVehiclesDao;
    }

    public String addPerson(PersonVehicles personVehicles){
        if (personsVehiclesDao.insertPerson(personVehicles) == 1){
            return new QRController().getQRCode(String.valueOf(personVehicles.getMobileNumber()));
        }else {
            return "register error";
        }
    }

    public Optional<PersonVehicles> getPersonByMobileNumber(String mobileNumber) {
        return personsVehiclesDao.selectPersonByMobileNumber(mobileNumber);
    }

    public int deletePerson(String mobileNumber) {
        return personsVehiclesDao.deletePersonById(mobileNumber);
    }

    public Optional<PersonVehicles> getQRCodeDetails(String mobileNumber) {
        return personsVehiclesDao.getQRScannedWeeklyQuota(mobileNumber);
    }

    public String adjustWeeklyBalanceQuota(String consumedUnit, String id) {
        int eligibleWeeklyBalance = personsVehiclesDao.getWeeklyBalanceQuota(id);
        int consumingUnit = Integer.parseInt(consumedUnit);

        if (consumingUnit > eligibleWeeklyBalance) {
            return "cannot consume";
        }else {
            int balanceUnit = eligibleWeeklyBalance - consumingUnit;

            if (personsVehiclesDao.adjustWeeklyBalanceQuota(balanceUnit,id) == 1) {
                return "success";
            }else {
                return "error in eligibleWeeklyBalance update";
            }
        }
    }
}
