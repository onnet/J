package ru.iam;

public class Ball extends SolidOfRevolution{

    Ball(double radius) {
        super(radius);
    }

    @Override
    public double getVolume() {
        return 4.0 / 3 * Math.PI * Math.pow(this.getRadius(), 3);
    }
}
