package com.ssm.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Think
 * @Date: 2019/3/18
 * @Time: 15:59
 */

@RestController
@RequestMapping("")
public class IndexController extends BaseController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {

        return "Run success !!! Hello World";
    }


}
