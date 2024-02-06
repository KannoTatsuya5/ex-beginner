package com.example.exbeginner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ex02")
public class Ex02Controller {
    @Autowired
    private HttpSession session;

    @GetMapping({"/index","/index/"})
    public String index() {
        return "Ex02/exam02";
    }

    @PostMapping({"/result","/result/"})
    public String result(Integer num1, Integer num2) {
        Integer addNumber = num1 + num2;
        session.setAttribute("num1", num1);
        session.setAttribute("num2", num2);
        session.setAttribute("addNumber", addNumber);
        return "Ex02/exam02-result";
    }

    @GetMapping({"/result2","/result2/"})
    public String result() {
        return "Ex02/exam02-result2";
    }
}
