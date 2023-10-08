package com.example.demo.model;

public class LinkedListStack {
    private Node top;
    public void push(Token item) {
        Node newNode = new Node(item);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }
    public Token pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        Token data = top.data;
        top = top.next;
        return data;
    }
    public Token peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }
}
