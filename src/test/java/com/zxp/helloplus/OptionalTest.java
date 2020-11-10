package com.zxp.helloplus;

import com.zxp.helloplus.model.People;
import com.zxp.helloplus.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author zxp
 * @create 2020-09-23 15:28
 */
@SpringBootTest
@Slf4j
public class OptionalTest {

    @Test
    public void  optional01(){
        User.getUserSteetName(null);
        log.info("返回值{}",User.getUserSteetName(null));
    }


    @Test
    void testOptional() {
        People person=new People();
        Optional<People> optionalPeople = Optional.empty();
        Optional<People> optionalPeople2 = Optional.ofNullable(null);
        optionalPeople.orElse(person);
        optionalPeople.orElseGet(()->new People());
        System.out.println(optionalPeople2.get());
        System.out.println(optionalPeople);
    }

}
