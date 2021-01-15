package com.cos.reflect.dto;


import org.jetbrains.annotations.Nullable;

public class LoginDto {
    @Nullable   //리플렉션에서 인식 못함
    //@Deprecated //리플렉션에서 인식함 -> Policy가 런타임
    private String username;
    private String password;
    private int age;

    @Override
    public String toString() {
        return "LoginDto [username=" + username + ", password=" + password + "]";
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
