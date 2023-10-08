package com.example.demo.services;



import com.example.demo.model.TokenDTO;

import java.util.ArrayList;

public interface CalculatorServicesInterface {
    double getResult(ArrayList<TokenDTO> tokenList);
}
