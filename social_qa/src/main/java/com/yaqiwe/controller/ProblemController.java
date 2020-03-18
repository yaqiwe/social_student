package com.yaqiwe.controller;

import com.yaqiwe.entity.Problem;
import com.yaqiwe.service.ProblemService;
import entity.PageResoult;
import entity.Resoult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import util.ResoultUtil;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/18 14:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {

    @Autowired
    ProblemService problemS;

    @PostMapping
    @Transactional
    public Resoult save(@RequestBody Problem problem) {
        problemS.save(problem);
        return ResoultUtil.success("发布问题成功");
    }

    @GetMapping
    public Resoult findAll() {
        return ResoultUtil.success(problemS.findAll());
    }

    @GetMapping("/{problemId}")
    public Resoult findById(@PathVariable String problemId) {
        return ResoultUtil.success(problemS.findById(problemId));
    }

    @PutMapping("/{problemId}")
    public Resoult update(@PathVariable String problemId, @RequestBody Problem problem) {
        problem.setId(problemId);
        problemS.update(problem);
        return ResoultUtil.success("问题信息更新成功");
    }

    @DeleteMapping("/{problemId}")
    public Resoult deleteById(@PathVariable String problemId) {
        problemS.deleteById(problemId);
        return ResoultUtil.success("成功删除问题");
    }

    @PostMapping("/search")
    public Resoult findSearch(@RequestBody Problem problem) {
        List<Problem> list = problemS.findSearch(problem);
        return ResoultUtil.success(list);
    }


    @PostMapping("/search/{page}/{size}")
    public Resoult pageQuery(@RequestBody Problem problem, @PathVariable int page, @PathVariable int size) {
        Page<Problem> problemPage = problemS.pageQuery(problem,page,size);
        PageResoult pageRequest=new PageResoult(problemPage.getTotalElements(),problemPage.getContent());
        return ResoultUtil.success(pageRequest);
    }

    @GetMapping("/newList/{labelId}/{page}/{size}")
    public Resoult newList(@PathVariable String labelId, @PathVariable int page,@PathVariable int size){
        Page<Problem> problemPage = problemS.newList(labelId,page,size);
        return ResoultUtil.success(new PageResoult(problemPage.getTotalElements(),problemPage.getContent()));
    }

    @GetMapping("/hotList/{labelId}/{page}/{size}")
    public Resoult hotList(@PathVariable String labelId, @PathVariable int page,@PathVariable int size){
        Page<Problem> problemPage = problemS.hotList(labelId,page,size);
        return ResoultUtil.success(new PageResoult(problemPage.getTotalElements(),problemPage.getContent()));
    }

    @GetMapping("/witList/{labelId}/{page}/{size}")
    public Resoult witList(@PathVariable String labelId, @PathVariable int page,@PathVariable int size){
        Page<Problem> problemPage = problemS.witList(labelId,page,size);
        return ResoultUtil.success(new PageResoult(problemPage.getTotalElements(),problemPage.getContent()));
    }
}
