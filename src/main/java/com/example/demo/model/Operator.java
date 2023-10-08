package com.example.demo.model;

public class Operator implements Token{

    private char symbol;

    public Operator(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public double getValue() {
        throw new UnsupportedOperationException("Operators do not have a numerical value");
    }
}
