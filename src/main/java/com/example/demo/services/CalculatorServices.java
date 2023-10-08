package com.example.demo.services;

import com.example.demo.model.LinkedListStack;
import com.example.demo.model.Operand;
import com.example.demo.model.Operator;
import com.example.demo.model.Token;


import java.util.ArrayList;

public class CalculatorServices implements CalculatorServicesInterface{
    @Override
    public double getResult(ArrayList<Token> tokenList) {
        LinkedListStack stack = new LinkedListStack();
        ArrayList<Token> tokenList1 = new ArrayList<>();
        tokenList1.add(new Operand(2));
        tokenList1.add(new Operand(5));
        tokenList1.add(new Operand(1));
        tokenList1.add(new Operator('+'));
        tokenList1.add(new Operator('+'));
        for (Token token : tokenList1) {
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
