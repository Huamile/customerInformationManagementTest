package com.huamile.test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Afa
 */
public class TestSplit {
    @Test
    public void testSplit(){
        String s = "2019-09-17 00:00:00 - 2019-10-29 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        String[] split = s.split(" - ");
        for (String s1 : split) {
            try {
                date = simpleDateFormat.parse(s1);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
