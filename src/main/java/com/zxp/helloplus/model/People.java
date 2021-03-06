package com.zxp.helloplus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Override
    public int compareTo(People o) {
        return this.getAge()-o.getAge();
    }
}
