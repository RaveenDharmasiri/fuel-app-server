package com.example.springTutorial.api;

import com.example.springTutorial.model.PersonVehicles;
import com.example.springTutorial.service.PersonVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/v1/person-vehicle-details")
@RestController
public class PersonVehicleController {
    private final PersonVehicleService personVehicleService;

    @Autowired
    public PersonVehicleController(PersonVehicleService personVehicleService) {
        this.personVehicleService = personVehicleService;
    }

    @PostMapping
    public String addPerson(@NonNull @RequestBody PersonVehicles personVehicles) {
        return personVehicleService.addPerson(personVehicles);
    }

    @GetMapping(path = "{mNumber}")
    public PersonVehicles getPersonByMobileNumber(@PathVariable("mNumber") String mobileNumber) {
        return personVehicleService.getPersonByMobileNumber(mobileNumber).orElse(null);
    }

    @DeleteMapping(path = "{mNumber}")
    public int deletePersonById(@PathVariable("mNumber") String mobileNumber) {
        return personVehicleService.deletePerson(mobileNumber);
    }

    @GetMapping(path = "/get-QR-Code-details/{mNumber}")
    public PersonVehicles getQRCodeDetail(@PathVariable("mNumber") String mobileNumber) {
        return personVehicleService.getQRCodeDetails(mobileNumber).orElse(null);
    }

    @PostMapping(path = "/change-eligible-weekly-balance/units={consumedUnit}&id={id}")
    public String changeEligibleWeeklyBalance(@PathVariable("consumedUnit") String consumedUnit, @PathVariable("id") String id) {
        return personVehicleService.adjustWeeklyBalanceQuota(consumedUnit,id);
    }
}
