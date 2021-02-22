package com.zxp.helloplus;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @author zxp
 * @date 2020-11-12 17:57
 * @description:
 */
@Slf4j
public class CollectTest {

    @Test
    public  void  clear(){
        Set<String>  set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String keyStr = it.next();
            System.out.println(set.size()+"_"+keyStr);
           // it.remove();
        }
    }

    @Test
    public void testListCopy(){
        List<String> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        List<String> list3=new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list2.add("中国");
        list2.add("美国");
        list1.add("加拿大");
        list1.addAll(list2);
        list1.addAll(list3);
        log.info(list1.toString());
    }
}
