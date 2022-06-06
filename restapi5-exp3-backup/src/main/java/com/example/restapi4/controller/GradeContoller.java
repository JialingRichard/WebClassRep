package com.example.restapi4.controller;

import com.example.restapi4.entity.Grade;
import com.example.restapi4.common.Projects;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="成绩列表")
@RestController
public class GradeContoller {
    @ApiOperation("查询科目成绩")
    @RequestMapping(value="/grade",method = RequestMethod.GET)
    public Projects getExp() throws Exception {
        Grade g = new Grade();

        return g.getExp();
    }

    @ApiOperation("设置科目成绩")
    @RequestMapping(value="/grade",method = RequestMethod.POST)
    public int setExp(@RequestBody Projects p) throws Exception {
        Grade g = new Grade();
        g.setExp(p);
        return 0;
    }
}
