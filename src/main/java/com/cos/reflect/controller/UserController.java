package com.cos.reflect.controller;

import com.cos.reflect.ano.RequestMapping;

public class UserController {

    @RequestMapping("/join")
    public String join(){
        System.out.println("join");
        return "/";
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("login");
        return "/";
    }
}
