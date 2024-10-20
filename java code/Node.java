package com.ruleengine.ast;

public class Node {
    private String type;      // "operator" for AND/OR, "operand" for conditions
    private Node left;        // Reference to the left child
    private Node right;       // Reference to the right child (for operators)
    private Object value;      // Optional value for operand nodes (e.g., conditions)

    // Constructor for operator nodes
    public Node(String type, Node left, Node right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    // Constructor for operand nodes
    public Node(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    // Default constructor
    public Node() {
        // Empty constructor for cases where you don't need to initialize
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
