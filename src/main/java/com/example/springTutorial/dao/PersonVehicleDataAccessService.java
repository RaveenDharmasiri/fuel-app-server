package com.example.springTutorial.dao;

import com.example.springTutorial.model.PersonVehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository("mysql")
public class PersonVehicleDataAccessService implements PersonsVehiclesDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonVehicleDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(PersonVehicles personVehicles) {

        String sql = "INSERT INTO PERSONS_VEHICLE(ID, ID_TYPE, MOBILE_NUMBER, FIRST_NAME, LAST_NAME, ADDRESS, VEHICLE_TYPE, VEHICLE_NUMBER, CHASSIS_NUMBER, ELIGIBLE_WEEKLY_QUOTA, ELIGIBLE_WEEKLY_BALANCE, JOIN_DATE) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE)";

        return jdbcTemplate.update(sql,
                personVehicles.getId(),
                personVehicles.getIdType(),
                personVehicles.getMobileNumber(),
                personVehicles.getFirstName(),
                personVehicles.getLastName(),
                personVehicles.getAddress(),
                personVehicles.getVehicleType(),
                personVehicles.getVehicleNumber(),
                personVehicles.getChassisNumber(),
                personVehicles.getEligibleWeekQuota(),
                personVehicles.getEligibleWeekBalance());
    }

    @Override
    public Optional<PersonVehicles> selectPersonByMobileNumber(String mobileNumber) {

        System.out.println(mobileNumber);

        final String sql =
                "SELECT ID, " +
                    "ID_TYPE," +
                    "MOBILE_NUMBER, " +
                    "FIRST_NAME, " +
                    "LAST_NAME, " +
                    "ADDRESS, " +
                    "VEHICLE_TYPE, " +
                    "VEHICLE_NUMBER, " +
                    "CHASSIS_NUMBER, " +
                    "ELIGIBLE_WEEKLY_QUOTA, " +
                    "ELIGIBLE_WEEKLY_BALANCE, " +
                    "JOIN_DATE FROM PERSONS_VEHICLE " +
                "WHERE MOBILE_NUMBER = ? " +
                "AND REGISTRATION_FLAG = 'R'";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        String id = rs.getString("ID");
                        String idType = rs.getString("ID_TYPE");
                        int a_mobileNumber = rs.getInt("MOBILE_NUMBER");
                        String firstName = rs.getString("FIRST_NAME");
                        String lastName = rs.getString("LAST_NAME");
                        String address = rs.getString("ADDRESS");
                        String vehicleType = rs.getString("VEHICLE_TYPE");
                        String vehicleNumber = rs.getString("VEHICLE_NUMBER");
                        String chassisNumber = rs.getString("CHASSIS_NUMBER");
                        int ewQuota = rs.getInt("ELIGIBLE_WEEKLY_QUOTA");
                        int ewBalance = rs.getInt("ELIGIBLE_WEEKLY_BALANCE");
                        Date joinDate = rs.getDate("JOIN_DATE");
                        return new PersonVehicles(id, idType, a_mobileNumber, firstName, lastName, address, vehicleType, vehicleNumber, chassisNumber, ewQuota, ewBalance, joinDate);
                    },
                    mobileNumber
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int deletePersonById(String mobileNumber) {

        String sql = "UPDATE PERSONS_VEHICLE " +
                "SET REGISTRATION_FLAG = 'U' " +
                "WHERE MOBILE_NUMBER = ?";

        return jdbcTemplate.update(sql,mobileNumber);
    }

    @Override
    public Optional<PersonVehicles> getQRScannedWeeklyQuota(String mobileNumber) {

        final String sql =
                "SELECT " +
                    "ID," +
                    "ELIGIBLE_WEEKLY_QUOTA," +
                    "ELIGIBLE_WEEKLY_BALANCE " +
                "FROM persons_vehicle " +
                "WHERE MOBILE_NUMBER = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> {
                    String id = rs.getString("ID");
                    int eligible_weekly_quota = rs.getInt("ELIGIBLE_WEEKLY_QUOTA");
                    int eligible_weekly_balance = rs.getInt("ELIGIBLE_WEEKLY_BALANCE");
                    return new PersonVehicles(id,eligible_weekly_quota,eligible_weekly_balance);
                },
                mobileNumber
        ));
    }

    @Override
    public int adjustWeeklyBalanceQuota(int balanceUnit, String id) {
        String sql = "UPDATE PERSONS_VEHICLE SET ELIGIBLE_WEEKLY_BALANCE = ? WHERE ID = ?";
        return jdbcTemplate.update(sql,balanceUnit, id);
    }

    @Override
    public int getWeeklyBalanceQuota(String id) {
        String sql = "SELECT ELIGIBLE_WEEKLY_BALANCE FROM PERSONS_VEHICLE WHERE ID = ?";
        //noinspection ConstantConditions
        return jdbcTemplate.queryForObject(sql, Integer.class,id);
    }
}