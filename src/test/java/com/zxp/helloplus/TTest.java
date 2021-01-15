package com.zxp.helloplus;

import com.zxp.helloplus.model.Person;
import com.zxp.helloplus.model.User;
import com.zxp.helloplus.utils.TUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zxp
 * @create 2020-10-20 22:08
 */
@Slf4j
public class TTest {

    final  int count=100000;

    @Test
    public void test01() {

        Person person = TUtil.getInfo(new Person().setName("张三").setMoney(100d).setDesct("工程师"));
        User user = TUtil.getInfo(new User().setName("李莉").setAge(20));
        log.info("person={},user={}",person,user);
    }

    @Test
    public  void  test02(){
        String objString = TUtil.getString(new Person().setName("张三").setMoney(100d).setDesct("工程师"));
        log.info(objString);
    }

    @Test
    public  void  test03(){
        TUtil<Person> tUtil=new TUtil<>();
        String objString = tUtil.getObj(new Person().setName("张三").setMoney(100d).setDesct("工程师"));
        log.info(objString);
    }

    @Test
    public void  test05(){
        List<Person> list=new ArrayList<>();
        List<Person> collect=new ArrayList<>();
        for(int i=0;i<count;i++){
            list.add(new Person().setAge(100).setName("测试").setDesct("性能测试").setWork("易车"));
        }
        long start = System.currentTimeMillis();
        for(int i=0;i<count;i++){
            list.get(i).setId(1);
            collect.add(list.get(i));
        }
        long end = System.currentTimeMillis();
        log.info("耗时："+(end-start));

    }


    @Test
    public void  test06(){
        List<Person> list=new ArrayList<>();
        for(int i=0;i<count;i++){
            list.add(new Person().setAge(100).setName("测试").setDesct("性能测试").setWork("易车"));
        }
        long start = System.currentTimeMillis();
        List<Person> collect = list.parallelStream().map(t -> {
            t.setId(1);
            return  t;
        }).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        log.info("耗时："+(end-start));


    }

    @Test
    public  void  test07(){
       Person person=null;
       log.info(person.toString());
    }
}
