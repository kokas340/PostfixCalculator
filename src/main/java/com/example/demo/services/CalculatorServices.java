package com.example.demo.services;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class CalculatorServices implements CalculatorServicesInterface{
    @Override
    public double getResult(ArrayList<TokenDTO> tokenList) {
        LinkedListStack stack = new LinkedListStack();

        for (TokenDTO token : tokenList) {
            if (token.isOperator()) {
                char operator = token.getSymbol();
                double operand2 = stack.pop().getValue();
                double operand1 = stack.pop().getValue();
                Token result = new Operand(performOperation(operand1, operand2, operator));
                stack.push(result);
            } else {
                Token number = new Operand(token.getValue());
                stack.push(number);
            }
        }

        return stack.pop().getValue();
    }
    private double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
