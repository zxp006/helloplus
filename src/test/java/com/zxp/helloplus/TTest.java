package com.zxp.helloplus;

import com.zxp.helloplus.model.Person;
import com.zxp.helloplus.model.User;
import com.zxp.helloplus.utils.TUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author zxp
 * @create 2020-10-20 22:08
 */
@Slf4j
public class TTest {


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
}
