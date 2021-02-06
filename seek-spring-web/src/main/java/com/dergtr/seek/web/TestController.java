package com.dergtr.seek.web;

import com.dergtr.seek.mybatis.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/test")
    public Integer test(){
        return testMapper.selectCount();
    }



}
