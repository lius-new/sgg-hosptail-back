package com.lius.yygh.service.hospital.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.lius.yygh.service.hospital.mapper")
public class HospitalSetConfig {

}
