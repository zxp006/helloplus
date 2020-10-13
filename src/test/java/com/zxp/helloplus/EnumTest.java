package com.zxp.helloplus;

import com.zxp.helloplus.model.enumparam.Color;
import com.zxp.helloplus.model.enumparam.MyDay;
import com.zxp.helloplus.model.enumparam.PayGatewayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author zxp
 * @create 2020-09-14 18:14
 */
@SpringBootTest
@Slf4j
public class EnumTest {

    @Test
    public  void  test01(){
        System.out.println(MyDay.MONDAY.getCode());
        System.out.println(MyDay.MONDAY.getName());
        System.out.println(MyDay.THUSDAY.getCode());
        System.out.println(MyDay.THUSDAY.getName());
        System.out.println(MyDay.THUSDAY);
    }


    @Test
    public  void  test02(){
        for(MyDay myDay:MyDay.values()){
            log.info("枚举:{}-值:{}-名称:{}",myDay,myDay.getCode(),myDay.getName());
        }
        for (Color c : Color.values()) {
            log.info("枚举:{}-值:{}-名称:{}",c,c.getIndex(),c.getName());
        }
    }


    @Test
    public  void  test03(){
        Map<String, String> map = PayGatewayStatusEnum.getMap();

//        log.info(json);
    }

    @Test
    public  void  testColor01(){
        System.out.println(Color.getName(2));
    }
}
