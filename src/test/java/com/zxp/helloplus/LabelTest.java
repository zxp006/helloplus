package com.zxp.helloplus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author zxp
 * @date 2020-12-07 13:52
 * @description:
 */
@Slf4j
public class LabelTest {

    @Test
    public  void  testLable(){
        lable1:
        {
           for(int i=0;i<100;i++){
               log.info("循环i={}",i);
               if(i==10){
                    break lable1;
               }
               label2:{
                   for(int j=1;j<8;j++){
                       log.info("循环j={}",j);
                       if(j==3)
                       break label2;
                   }
               }

           }

        }
        log.info("结束循环");

    }
}
