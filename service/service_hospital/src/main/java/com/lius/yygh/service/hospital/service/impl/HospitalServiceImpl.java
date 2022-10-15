package com.lius.yygh.service.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lius.hospital.model.hosp.HospitalSet;
import com.lius.yygh.service.hospital.mapper.HospitalSetMapper;
import com.lius.yygh.service.hospital.service.HospitalService;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalService {

    // 此处不用去注入mapper,因为父类中已经注入

}
