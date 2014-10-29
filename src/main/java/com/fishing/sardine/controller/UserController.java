package com.fishing.sardine.controller;

import com.fishing.sardine.entity.User;
import com.fishing.sardine.service.UserDaoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by weijian on 2014/10/29.
 */
@Controller
public class UserController {
        @Autowired
        public UserDaoI userDaoI;

        @RequestMapping("/hello")
        public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
            model.addAttribute("name", name);
            System.out.println(name+"-----------------------------");
            User user = new User();
            user.setId(1);
            user.setName("屌丝");
            user.setPassword("123123");
            userDaoI.addUser(user);
            return "index";
        }


}
