package com.yaqiwe.controller;

import com.yaqiwe.entity.Recruit;
import com.yaqiwe.service.RecruitService;
import entity.PageResoult;
import entity.Resoult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.ResoultUtil;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/17 9:57
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    RecruitService recruitS;

    @PostMapping
    public Resoult save(@RequestBody Recruit recruit){
        recruitS.save(recruit);
        return ResoultUtil.success("增加招聘职位成功");
    }

    @GetMapping
    public Resoult findAll(){
        List<Recruit> recruitList= recruitS.findAll();
        return ResoultUtil.success(recruitList);
    }

    @GetMapping("/{recruitId}")
    public Resoult findById(@PathVariable String recruitId){
        return ResoultUtil.success(recruitS.findById(recruitId));
    }

    @PutMapping("/{recruitId}")
    public Resoult update(@PathVariable String recruitId ,@RequestBody Recruit recruit){
        recruit.setId(recruitId);
        recruitS.update(recruit);
        return ResoultUtil.success("更新招聘信息成功");
    }

    @DeleteMapping("/{recruitId}")
    public Resoult deleteById(@PathVariable String recruitId){
        recruitS.deleteById(recruitId);
        return ResoultUtil.success("删除招聘信息成功");
    }

    @PostMapping("/search")
    public Resoult findSearch(@RequestBody Recruit recruit){
        List<Recruit> recruitList= recruitS.findSearch(recruit);
        return ResoultUtil.success(recruitList);
    }

    @PostMapping("/search/{page}/{size}")
    public Resoult pageQuery(@RequestBody Recruit recruit,@PathVariable int page ,@PathVariable int size){
        Page<Recruit> recruitPage=recruitS.pageQuery(recruit,page,size);
        PageResoult pageResoult=new PageResoult(recruitPage.getTotalElements(),recruitPage.getContent());
        return ResoultUtil.success(pageResoult);
    }

    @GetMapping("/search/recommend")
    public Resoult recommend(){
        List<Recruit> recruitList=recruitS.recommend();
        return ResoultUtil.success(recruitList);
    }

    @GetMapping("/search/newList")
    public Resoult newList(){
        return ResoultUtil.success(recruitS.newList());
    }
}
