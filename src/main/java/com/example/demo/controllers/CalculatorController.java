package com.example.demo.controllers;

import com.example.demo.model.Token;
import com.example.demo.services.CalculatorServices;
import com.example.demo.services.CalculatorServicesInterface;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
    CalculatorServicesInterface cal= new CalculatorServices();
    @GetMapping("")
    public double evaluateExpression(ArrayList<Token> tokenList){
        System.out.println(tokenList);
        return cal.getResult(tokenList);
    }
}
