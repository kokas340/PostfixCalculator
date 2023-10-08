package com.example.demo.model;

public class Operand implements Token{
    private double value;
    public Operand(double value) {
        this.value = value;
    }
    @Override
    public boolean isOperator() {
        return false;
    }
    @Override
    public double getValue() {
        return value;
    }
    @Override
    public char getSymbol() {
        throw new UnsupportedOperationException("Operands do not have a char value");
    }
}
