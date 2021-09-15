package ru.iam;

public class BankCustomer extends Person{
    private String bankName;
    public BankCustomer(String name, String surName, String bankName) {
        super(name, surName);
        this.bankName = bankName;
    }

    @Override
    public void printOut() {
        System.out.println(this.getName() + " " + this.getSurName() +
                " клиент банка " + this.bankName + ".");
    }
}
