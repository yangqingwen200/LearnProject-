package com.controller;

import com.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 * @author 阳庆文
 * @version v1.0
 * @date 2017/2/27
 */
@RestController
@Component
public class HelloworldController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/user")
    public User jsonUser() {
        User user = new User();
        user.setId(1);
        user.setAge(20);
        user.setName("yang");
        user.setBorn(new Date());
        return user;
    }

}
