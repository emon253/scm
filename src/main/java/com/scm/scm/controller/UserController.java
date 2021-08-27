package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class UserController {

    @RequestMapping("/signin")
    public String loginForm(Model model){
        model.addAttribute("title","Login");
        return "login_form";
    }
  

}
