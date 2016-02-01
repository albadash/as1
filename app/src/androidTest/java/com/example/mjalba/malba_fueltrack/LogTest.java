package com.example.mjalba.malba_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by MJ Alba on 2016-01-31.
 */
public class LogTest extends ActivityInstrumentationTestCase2 {

    public LogTest() {
        super(MainActivity.class);
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
}