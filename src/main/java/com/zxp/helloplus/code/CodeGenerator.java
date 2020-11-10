package com.zxp.helloplus.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取当前系统目录
        System.out.println("projectPath" + projectPath);
        gc.setOutputDir(projectPath + "/src/main/java");//指定输出的位置
        gc.setAuthor("zxp");//设置作者
        gc.setOpen(false);//是否打开资源管理器
        gc.setFileOverride(false);//是否覆盖原来的文件

        // 默认的自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
//      gc.setServiceName("%sService");//去掉service的i前缀
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");

        gc.setIdType(IdType.AUTO);//设置id的生成策略默认算法
        gc.setDateType(DateType.ONLY_DATE);//设置日期生成策略
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //dsc.setUrl("jdbc:mysql://localhost/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false");
        dsc.setUrl("jdbc:mysql://192.168.15.127:3306/mapi_butler?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true&failOverReadOnly=false&zeroDateTimeBehavior=convertToNull&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("jRoQ#BF%P4IN");
//        dsc.setPassword("jRoQ#BF%P4IN");
        dsc.setDbType(DbType.MYSQL);//数据库类型
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("repo"));//设置模块
        pc.setParent("com.zxp.helloplus");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//设置命名规则下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//是否生成lombok注解
        strategy.setRestControllerStyle(true);
        //strategy.setLogicDeleteFieldName("deleted");//逻辑删除字段配置
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        //表名，多个英文逗号分割
        strategy.setInclude("t_driver_area".split(","));
        //乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);//开启驼峰命名
        strategy.setControllerMappingHyphenStyle(true);//开启链接地址的下划线命名 localhost:8080/hello_id_2
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }

}