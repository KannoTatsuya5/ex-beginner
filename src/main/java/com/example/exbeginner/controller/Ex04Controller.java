package com.example.exbeginner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.exbeginner.form.UserForm;
import com.example.exbeginner.model.User;


@Controller
@RequestMapping("/ex04")
public class Ex04Controller {
    
    @GetMapping({"/index","/index/"})
    public String index(Model model, UserForm form) {
        return "Ex04/exam04";
    }

    @PostMapping({"/result","/result/"})
    public String result(@Validated UserForm form, BindingResult result, Model model) {
        
        if(result.hasErrors()) {
            return index(model,form);
        }
        User user = new User();
        user.setName(form.getName());
        user.setAge(form.getAge());
        user.setComment(form.getComment());
        model.addAttribute("user", user);
        return "Ex04/exam04-result";
    }
    
}
