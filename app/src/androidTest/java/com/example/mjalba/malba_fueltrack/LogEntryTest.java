package com.example.mjalba.malba_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * Testing the LogEntry class.
 */
public class LogEntryTest extends ActivityInstrumentationTestCase2 {

    public LogEntryTest() {
        super(MainActivity.class);
    }

    public void testGetDate() {
        Log log = new Log();
        LogEntry entry = new LogEntry();
        log.addEntry(entry);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String now = formatter.format(new Date());
        assertTrue(now.equals(log.getLog().get(0).getDate()));
    }

    public void testGetStation() {
        Log log = new Log();
        LogEntry entry = new LogEntry();
        String test = "test";
        entry.setStation(test);
        log.addEntry(entry);
        assertEquals(test, log.getLog().get(0).getStation());
    }

    public void testGetOdometer() {
        Log log = new Log();
        LogEntry entry = new LogEntry();
        Double odometer = 100.00;
        String odometerString = new DecimalFormat("#.0").format(odometer);
        entry.setOdometer(odometer);
        log.addEntry(entry);
        assertEquals(odometerString, log.getLog().get(0).getOdometer());
    }

    public void testGetAmount() {
        Log log = new Log();
        LogEntry entry = new LogEntry();
        Double amount = 100.00;
        String amountString = new DecimalFormat("#.000").format(amount);
        entry.setAmount(amount);
        log.addEntry(entry);
        assertEquals(amountString, log.getLog().get(0).getAmount());
    }

    public void testGetUnitCost() {
        Log log = new Log();
        LogEntry entry = new LogEntry();
        Double unitCost = 100.0;
        String unitCostString = new DecimalFormat("#.0").format(unitCost);
        entry.setUnitCost(unitCost);
        log.addEntry(entry);
        assertEquals(unitCostString, log.getLog().get(0).getUnitCost());

    }

    public void testGetCostValue() {
        LogEntry entry = new LogEntry();
        Double amount = 10.00;
        Double unitCost = 69.4;
        Double totalCost = amount * (unitCost / 100);
        entry.setAmount(amount);
        entry.setUnitCost(unitCost);
        assertEquals(entry.getCostValue(), totalCost, 0.0);
    }
}
