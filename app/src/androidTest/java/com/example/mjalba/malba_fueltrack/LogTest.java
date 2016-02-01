package com.example.mjalba.malba_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * Testing the Log class.
 */
public class LogTest extends ActivityInstrumentationTestCase2 {

    public LogTest() {
        super(MainActivity.class);
    }

    public void testGetLog() {
        Log log = new Log();

        LogEntry entry = new LogEntry();
        log.addEntry(entry);
        assertEquals(log.getLog().get(0).getDate(), entry.getDate());
        assertEquals(log.getLog().get(0).getStation(), entry.getStation());
        assertEquals(log.getLog().get(0).getOdometer(), entry.getOdometer());
        assertEquals(log.getLog().get(0).getGrade(), entry.getGrade());
        assertEquals(log.getLog().get(0).getAmount(), entry.getAmount());
        assertEquals(log.getLog().get(0).getUnitCost(), entry.getUnitCost());

        LogEntry nextEntry = new LogEntry();
        log.addEntry(nextEntry);
        assertEquals(log.getLog().get(1).getDate(), nextEntry.getDate());
        assertEquals(log.getLog().get(1).getStation(), nextEntry.getStation());
        assertEquals(log.getLog().get(1).getOdometer(), nextEntry.getOdometer());
        assertEquals(log.getLog().get(1).getGrade(), nextEntry.getGrade());
        assertEquals(log.getLog().get(1).getAmount(), nextEntry.getAmount());
        assertEquals(log.getLog().get(1).getUnitCost(), nextEntry.getUnitCost());
    }

    public void testSetLog() {
        Log log = new Log();
        LogEntry entry = new LogEntry();
        log.addEntry(entry);

        Log log2 = new Log();
        log2.setLog(log.getLog());
        assertEquals(log.getLog().get(0).getDate(), log2.getLog().get(0).getDate());
        assertEquals(log.getLog().get(0).getStation(), log2.getLog().get(0).getStation());
        assertEquals(log.getLog().get(0).getOdometer(), log2.getLog().get(0).getOdometer());
        assertEquals(log.getLog().get(0).getGrade(), log2.getLog().get(0).getGrade());
        assertEquals(log.getLog().get(0).getAmount(), log2.getLog().get(0).getAmount());
        assertEquals(log.getLog().get(0).getUnitCost(), log2.getLog().get(0).getUnitCost());
    }

    public void testAddEntry() {
        Log log = new Log();
        LogEntry entry = new LogEntry();
        log.addEntry(entry);
        assertTrue(log.hasEntry(entry));
    }

    public void testHasEntry() {
        Log log = new Log();
        LogEntry entry = new LogEntry();
        assertFalse(log.hasEntry(entry));
        log.addEntry(entry);
        assertTrue(log.hasEntry(entry));
    }

    public void testHasTotalCostValue() {
        Log log = new Log();
        LogEntry entry = new LogEntry();

        Double amount = 10.00;
        Double unitCost = 69.4;
        Double totalCost = amount * (unitCost / 100);
        entry.setAmount(amount);
        entry.setUnitCost(unitCost);
        log.addEntry(entry);
        assertEquals(log.getTotalCostValue(), totalCost, 0.0);

        LogEntry newEntry = new LogEntry();
        amount = 20.0;
        unitCost = 85.9;
        totalCost += amount * (unitCost / 100);
        newEntry.setAmount(amount);
        newEntry.setUnitCost(unitCost);
        log.addEntry(newEntry);
        assertEquals(log.getTotalCostValue(), totalCost, 0.0);
    }
}