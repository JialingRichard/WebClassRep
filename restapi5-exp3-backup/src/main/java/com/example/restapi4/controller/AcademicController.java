package com.example.restapi4.controller;

import com.example.restapi4.entity.Academic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags="学术经历")
@RestController
public class AcademicController {
    @ApiOperation("学术经历获取")
    @RequestMapping(value="/academic",method = RequestMethod.GET)
    public String getName() throws Exception {
        Academic ac = new Academic();

        return  ac.getOldAcademic();
    }

    @ApiOperation("学术经历设置")
    @RequestMapping(value="/academic",method = RequestMethod.POST)
    public int setName(@RequestBody Academic ac)throws Exception {
//        Academic ac1 = new Academic();
//        ac1.setAcademicExp(ac.getAcademicExp());
        return  ac.setNewAcademic();
    }

}

