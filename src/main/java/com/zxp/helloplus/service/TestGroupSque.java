package com.zxp.helloplus.service;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author zxp
 * @create 2020-09-15 13:59
 */


public interface TestGroupSque {
    public  interface  GroupId{}
    public interface  GroupAge{}
    //校验顺序是GroupAge组，默认组，GroupId组
    @GroupSequence({GroupAge.class, Default.class,GroupId.class})
    public  interface GroupSque{}
}
