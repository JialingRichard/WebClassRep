package com.example.restapi4.controller;

import com.example.restapi4.entity.Login;
import com.example.restapi4.common.ReturnPasswd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags="密码校验")
@RestController
public class LoginController {

    @ApiOperation("使用json进行密码校验")
    @ResponseBody
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ReturnPasswd setName(@RequestBody Login l) throws Exception {
        return  l.loginConfirm();
    }

}
