package com.example.restapi4.controller;

import com.example.restapi4.entity.PrivatePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="个人主页信息")
@RestController
public class PrivatePageController {
    @ApiOperation("主页名称获取")
    @RequestMapping(value="/privatePageName",method = RequestMethod.GET)
    public String getName() throws Exception {
        PrivatePage privatePage = new PrivatePage();
        return  privatePage.getOldUserName();
    }

    @ApiOperation("主页名称设置")
    @RequestMapping(value="/privatePageName",method = RequestMethod.POST)
    public int setName(@RequestParam(value = "newPageName") String newPageName) throws Exception {
        PrivatePage privatePage = new PrivatePage();
        privatePage.getOldUserName();

        return  privatePage.setNewUserName(newPageName);
    }

}
