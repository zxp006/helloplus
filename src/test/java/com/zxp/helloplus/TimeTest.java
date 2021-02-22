package com.zxp.helloplus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author zxp
 * @date 2020-11-24 10:38
 * @description:
 */
@Slf4j
public class TimeTest {

    @Test
    public void  getNow(){
        Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat3=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long t=calendar.getTime().getTime();
        log.info("当前："+calendar.getTime());
        log.info("当前2:"+simpleDateFormat2.format(calendar.getTimeInMillis()));
        log.info("当前3:"+simpleDateFormat3.format(new Date()));
    }


    @Test
    public  void testArray() {
        String[] names = new String[] { "A", "B", "C" ,"D"};
        String[] extended = {"1","2","3","4","5","6"};

        log.info("extended长度前="+extended.length);
//        System.arraycopy(names, 0, extended, 0, names.length);
        extended = Arrays.copyOf(extended,names.length+extended.length);
        for (String str : extended){
            System.out.println(str);
        }
        log.info("extended长度后="+extended.length);
    }
}
