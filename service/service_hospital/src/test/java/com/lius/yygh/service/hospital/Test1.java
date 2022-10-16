package com.lius.yygh.service.hospital;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lius.hospital.model.hosp.HospitalSet;
import com.lius.yygh.service.hospital.service.HospitalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {

    @Autowired
    HospitalService hospitalService;

    // 查询所有的数据
    @Test
    public void findAll(){
        List<HospitalSet> list = hospitalService.list();
        list.forEach(System.out::println);
    }

    // 删除数据
    @Test
    public void delete(){
        QueryWrapper<HospitalSet> query= new QueryWrapper<>();
        query.eq("hosname", "0000");
        System.out.println(hospitalService.remove(query));//删除数据
    }

    // 添加数据
    @Test
    public void add(){
        HospitalSet hospitalSet = new HospitalSet();
        // HospitalSet(hosname=0000, hoscode=00, apiUrl=00, signKey=458616ff6386801c36c9e57e75061d4a, contactsName=00, contactsPhone=00, status=1)
        hospitalSet.setHosname("0000");
        hospitalSet.setHoscode("000");
        hospitalSet.setApiUrl("00");
        hospitalSet.setSignKey("458616ff6386801c36c9e57e75061d4a");
        hospitalSet.setContactsName("00");
        hospitalSet.setContactsPhone("00");
        hospitalSet.setStatus(1);
        hospitalService.save(hospitalSet);
    }

    // 修改数据
    @Test
    public void update(){
        UpdateWrapper<HospitalSet> hospitalSetUpdateWrapper = new UpdateWrapper<>();
        hospitalSetUpdateWrapper.eq("hosname","0000");

        HospitalSet hospitalSet = new HospitalSet();
        hospitalSet.setHoscode("001"); // 修改为001

        // 更新
        hospitalService.update(hospitalSet,hospitalSetUpdateWrapper);
    }

    // 测试分页查询
    @Test
    public void queryByPage() {
        Page<HospitalSet> hospitalSetPage = new Page<>(0,2);
        QueryWrapper<HospitalSet> hospitalSetQueryWrapper = new QueryWrapper<>();

        Page<HospitalSet> page = hospitalService.page(hospitalSetPage, hospitalSetQueryWrapper);
        List<HospitalSet> records = page.getRecords();
        records.forEach(System.out::println);
    }

}
