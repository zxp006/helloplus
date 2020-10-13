package com.zxp.helloplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxp.helloplus.model.Person;

import java.util.List;
import java.util.Map;

/**
 * @author zxp
 * @create 2020-09-13 22:45
 */
public interface  PersonMapper extends BaseMapper<Person> {

    Map<String,Object> getPersonOfId();

    List<Map<String, Object>> getPersons(IPage page);
}
