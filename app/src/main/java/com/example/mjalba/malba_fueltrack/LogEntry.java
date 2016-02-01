package com.example.mjalba.malba_fueltrack;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * This class represents a single entry in the fuel log. Its purpose is
 * to hold all data necessary for a log entry, and will allow more information
 * to be added to log entries in the future should the need arise.
 */
public class LogEntry {

    protected Date date; // date of entry
    protected String station; // gas station
    protected Double odometer = 0.0; // odometer reading (km)
    protected String grade; // fuel grade
    protected Double amount = 0.0; // amount of fuel (L)
    protected Double unitCost = 0.0; // cost of fuel (cents/L)

    // create new LogEntry for some date
    public LogEntry(Date date) {
        this.date = date;
    }

    // create new LogEntry for current date
    public LogEntry() {
        this.date = new Date();
    }

    // gets the date as a string in the format yyyy-mm-dd
    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public void setDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = formatter.parse(date);
        } catch (Exception e) {}

    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    // returns the odometer reading of this log entry, nicely formatted
    public String getOdometer() {
        String odometerString = new DecimalFormat("#.0").format(odometer);
        return odometerString;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // returns the amount of this log entry, nicely formatted
    public String getAmount() {
        String amountString = new DecimalFormat("#.000").format(amount);
        return amountString;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    // returns the unit cost of this log entry, nicely formatted
    public String getUnitCost() {
        String costString = new DecimalFormat("#.0").format(unitCost);
        return costString;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    // returns the total cost (in dollars) of the fuel in this LogEntry
    public Double getCostValue() {
        return amount * (unitCost / 100);
    }

    // returns a string of the total cost (in dollars) of the fuel in this LogEntry
    public String getCost() {
        Double totalCost = getCostValue();
        String costString = new DecimalFormat("#.00").format(totalCost);
        return "$" + costString;
    }
}
