package com.zxp.helloplus.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zxp
 * @create 2020-09-14 15:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PersonParam implements Serializable {
    @Max(value = 18, message = "年龄不能超过18岁")
    private Integer age;
    @Max(value = 1, message = "性别只能为0和1: 0=女1=男")
    @Min(value = 0, message = "性别只能为0和1: 0=女1=男")
    private Short sex;

    private String name;

}
