package com.zxp.helloplus;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zxp
 * @date 2020-11-12 17:57
 * @description:
 */
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
}
