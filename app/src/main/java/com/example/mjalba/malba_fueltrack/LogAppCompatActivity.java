package com.example.mjalba.malba_fueltrack;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by MJ Alba on 2016-02-01.
 *
 * This class simply handles functions and variables that are common to
 * views across my app (namely loading from a file).
 */
public class LogAppCompatActivity extends AppCompatActivity {

    protected static final String SAVEFILE = "file.sav"; // local save file name
    protected Log log = new Log(); // the current fuel log

    // load fuel log from save file
    // code based on in-class LonelyTwitter example, the original source code for which
    // can be found at https://github.com/joshua2ua/lonelyTwitter
    protected void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(SAVEFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // taken from https://googlecode.com/svn/trunk/gson/docs/javadocs
            Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
            log.setLog((ArrayList<LogEntry>) gson.fromJson(in, listType));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log.setLog(new ArrayList<LogEntry>());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
