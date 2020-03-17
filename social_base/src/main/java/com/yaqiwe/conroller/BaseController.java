package com.yaqiwe.conroller;

import com.yaqiwe.entity.Label;
import com.yaqiwe.service.LanelService;
import entity.PageResoult;
import entity.Resoult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.ResoultUtil;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 16:15
 * @Version 1.0
 */
@RequestMapping("/label")
@RestController
@CrossOrigin
public class BaseController {

    @Autowired
    LanelService lanelS;

    @GetMapping
    public Resoult findAll() {
        return ResoultUtil.success(lanelS.findAll());
    }

    @GetMapping("/{labelId}")
    public Resoult findById(@PathVariable String labelId) {
        return ResoultUtil.success(lanelS.findById(labelId));
    }

    @PostMapping
    public Resoult save(@RequestBody Label label) {
        lanelS.save(label);
        return ResoultUtil.success("插入成功");
    }

    @PutMapping("/{labelId}")
    public Resoult update(@PathVariable String labelId, @RequestBody Label label) {
        label.setId(labelId);
        lanelS.update(label);
        return ResoultUtil.success("更新成功");
    }

    @DeleteMapping("/{labelId}")
    public Resoult deleteById(@PathVariable String labelId) {
        lanelS.deleteById(labelId);
        return ResoultUtil.success("删除成功");
    }

    @PostMapping("/search")
    public Resoult findSearch(@RequestBody Label label) {
        List<Label> labels = lanelS.findSearch(label);
        return ResoultUtil.success(labels);
    }

    @PostMapping("/search/{page}/{size}")
    public Resoult pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> labels = lanelS.pageQuery(label,page,size);
        PageResoult<Label> labelPage = new PageResoult<>(labels.getTotalElements(),labels.getContent());
        return ResoultUtil.success(labelPage);
    }

}
