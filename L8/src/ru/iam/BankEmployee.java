package ru.iam;

public class BankEmployee extends Person{
    private String bankName;

    public BankEmployee(String name, String surName, String bankName) {
        super(name, surName);
        this.bankName = bankName;
    }

    @Override
    public void printOut() {
        System.out.println(this.getName() + " " + this.getSurName() +
                " сотрудник банка " + this.bankName + ".");
    }
}
