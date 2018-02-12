package com.example.frontkom.androidchallenge.Helpers;

import android.content.Context;

import com.example.frontkom.androidchallenge.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Created by Cesar Ferreira on 10/02/2018.
 */

public class FileReaderJSON {

    public static String getJSON(InputStream is, int id)
    {
        //InputStream is = context.getResources().openRawResource(id);
        Writer writer = new StringWriter();

        try {
            char[] buffer = new char[is.available()];
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer.toString();
    }
}
