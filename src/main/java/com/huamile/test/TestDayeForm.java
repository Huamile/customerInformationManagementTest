package com.huamile.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDayeForm {

    @Test
    public void testTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(new Date(Long.valueOf("1567751828000")));
        System.out.println(time);
    }
}
