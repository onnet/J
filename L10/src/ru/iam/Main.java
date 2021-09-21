package ru.iam;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //1.
        System.out.println("1. Написать метод, который на входе получает коллекцию объектов, а возвращает коллекцию уже без дубликатов");
        List<String> strings = List.of("first", "second", "third","first", "second");
        System.out.println(setFilter(strings));

        //2.
        System.out.println("2. Сравнение ArrayList и LinkedList.\n");
        ListTypesCompare();

    }

    static Set<String> setFilter(List<String> str) {
        Set<String> stringsSet = new HashSet<>();
        str.forEach(stringsSet::add);
        return stringsSet;
    }

    private static int random() {
        return new Random().nextInt(1000000);
    }
    public static void ListTypesCompare() {
        int tmp;
        long start_ms;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 1000000; i++) {
            tmp = random();
            arrayList.add(tmp);
            linkedList.add(tmp);
        }

        start_ms = System.nanoTime();
                for (int i = 0; i < 100000; i++) {
            arrayList.get((random()));
        }
        System.out.println("ArrayList operations duration: " + (System.nanoTime() - start_ms) / 1000000);

        start_ms = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get((random()));
        }
        System.out.println("LinkedList operations duration: " + (System.nanoTime() - start_ms) / 1000000);

    }
class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
}
