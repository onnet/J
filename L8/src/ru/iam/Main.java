package ru.iam;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BankEmployee employee = new BankEmployee("Герман", "Греф", "Сбербанк");
        BankCustomer customer = new BankCustomer("Алексей", "Миллер", "Газпромбанк");
        employee.printOut();
        customer.printOut();
    }
}
