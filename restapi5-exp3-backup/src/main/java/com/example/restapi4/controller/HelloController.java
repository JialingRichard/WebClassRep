package com.example.restapi4.controller;

import com.example.restapi4.entity.MyUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="控制类测试接口一", description = "Operations about user")
@RestController
public class HelloController {

    @ApiOperation("控制类接口")
    @RequestMapping(value="/user",method = RequestMethod.GET)
    public MyUser hello(){

        return new MyUser();
    }

    @ApiOperation("控制类接口")
    @RequestMapping(value="/user1",method = RequestMethod.POST)
    public int hello1(@RequestBody List<MyUser> list){
        System.out.println("mytime");
        System.out.println(list);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getPassWard()+"  "+list.get(i).getUserName());
        }
        return 1;
    }


    @ApiOperation("控制类接口")
    @RequestMapping(value="/user",method = RequestMethod.POST)
    public String hello2(@ApiParam("用户名参数") @RequestParam String ss){

        return "hello!!"+ss;
    }
}