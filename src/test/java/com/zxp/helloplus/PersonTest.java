package com.zxp.helloplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxp.helloplus.mapper.PersonMapper;
import com.zxp.helloplus.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zxp
 * @create 2020-09-13 22:47
 */
@SpringBootTest
@Slf4j
public class PersonTest {
    @Autowired
    private PersonMapper personMapper;

    @Test
    public void getPerson() {
        List<Person> personList = personMapper.selectList(null);
        personList.forEach(person -> System.out.println(person));
    }

    /**
     * 自定义获取用户
     */
    @Test
    public void getPersonOfId() {
        Map person = personMapper.getPersonOfId();
        log.info("获取的对象:" + person);
    }

    /**
     * 系统方法获取分页数据
     */
    @Test
    public void getByPage() {
        IPage<Person> page = new Page<>(2, 2);
        IPage<Person> personIPage = personMapper.selectPage(page, null);
        System.out.println(personIPage.getTotal());
        personIPage.getRecords().forEach(person -> System.out.println(person));
    }

    /**
     * 自定义map获取分页数据
     */
    @Test
    public void getPersonsByPage() {
        IPage<Person> page = new Page<>(2, 2);
        List<Map<String, Object>> persons = personMapper.getPersons(page);
        log.info("总数量"+page.getTotal()+" 获取的对象:" + persons);
    }
    @Test
    public void addPerson() {
        Person person = new Person();
        person.setName("测试8");
        person.setAge(100);
        person.setDesct("测试plus插入数据8");
        person.setMoney(1000d);
//        person.setWork("java");
        person.setBirth(new Date());
        int i=personMapper.insert(person);
        log.info("插入了{}行",i);
    }


    /**
     * 3.0版本后
     * 只根据wrapper更新
     * Preparing: UPDATE person SET name=?,work=? WHERE (id = ?)
     * Parameters: null, 条件更新(String), 8(Integer)
     *
     */
    @Test
    public  void  updatePersonByWrapper(){
       UpdateWrapper<Person> updateWrapper=new UpdateWrapper<>();
       updateWrapper.set("name",null);
       updateWrapper.set("work","条件更新");
       updateWrapper.eq("id",8);
       int i= personMapper.update(null,updateWrapper);
        log.info("更新了{}行",i);
    }

    /**
     * 3.0版本后
     * 根据实体和wrapper更新,更新内容为这两部设置的值
     * Preparing: UPDATE person SET name=?, money=?, work=?,description=? WHERE (id = ?)
     * Parameters: 测试更新03(String), 100.0(Double), null, 新的工作新的环境(String), 3(Integer)
     */
    @Test
    public  void  updatePersonByEntityAndWrapper(){
        Person person=new Person();
        person.setName("测试更新03");
        person.setMoney(100d);
        UpdateWrapper<Person> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",3);
        updateWrapper.set("work",null);
        updateWrapper.set("description","新的工作新的环境");
        int i=personMapper.update(person,updateWrapper);
        log.info("更新了{}行",i);
    }

    /**
     * 版本通用,没法指定字段更新为null
     * Preparing: UPDATE person SET name=?, work=? WHERE (id = ?)
     * Parameters: 测试更新02(String), 更新work了(String), 2(Integer)
     */
    @Test
    public  void  updatePerson(){
        Person person=new Person();
        person.setName("测试更新02");
        person.setWork("更新work了");
        person.setDesct(null);

        QueryWrapper<Person> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",2);
        int i=personMapper.update(person,queryWrapper);
        log.info("更新了{}行",i);
    }


    /**
     * 版本通用,没法指定字段更新为null
     *  Preparing: Preparing: UPDATE person SET name=?, work=?, money=? WHERE id=?
     *  Parameters: 测试更新07(String), 工程师(String), 1000000.0(Double), 7(Integer)
     */
    @Test
    public  void updatePersonById(){
        Person person=new Person();
        person.setName("测试更新07");
        person.setWork("工程师");
        person.setMoney(1000000d);
        person.setDesct(null);
        person.setId(7);
        int i=personMapper.updateById(person);
        log.info("更新了{}行",i);
    }



}
