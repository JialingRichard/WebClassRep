package com.example.restapi4.controller;


import com.example.restapi4.entity.BasicInfo;
import com.example.restapi4.common.TempBasicInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags="个人基本信息")
@RestController
public class BasicInfoController {
    @ApiOperation("基本信息获取")
    @RequestMapping(value="/basicInfo",method = RequestMethod.GET)
    public TempBasicInfo getBasicInfo() throws Exception {
        BasicInfo bi = new BasicInfo();
        bi.getBasicInfo();
        TempBasicInfo tbi = new TempBasicInfo();
        tbi.studentNum = bi.getStudentNum();
        tbi.age = bi.getAge();
        tbi.school = bi.getSchool();
        tbi.college = bi.getCollege();
        tbi.name = bi.getName();
        return tbi;
    }


    @ApiOperation("基本信息设置")
    @ResponseBody
    @RequestMapping(value="/basicInfo",method = RequestMethod.POST)
    public int setName(@RequestBody BasicInfo bi) throws Exception {

        bi.setBasicInfo(bi);
        return  0;
    }

}
