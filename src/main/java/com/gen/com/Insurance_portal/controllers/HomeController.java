package com.gen.com.Insurance_portal.controllers;

import com.gen.com.Insurance_portal.services.IRoleService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Hidden
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/home")
public class HomeController {

    private final IRoleService roleService;

    public HomeController(IRoleService roleService) {
        this.roleService = roleService;
    }


    @RequestMapping("/swagger")
    public String home() {
        return "redirect:/swagger-ui.html";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping
    public String hello() {
        return "Welcome to here!";
    }



}
