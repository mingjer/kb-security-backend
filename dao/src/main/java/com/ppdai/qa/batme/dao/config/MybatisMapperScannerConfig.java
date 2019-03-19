//package com.ppdai.qa.batme.dao.config;
//
//import org.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.mapper.MapperScannerConfigurer;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by yangkun on 2018/5/30.
// */
//
//@Configuration
//@AutoConfigureAfter(DruidConfig.class)
//@MapperScan("com.ppdai.qa.batme.dao.mapper")
//public class MybatisMapperScannerConfig {
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.ppdai.qa.batme.dao.mapper");
//        return mapperScannerConfigurer;
//    }
//}
