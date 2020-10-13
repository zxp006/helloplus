package com.zxp.helloplus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxp.helloplus.model.Person;
import com.zxp.helloplus.model.param.Common;
import com.zxp.helloplus.model.param.PersonParam;
import com.zxp.helloplus.model.vo.Result;
import com.zxp.helloplus.paramAnnotation.SelfParam;
import com.zxp.helloplus.service.TestGroupSque;
import com.zxp.helloplus.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author zxp
 * @create 2020-09-14 7:36
 */

@RestController
@Slf4j
public class PersonController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping("/person")
    /**
     * @Validated 不指定分组时，默认校验default组
     */
    public Person getPerson(@Validated(TestGroupSque.GroupSque.class) Person person, BindingResult result) {
        log.info("传入的对象【{}】", person);
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(e -> {
                log.error("error msg: 【{}】", e.getDefaultMessage());
            });
        }

        return new Person(1, "哈哈", 20, "书法", "行政", 10000d, new Date(), new Date());
    }

    /**
     * 如果方法参数写了BindingResult参数，则验证结果由它接受，不会抛出异常,否则抛出异常
     *
     * @param personParmCommon
     * @param result
     * @return
     */
    @PostMapping("/person")
    public Result getPerson(@Validated @RequestBody Common<PersonParam> personParmCommon, BindingResult result) {
        PersonParam personParam = personParmCommon.getParam();
        log.info("传入的对象【{},{},{}】", personParmCommon, personParmCommon.getParam(), personParmCommon.getParam().getClass());
        if (result.hasErrors()) {
            return Result.fail(result.getFieldError().getDefaultMessage());
        }
        Person person = new Person(1, "哈哈", 20, "书法", "行政", 10000d, new Date(), new Date());

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String param = request.getParameter("param");
        String desc = request.getParameter("desc");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String body = RequestUtil.getBody(request);
        return Result.sucess(person);
    }


    @PostMapping("/person2")
    public Result getPerson(@Validated @RequestBody Common<PersonParam> personParmCommon) {
        log.info("传入的对象【{}】", personParmCommon);
        PersonParam personParam = personParmCommon.getParam();
        log.info("传入的子对象【{}】", personParam);
        Person person = new Person(1, "哈哈", 20, "书法", "行政", 10000d, new Date(), new Date());
        return Result.sucess(person);

    }

    /**
     * 测试Validation手动验证
     *
     * @param personParmCommon
     * @param result
     * @return
     */

    @GetMapping("/person3")
    public Result getPerson3(@Validated Common<PersonParam> personParmCommon, BindingResult result) {
        if (result.hasErrors()) {
            log.info(result.getFieldError().getDefaultMessage());
        }
        //报错
//        PersonParam personParam=personParmCommon.getParam();

        //手动验证
        Class[] clazz = new Class[]{Default.class};
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(personParmCommon, clazz);

        log.info("传入的对象【{}】", personParmCommon);
        if (result.hasErrors()) {
            return Result.fail(result.getFieldError().getDefaultMessage());
        }
        Person person = new Person(1, "哈哈", 20, "书法", "行政", 10000d, new Date(), new Date());

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String param = request.getParameter("param");
        String desc = request.getParameter("desc");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        Map<String, String[]> parameterMap = request.getParameterMap();
        return Result.sucess(person);
    }

    /**
     * 测试post请求方式的form格式参数和url参数
     *
     * @param personParmCommon
     * @return
     */
    @PostMapping("/person5")
    public Result getPerson5(@Validated Common<PersonParam> personParmCommon) throws Exception {
        //直接转报错
//      PersonParam p= (PersonParam) personParmCommon.getParam();
        Object p = personParmCommon.getParam();
        PersonParam personParam = objectMapper.readValue(p.toString(), PersonParam.class);
        log.info("传入的Common【{}】,【{}】", personParmCommon, personParam);
        Person person = new Person(1, "哈哈", 20, "书法", "行政", 10000d, new Date(), new Date());
        //获取get传参
        String getid = request.getParameter("id");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");

        if (request.getMethod().equals(HttpMethod.POST.name())) {
            String contentType = request.getContentType();
            if (StringUtils.isNotEmpty(contentType)) {
                if (contentType.startsWith(ContentType.MULTIPART_FORM_DATA.getMimeType())) {
                    //获取post方式的form类型传参
                    String postid = request.getParameter("id");
                    String name = request.getParameter("name");
                    String param = request.getParameter("param");
                    String desc = request.getParameter("desc");
                } else {
                    //获取post方式的json类型传参
                    String body = RequestUtil.getBody(request);
                    log.info("body:{}", body);
                }
            }
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("parameterMap:{}", parameterMap);
        return Result.sucess(person);
    }


    /**
     * 注解@RequestParam接收的参数是来自HTTP请求体或请求url的QueryString中
     *
     * @return
     */
    @RequestMapping("/person6")
    public Result getPerson6() {
        Object params = RequestUtil.getParams(request);
        return Result.sucess(params);
    }

    //RequestParam 不可接受对象
    @PostMapping("/person7")
    public Result getPerson7(@RequestParam(value = "person", required = false) Comparable<PersonParam> personparam) {
        log.info("传入的对象【{}】", personparam);
//        String person=String.format("id=%d,name=%s,param=%s", id,name,param);
        return Result.sucess(personparam);
    }


    /**
     * 添加注释
     * @param personParam
     * @return
     */
    @RequestMapping("/person8")
    public  Result getPerson8(@SelfParam(value = "person",required = false) Common<PersonParam> personParam){
        log.info("参数={}",personParam);
        return  Result.sucess(personParam);
    }
}
