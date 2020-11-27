package com.zxp.helloplus;

import com.zxp.helloplus.model.Address;
import com.zxp.helloplus.model.People;
import com.zxp.helloplus.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author zxp
 * @date 2020-11-27 15:07
 * @description:
 */
@Slf4j
public class FanSheTest {


     static People people = new People();

    @BeforeAll
    public static void initList() {
        people.setList1(new ArrayList<>(Arrays.asList(new Address().setId(1).setDesc("北京"), new Address().setId(2).setDesc("上海"))));
        people.setList2(new ArrayList<>(Arrays.asList(new Address().setId(1).setDesc("广州"), new Address().setId(2).setDesc("深圳"))));
    }

    @Test
    public void getPropertyValue() throws Exception {
        /*Scanner sc = new Scanner(System.in);
        System.out.println("请输入变量");*/
        long l = 3;
        String getList="getList"+l;
        String setList="setList"+l;
        Method m = people.getClass().getMethod(getList);
        List list1 = (List) m.invoke(people); // 调用getter方法获取属性值
        m = people.getClass().getMethod(setList, List.class);
        list1.add(new Address().setId(3).setDesc("天津"));
        m.invoke(people, list1);
        List list2=people.getList1();
        log.info("people={}",people);
    }
}
