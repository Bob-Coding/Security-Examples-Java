package com.example.security.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateEndpoint {
    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("dashboard")
    public String getDashboard() {
        return "dashboard";
    }

}
