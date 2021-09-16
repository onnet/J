package ru.iam;

public abstract class SolidOfRevolution implements Shape{
    private double radius;
    protected double volume;


    SolidOfRevolution(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
