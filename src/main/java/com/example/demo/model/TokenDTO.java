package com.example.demo.model;

public class TokenDTO {

    private double value;
    private Character symbol;
    private boolean operator;

    public TokenDTO(double value, Character symbol, boolean operator) {
        this.value = value;
        this.symbol = symbol;
        this.operator = operator;
    }


    public double getValue() {
        return value;
    }

    public Character getSymbol() {
        return symbol;
    }

    public boolean isOperator() {
        return operator;
    }
}
