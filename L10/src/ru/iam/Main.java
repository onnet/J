package ru.iam;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //1.
        System.out.println("1. Написать метод, который на входе получает коллекцию объектов, а возвращает коллекцию уже без дубликатов");
        List<String> strings = List.of("first", "second", "third", "first", "second");
        System.out.println(setFilter(strings));

        //2.
        System.out.println("2. Сравнение ArrayList и LinkedList.\n");
        ListTypesCompare();

        //3.
        System.out.println("3. Создайте Map (Map<User, Integer>).\n");
        Map<User, Integer> myUsersMap = Map.of(
                new User("Ivan"), 5,
                new User("Petr"), 4,
                new User("Vasiliy"), 3,
                new User("Luka"), 4
        );
        System.out.print("Input name (ex. Ivan): ");
        Scanner console = new Scanner(System.in);
        String username = console.nextLine();
        Optional SearchResult = myUsersMap.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getKey().getName(), username))
                .map(Map.Entry::getValue)
                .findFirst();

        if (SearchResult.isPresent()) {
            System.out.println("\nUser " + username + " score is " + SearchResult.get().toString());
        } else {
            System.out.println("\nSorry, no user found");
        }

        //4.
        System.out.println("\n4. Вернуть нужно объект Map<K, Integer>.\n");
        String array[] = { "Пиво", "Вино", "Водка", "Водка", "Водка", "Газировка", "Газировка" };
        Map<String, Long> mymap = convertArrayToMap(array);
        System.out.println("MyMap: " + mymap);
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

    static class User {
        User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

    }

    public static <T> Map<T,Long> convertArrayToMap(T array[])
    {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, array);

        Map<T,Long> map = set.stream()
//       Map<T,T> map = Set.of(array).stream()
                .collect(
//          Collectors.toMap(x -> x, x -> x, (oldValue, newValue) -> newvalue)
                        Collectors.toMap(x -> x, x ->
                                Arrays.stream(array).filter(i -> i == x).count()
                        )
                );

        return map;
    }
}
