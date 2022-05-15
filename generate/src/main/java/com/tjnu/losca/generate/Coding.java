package com.tjnu.losca.generate;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.tjnu.losca.pojo.BasePojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Losca
 * @date 2022/1/13 20:39
 */
public class Coding {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 获取到项目路径
        String projectPath = System.getProperty("user.dir");
        // 定义项目生成路径
        String pojo = projectPath + "/pojo/src/main/java/com/tjnu/losca/pojo";
        // 定义其他模块路径
        String other = projectPath + "/product" + "/src/main";
        // 配置全局
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("losca");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.142.201:3307/parentdemo?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.tjnu.losca");
        pc.setEntity("pojo");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        Map<String,String> pathInfo = new HashMap<String,String>();
        pathInfo.put("entity_path",pojo);
        pathInfo.put("mapper_path", other + "/java/com/tjnu/losca/mapper");
        pathInfo.put("xml_path", other + "/resources/com/tjnu/losca/mapper");
        pathInfo.put("service_path", other + "/java/com/tjnu/losca/service");
        pathInfo.put("service_impl_path", other + "/java/com/tjnu/losca/service/impl");
        pathInfo.put("controller_path", other + "/java/com/tjnu/losca/controller");
        pc.setPathInfo(pathInfo);
        mpg.setPackageInfo(pc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BasePojo.class);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        //表名
        strategy.setInclude("pms_product");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 执行生成
        mpg.execute();
    }
}
