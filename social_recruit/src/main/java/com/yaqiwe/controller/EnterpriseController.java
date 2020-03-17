package com.yaqiwe.controller;

import com.yaqiwe.entity.Enterprise;
import com.yaqiwe.service.EnterpriseService;
import entity.PageResoult;
import entity.Resoult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import util.ResoultUtil;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/16 21:15
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseS;

    @PostMapping
    public Resoult save(@RequestBody Enterprise enterprise) {
        enterpriseS.save(enterprise);
        return ResoultUtil.success("添加企业成功");
    }

    @GetMapping
    public Resoult findAll() {
        return ResoultUtil.success(enterpriseS.findAll());
    }

    @GetMapping("/{enterpriseId}")
    public Resoult findById(@PathVariable String enterpriseId) {
        return ResoultUtil.success(enterpriseS.findById(enterpriseId));
    }

    @PutMapping("/{enterpriseId}")
    public Resoult update(@PathVariable String enterpriseId, @RequestBody Enterprise enterprise) {
        enterprise.setId(enterpriseId);
        enterpriseS.update(enterprise);
        return ResoultUtil.success("企业信息更新成功");
    }

    @DeleteMapping("/{enterpriseId}")
    public Resoult deleteById(@PathVariable String enterpriseId) {
        enterpriseS.deleteById(enterpriseId);
        return ResoultUtil.success("成功删除企业信息");
    }

    @PostMapping("/search")
    public Resoult findSearch(@RequestBody Enterprise enterprise) {
        List<Enterprise> list = enterpriseS.findSearch(enterprise);
        return ResoultUtil.success(list);
    }

    @GetMapping("/search/hotList")
    public Resoult hotList() {
        return ResoultUtil.success(enterpriseS.hotList());
    }

    @PostMapping("/search/{page}/{size}")
    public Resoult pageQuery(@RequestBody Enterprise enterprise, @PathVariable int page, @PathVariable int size) {
        Page<Enterprise> enterprisePage = enterpriseS.pageQuery(enterprise,page,size);
        PageResoult pageRequest=new PageResoult(enterprisePage.getTotalElements(),enterprisePage.getContent());
        return ResoultUtil.success(pageRequest);
    }
}
