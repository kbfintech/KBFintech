package com.spring.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {
   
   @GetMapping("plan/planer")
   public String planer() {
      System.out.println("plan/planer.jsp");
      return "/plan/planer";
   }

}