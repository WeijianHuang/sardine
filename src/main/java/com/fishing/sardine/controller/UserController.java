package com.fishing.sardine.controller;

import com.fishing.sardine.service.UserDaoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijian on 2014/10/29.
 */
@Controller
public class UserController {
        @Autowired
        public UserDaoI userDaoI;

        @RequestMapping("/hello")
        public ModelAndView hello(@RequestParam(value="name", required=false, defaultValue="World") String name) {
            ModelAndView modelAndView = new ModelAndView();
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");
            modelAndView.setViewName("index");
            modelAndView.addObject("list", list);
            System.out.println(name+"-----------------------------");


            return modelAndView;
        }


}
