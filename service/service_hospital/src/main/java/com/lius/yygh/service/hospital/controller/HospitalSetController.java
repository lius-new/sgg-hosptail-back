package com.lius.yygh.service.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lius.hospital.model.hosp.HospitalSet;
import com.lius.hospital.vo.hosp.HospitalSetQueryVo;
import com.lius.yygh.common.utils.MD5;
import com.lius.yygh.service.hospital.service.HospitalService;
import com.luis.yygh.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Api(tags = "医院设置")
@RestController
@RequestMapping("/admin/hospital") ///admin/hospital/hospitalSet
public class HospitalSetController {

    @Autowired
    private HospitalService hospitalService;

    // 查询医院所有的信息
    @GetMapping("/hospitalSets")
    @ApiOperation("查找所有的医院")
    public Result<List<HospitalSet>> findAllHospitalSet() {
        List<HospitalSet> list = hospitalService.list();
        return Result.ok(list);
    }

    // 获取医院设置
    @GetMapping("/hospitalSets/{id}")
    @ApiOperation("获取医院设置")
    public Result<HospitalSet> findByIdHospitalSet(@PathVariable long id) {
        HospitalSet byId = hospitalService.getById(id);
        return Result.ok(byId);
    }

    // 删除医院设置数据
    @DeleteMapping("/hospitalSets/{id}")
    @ApiOperation("删除指定id的医院数据")
    public Result<Boolean> removeByIdHospitalSet(@PathVariable long id) {
        QueryWrapper<HospitalSet> hospitalSetQueryWrapper = new QueryWrapper<>();
        hospitalSetQueryWrapper.eq("id", id);
        boolean remove = hospitalService.remove(hospitalSetQueryWrapper);
        return remove ? Result.ok() : Result.fail();
    }

    // 批量删除医院设置
    @DeleteMapping("/hospitalSets")
    @ApiOperation("批量删除医院设置")
    public Result<Boolean> removeBatchHospitalSet(@RequestBody List<Long> idList) {
        boolean b = hospitalService.removeByIds(idList);
        return b ? Result.ok() : Result.fail();
    }

    // 分页查询
    @PostMapping("/hospitalSets/{current}/{limit}") // 改成Get请求无法发送数据,必须是POST
    @ApiOperation("医院设置-分页查询")
    public Result<Page<HospitalSet>> queryByPageOfHospitalSet(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {

        Page<HospitalSet> hospitalSetPage = new Page<>(current, limit); // 指定当前页数和数量

        QueryWrapper<HospitalSet> hospitalSetQueryWrapper = new QueryWrapper<>();
        assert hospitalSetQueryVo != null;
        String hosname = hospitalSetQueryVo.getHosname();
        String hoscode = hospitalSetQueryVo.getHoscode();

        if (!StringUtils.isEmpty(hosname))
            hospitalSetQueryWrapper.like("hosname", hosname);
        if (!StringUtils.isEmpty(hoscode))
            hospitalSetQueryWrapper.eq("hoscode", hoscode);

        Page<HospitalSet> page = hospitalService.page(hospitalSetPage, hospitalSetQueryWrapper);

        return Result.ok(page);
    }

    /**
     * 添加医院设置
     * 注意: status和signKey都应该设置默认的值
     */
    @PostMapping("/hospitalSets")
    @ApiOperation("添加医院设置")
    public Result<Boolean> addHospitalSet(@RequestBody HospitalSet hospitalSet) {
        // 医院设置的几个参数默认的值
        hospitalSet.setStatus(1);
        int randomInt = new Random().nextInt(1000);
        // 签名密钥
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + randomInt));
        boolean save = hospitalService.save(hospitalSet);
        return save ? Result.ok() : Result.fail();
    }

    // 修改医院设置
    @PutMapping("/hospitalSets")
    @ApiOperation("修改医院设置")
    public Result<Boolean> updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean update = hospitalService.updateById(hospitalSet);
        return update ? Result.ok() : Result.fail();
    }

    // 设置医院接口允许对接(本质上就是通过一个字段来控制)
    @PatchMapping("/hospitalSets/{id}/{status}")
    @ApiOperation("医院设置锁定和解锁")
    public Result<Boolean> lockHospitalSet(@PathVariable long id, @PathVariable int status) {
        HospitalSet byId = hospitalService.getById(id);
        byId.setStatus(status);
        boolean b = hospitalService.updateById(byId);
        return b ? Result.ok() : Result.fail();
    }

    // 发布签名密钥
    @PatchMapping("/hospitalSets/{id}")
    @ApiOperation("发布签名密钥")
    public Result<Boolean> sendSingKey(@PathVariable long id){
        HospitalSet byId = hospitalService.getById(id);
        String signKey = byId.getSignKey();
        String hoscode = byId.getHoscode();
        // TODO 发送签名密钥/发送短信
        return Result.ok();
    }


}
