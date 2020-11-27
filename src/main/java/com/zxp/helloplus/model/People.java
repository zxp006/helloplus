package com.zxp.helloplus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxp
 * @create 2020-09-23 13:42
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class People implements Comparable<People>{
    private String name;
    private Integer age;
    private String country;
    private char sex;
    private List<Address> list1=new ArrayList<>();
    private List<Address> list2=new ArrayList<>();
    private List<Address> list3=new ArrayList<>();

    @Override
    public int compareTo(People o) {
        return this.getAge()-o.getAge();
    }

    public People(String name,Integer age,String country,char sex){
        this.name=name;
        this.age=age;
        this.country=country;
        this.sex=sex;
    }
}
