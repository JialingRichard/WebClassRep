package com.example.restapi4.controller;

import com.example.restapi4.entity.ProExp;
import com.example.restapi4.common.Projects;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="项目经历列表")
@RestController
public class ProExpController {

    @ApiOperation("查询项目经历")
    @RequestMapping(value="/proExp",method = RequestMethod.GET)
    public Projects getExp() throws Exception {
        ProExp pe = new ProExp();

        return pe.getExp();
    }

    @ApiOperation("设置项目经历")
    @RequestMapping(value="/proExp",method = RequestMethod.POST)
    public int setExp(@RequestBody Projects p) throws Exception {
        ProExp pe = new ProExp();
        pe.setExp(p);
        return 0;
    }
}
