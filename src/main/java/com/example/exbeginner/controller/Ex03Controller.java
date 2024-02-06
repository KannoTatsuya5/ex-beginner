package com.example.exbeginner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/ex03")
public class Ex03Controller {
    @Autowired
    private ServletContext application;

    @GetMapping({"/index","/index/"})
    public String index() {
        return "Ex03/exam03";
    }

    @PostMapping({"/result","/result/"})
    public String getResult(Integer price1,Integer price2,Integer price3) {
        Integer totalAmount = price1 + price2 + price3;
        Integer includTax = (int) (totalAmount * 1.1);
        application.setAttribute("total", totalAmount);
        application.setAttribute("tax", includTax);
        return "Ex03/exam03-result";
    }
}
