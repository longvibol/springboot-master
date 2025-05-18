package com.eazybytes.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {

    /*
    @RequestMapping(value = {"/home","/",""})
    public String displayHomepage(){
        return "home.html";
    }
    */
    
    @RequestMapping(value = {"/home","/",""})
    public String displayHomepage(Model model){
        model.addAttribute("username","Long Vibol");
        return "home.html";
    }
}
