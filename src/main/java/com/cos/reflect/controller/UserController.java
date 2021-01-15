package com.cos.reflect.controller;

import com.cos.reflect.ano.RequestMapping;
import com.cos.reflect.dto.JoinDto;
import com.cos.reflect.dto.LoginDto;

public class UserController {

    @RequestMapping("/join")
    public String join(JoinDto dto){
        System.out.println("join");
        return "/";
    }

    @RequestMapping("/login")
    public String login(LoginDto dto){
        System.out.println("login");
        return "/";
    }
}
