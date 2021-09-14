package ru.iam;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Study study = new Study("Изучение Java - это просто!");
        System.out.println(study.printCourse());
        System.out.println();

        House house1 = new House();
        house1.setFloors(18);
        house1.setYear(2010);
        house1.setName("жилой дом");
        System.out.println("Характеристики дома: ");
        System.out.println("\tколичество этажей: " + house1.getFloors());
        System.out.println("\tгод постройки: " + house1.getYear());
        System.out.println("\tнаименование: " + house1.getName());
        System.out.println("\tколичество лет с момента постройки: " + house1.ageHouse());
        House house2 = new House();
        house2.setHouse(70, 1933, "Rockefeller Plaza");
        System.out.println("Характеристики дома: " + house2.toString());
        System.out.println("Лет с момента постройки: " + house2.ageHouse());
        System.out.println();

        Tree tree1 = new Tree(15, "Сосна");
        Tree tree2 = new Tree(21, true, "Рябина");
        Tree tree3 = new Tree();
        System.out.println();

        Airplane a1 = new Airplane();
        Airplane.Wing w1 = a1.new Wing();
        w1.setWeight(5);
        Airplane a2 = new Airplane();
        Airplane.Wing w2 = a2.new Wing();
        w2.setWeight(4);
        System.out.println("Крыло первого самолета весит: " + w1.getWeight() + " т.");
        System.out.println("Крыло второго самолета весит: " + w2.getWeight() + " т.");
    }
}
