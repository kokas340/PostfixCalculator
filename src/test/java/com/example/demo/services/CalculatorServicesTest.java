package com.example.demo.services;

import com.example.demo.model.TokenDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServicesTest {

    @Test
    public void testValidExpression() {
        CalculatorServices calculatorServices = new CalculatorServices();

        // Create an example token list for a valid expression: "2 3 + 4 *"
        ArrayList<TokenDTO> tokenList = new ArrayList<>();
        tokenList.add(new TokenDTO(2.0, null, false));
        tokenList.add(new TokenDTO(3.0, null, false));
//        tokenList.add(new TokenDTO(null, '+', true));
        tokenList.add(new TokenDTO(4.0, null, false));
//        tokenList.add(new TokenDTO(null, '*', true));

        double result = calculatorServices.getResult(tokenList);

        // Assert that the result is as expected
        assertEquals(20.0, result);
    }

    @Test
    public void testInvalidExpression() {
        CalculatorServices calculatorServices = new CalculatorServices();

        // Create an example token list for an invalid expression: "2 0 /"
        ArrayList<TokenDTO> tokenList = new ArrayList<>();
        tokenList.add(new TokenDTO(2.0, null, false));
        tokenList.add(new TokenDTO(0.0, null, false));
//        tokenList.add(new TokenDTO(null, '/', true));

        // Assert that an ArithmeticException is thrown when dividing by zero
        assertThrows(ArithmeticException.class, () -> calculatorServices.getResult(tokenList));
    }
}
