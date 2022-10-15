package com.lius.yygh.service.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lius.hospital.model.hosp.HospitalSet;
import com.lius.yygh.service.hospital.service.HospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "医院设置")
@RestController
@RequestMapping("/admin/hospital/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalService hospitalService;

    // 查询医院所有的信息
    @GetMapping("/findAll")
    @ApiOperation("查找所有的医院")
    public List<HospitalSet> findAll(){
        List<HospitalSet> list = hospitalService.list();
        list.forEach(System.out::println);

        return list;
    }
    // 删除设置
    @DeleteMapping("/{id}")
    @ApiOperation("删除指定id的医院数据")
    public boolean deleteById(@PathVariable long id){
        QueryWrapper<HospitalSet> hospitalSetQueryWrapper = new QueryWrapper<>();
        hospitalSetQueryWrapper.eq("id",id);
        return hospitalService.remove(hospitalSetQueryWrapper);
    }
}
