package com.sardine.base.user.controller;

import com.sardine.base.user.entity.User;
import com.sardine.base.user.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户模块 view控制层
 * Created by weijian on 2014/10/29.
 */
@Controller
public class UserController {

    @Autowired
    public UserServiceI userServiceI;
    @Autowired
    public Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping("/hello")
    public ModelAndView hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        try {
            User user = new User();
            System.out.println(userServiceI);
            System.out.println( userServiceI.test("cdcsdcdscds")+"---------------------------");
        }catch (Exception e){
            e.printStackTrace();
        }

        modelAndView.setViewName("index");
        modelAndView.addObject("list", list);
        return modelAndView;
    }
    @RequestMapping("login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println( md5PasswordEncoder.encodePassword("111111", "admin"));
        System.out.println(userServiceI);
        String basePath = "www.baidu.com";
        modelAndView.setViewName("index");
        modelAndView.addObject("basePath",basePath);
        return modelAndView;
    }

    @RequestMapping("/test")
    public ModelAndView testQuanXian(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

}
