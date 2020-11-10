package com.zxp.helloplus;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.zxp.helloplus.model.People;
import com.zxp.helloplus.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zxp
 * @create 2020-09-23 13:43
 */


@Slf4j
public class StreamTest {

    static List<People> peopleList = new ArrayList<>();
    static List<People> peopleList2 = new ArrayList<>();

    @BeforeAll
    public static void init() {
        peopleList.add(new People("欧阳雪", 18, "中国", 'F'));
        peopleList.add(new People("Tom", 24, "美国", 'M'));
        peopleList.add(new People("Harley", 22, "英国", 'F'));
        peopleList.add(new People("向天笑", 20, "中国", 'M'));
        peopleList.add(new People("李康", 22, "中国", 'M'));
        peopleList.add(new People("小梅", 20, "中国", 'F'));
        peopleList.add(new People("何雪", 21, "中国", 'F'));
        peopleList.add(new People("李康", 22, "中国", 'M'));
    }

    @Test
    public void testFilter() {
        List<People> collect = peopleList.stream().filter(people -> {
            return people.getAge() > 20 && people.getSex() == 'F';
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("---------------------");
        List<People> list = new ArrayList<>();
        peopleList.forEach(people -> {
            if (people.getAge() > 20 && people.getSex() == 'F') {
                list.add(people);
            }
        });
        list.forEach(System.out::println);
    }

    /**
     * Stream中间操作--映射
     */
    @Test
    public void testMap() {
        Set<Person> personSet = peopleList.stream().map(people -> {
            return new Person().setName(people.getName()).setDesct(people.getCountry());
        }).collect(Collectors.toSet());

        personSet.forEach(System.out::println);
    }

    /**
     * 自然排序(Comparable)
     */
    @Test
    public void testSorted1() {
        peopleList.stream().sorted().forEach(System.out::println);
    }

    /**
     * 定制排序（Comparator）
     */
    @Test
    public void testSorted2() {
        List<People> collect = peopleList.stream().sorted((p1, p2) -> {
            if (p1.getAge().equals(p2.getAge())) {
                return -p1.getName().compareTo(p2.getName());
            } else {
                return -p1.getAge().compareTo(p2.getAge());
            }
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    @Test
    public void testMax() {
        Optional<People> max = peopleList.stream().filter(people -> people.getSex() == 'M').max((p1, p2) -> p1.getAge() - p2.getAge());
        log.info("最大年龄的男性{}", max.get());
    }

    @Test
    public void testAllMatch() {
        boolean b = peopleList.stream().allMatch(people -> people.getAge() > 20);
        log.info("所有人都大于20岁" + b);
    }

    /**
     * 测试方法引用
     * 获取年龄最新的外国人
     */
    @Test
    public void testF() {
        peopleList.stream().filter(people -> !people.getCountry().equals("中国")).forEach(peopleList2::add);
        peopleList2.forEach(System.out::println);
        Optional<People> first = peopleList2.stream().sorted((p1, p2) -> {
            return p1.getAge() - p2.getAge();
        }).findFirst();
        log.info(first.toString());
    }

    @Test
    public  void  testList(){
        List<Integer> collect = peopleList.stream().filter(people -> people.getAge() > 30).map(people -> people.getAge()).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public  void  testEmptyList(){
        List<People> list=null;
        System.out.println("list=null="+(CollectionUtils.isEmpty(list)));
        //list=null的情况下，以下语句报空指针异常
//        System.out.println(list.isEmpty());
//        list.forEach(System.out::println);
    }
}
