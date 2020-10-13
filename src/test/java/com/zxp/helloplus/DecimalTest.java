package com.zxp.helloplus;

import com.zxp.helloplus.utils.PrecisionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.jar.JarEntry;

/**
 * @author zxp
 * @create 2020-09-25 10:22
 */

@Slf4j
public class DecimalTest {

    @Test
    public void test1() {
        log.info(PrecisionUtil.format1(100.525));
        log.info(PrecisionUtil.format1(100.521));
    }

    @Test
    public void test2() {
        log.info(PrecisionUtil.format2(100.525));
        log.info(PrecisionUtil.format2(100.521));
    }


    @Test
    public void test3() {
        log.info(PrecisionUtil.format3(100.525));
        log.info(PrecisionUtil.format3(100.521));
    }

    @Test
    public void test4() {
        log.info(PrecisionUtil.format4(100.525));
        log.info(PrecisionUtil.format4(100.521));
    }

    /**
     * BigDecimal 指定位数测试
     */
    @Test
    public void test5() {
        BigDecimal bigDecimal = new BigDecimal(100.2591);
        bigDecimal=bigDecimal.setScale(2, RoundingMode.HALF_UP);
        log.info(bigDecimal.toString());
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(100));
        log.info(multiply.toString());
    }


    @Test
    public void test6() {
        BigDecimal bigDecimal = new BigDecimal(1000);
        bigDecimal=bigDecimal.setScale(2, RoundingMode.HALF_UP);
        log.info(bigDecimal.toString());
        BigDecimal multiply = bigDecimal.multiply(new BigDecimal(0.3));
//        multiply=multiply.setScale(1,BigDecimal.ROUND_UP);
        multiply=new BigDecimal(String.valueOf(multiply));
        log.info(multiply.toString());


        Long a=1000L;
        Long b=1000L;
        log.info(String.valueOf(a.equals(b)));


        Long m=1000L;
        log.info("m={}",m*0.3);
    }
}
