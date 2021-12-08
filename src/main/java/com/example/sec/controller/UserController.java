package com.example.sec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/v1")
public class UserController {


    @GetMapping("/public/users")
    public String publicUsers() {
        return "public users";
    }

    @GetMapping("/private/admin")
    public String admin() {
        return "admin config";
    }

    @GetMapping("/private/users")
    public String privateUsers() {
        return "users list";
    }
}
