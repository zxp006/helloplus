package com.zxp.helloplus.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zxp.helloplus.service.TestGroupSque;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zxp
 * @create 2020-03-12 22:56
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person {

    /**
     * 属性参与校验时，默认归属default组与 @Validated 不指定分组时，默认校验default组相对应
     */
    @NotNull(groups = TestGroupSque.GroupId.class, message = "id不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @JsonProperty("username")
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @Max(groups = TestGroupSque.GroupAge.class, value = 100, message = "年龄不能超过100岁")
    private Integer age;

    /**
     * 过滤不想序列化的字段
     */
    @JsonIgnore
    @TableField(value = "description")
    @NotEmpty(message = "描述不能为空")
    private String desct;
    /**
     * 为null时也更新，默认不更新
     */
//    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String work;
    /**
     * 过滤不想序列化的字段
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double money;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date birth;
    @TableField(exist = false)
    private Date loggin;
}
