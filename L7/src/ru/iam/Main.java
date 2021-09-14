package ru.iam;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Truck truck = new Truck(11000, "KAMAZ‑43255‑69", 'r', 90.0f, 4, 14800);
        truck.outPut();
        truck.newWheels(6);

        Box box = new Box(387);
        Pyramid pyramid = new Pyramid(1,5);
        System.out.println(box.add(pyramid));
        Cylinder cylinder = new Cylinder(5,9);
        System.out.println(box.add(cylinder));
        Ball ball = new Ball(3);
        System.out.println(box.add(ball));
        System.out.println("В коробке: ");
        box.showBox();
    }
}
