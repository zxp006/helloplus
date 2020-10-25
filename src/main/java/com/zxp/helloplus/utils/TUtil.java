package com.zxp.helloplus.utils;

/**
 * @author zxp
 * @create 2020-10-20 22:06
 */
public class TUtil<T> {

    public  static  <M> M getInfo(M obj){
        return  obj;
    }

    public  static  <M> String getString(M obj){
        return  obj.toString();
    }

    public  String getObj(T t){
        return t.toString();
    }
}
