package ru.iam;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BankEmployee employee = new BankEmployee("Герман", "Греф", "Сбербанк");
        BankCustomer customer = new BankCustomer("Алексей", "Миллер", "Газпромбанк");
        employee.printOut();
        customer.printOut();

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
