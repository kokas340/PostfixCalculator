package com.example.demo.controllers;

import com.example.demo.model.TokenDTO;
import com.example.demo.services.CalculatorServices;
import com.example.demo.services.CalculatorServicesInterface;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("calculator")
public class CalculatorController {

    private final CalculatorServicesInterface cal;

    public CalculatorController(CalculatorServices cs) {
        this.cal = cs;
    }
    @PostMapping("")
    public double evaluateExpression(@RequestBody ArrayList<TokenDTO> tokenList){

        return cal.getResult(tokenList);
    }
}
