package com.zxp.helloplus.model.param;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author zxp
 * @create 2020-09-14 15:27
 */

@Data
@Accessors(chain = true)
public class Common<T> {

    @Max(value = 100,message = "id最大值不超过100")
    private  Integer id;
    @NotEmpty(message = "描述不能为空")
    private String desc;

    @Valid
    @NotNull(message = "param不能为空")
    private T param;

}
