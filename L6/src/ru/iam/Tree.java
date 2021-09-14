package ru.iam;

public class Tree {
    private int age;
    private boolean isAlive;
    private String name;

    public Tree() {
        System.out.println("Работает конструктор арностью 0");
    }

    public Tree(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Tree(int age, boolean isAlive, String name) {
        this.age = age;
        this.isAlive = isAlive;
        this.name = name;
    }
}
