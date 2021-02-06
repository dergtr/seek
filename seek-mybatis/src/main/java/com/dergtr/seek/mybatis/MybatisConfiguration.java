package com.dergtr.seek.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.dergtr.seek.mybatis.mapper")
public class MybatisConfiguration {
}
