package com.example.exbeginner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.exbeginner.repository.MemberRepository;



@Controller
@RequestMapping("/ex05")
public class Ex05Controller {
    @Autowired
    private MemberRepository repository;

    @GetMapping({"/index","/index/"})
    public String index() {
        return "Ex05/exam05";
    }

    @PostMapping({"/search","/search/"})
    public String search(String name,Model model) {
        model.addAttribute("members", repository.search(name));
        return "Ex05/exam05-result";
    }
    
    
}
