package ru.iam;

public class Truck extends Car{
    private int wheels;
    private int maxWeight;

    public Truck(int w, String m, char c, float s, int wheels, int maxWeight) {
        super(w, m, c, s);
        this.wheels = wheels;
        this.maxWeight = maxWeight;
    }

    public void newWheels(int w) {
        this.wheels = w;
        System.out.println("Количество колёс - " + this.wheels);
    }
}
