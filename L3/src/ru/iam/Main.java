package ru.iam;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        checkIfAscendingArr();
//        arrayInput();
//        swapBorderlements();
//        firstUnique();
//        fibonacci();
        mySortMerge();
    }

    public static void checkIfAscendingArr() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите числа через запятую: ");
        String arrString = scanner.nextLine();
        int[] intArray = Arrays.stream(arrString.split("[,\\s]+"))
                .mapToInt(Integer::parseInt)
                .toArray();
//        System.out.println("Введенный массив: " + Arrays.toString(intArray));
        int[] intArray2 = intArray.clone();
        bubbleSort(intArray2);
//        System.out.println("Массив после сортировки: " + Arrays.toString(intArray2));
        if (ifArraysEqual(intArray, intArray2)) {
            System.out.println("OK");
        } else {
            System.out.println("Please, try again");
        }
    }

    public static void arrayInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Array length: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Input element [" + i + "]: ");
            arr[i] = scanner.nextInt();
        }
        System.out.println("Result: " + Arrays.toString(arr));
    }

    public static void swapBorderlements() {
        int[] arr = createArray();
        System.out.println("Array1 = " + Arrays.toString(arr));
        int arrSize = arr.length;
        int tmp = arr[0];
        arr[0] = arr[arrSize - 1];
        arr[arrSize - 1] = tmp;
        System.out.println("Array2 = " + Arrays.toString(arr));
    }

    public static void firstUnique() {
        int[] arr = createArray();
        System.out.println("Массив для поиска первого уникального элемента: "
                           + Arrays.toString(arr));
        int arrSize = arr.length;
        for (int i = 0; i < arrSize; i++){
            int count = 0;
            int lookupNum = arr[i];
            for (int j=0; j < arrSize; j++) {
                if (arr[j] == lookupNum) count++;
            }
            if (count < 2) {
                System.out.println("First unique: " + lookupNum);
                return;
            }
        }

    }

    public static void fibonacci(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input Number: ");
        int n = scanner.nextInt();

        int x = 1;
        int y = 1;

        for (int i = 2; i < n; i++) {
            y = x + y;
            x = y - x;
        }
        System.out.println("Fibonacci N(" + n + "): " + y);
    }

    public static void bubbleSort(int[] a) {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }

    private static void mySortMerge() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Array length: ");
        int size = scanner.nextInt();
        int[] arr = createArray(size);
        System.out.println("Начальный массив: " + Arrays.toString(arr));
        sortMerge(arr);
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
    }
    private static void sortMerge(int[] arr) {
        int len = arr.length;
        int n = 1; // кратность сравнений (сравнивать по 1-му элем., 2-м ...)
        int shift; // сдвиг в перебираемом массиве
        int two_size; // количество элементов для 2-го массива
        int[] arr2;
        while (n < len) {
            shift = 0;
            while (shift < len) {
                if (shift + n >= len) break;
                two_size = (shift + n * 2 > len) ? (len - (shift + n)) : n;
                arr2 = merge(Arrays.copyOfRange(arr, shift, shift + n),
                        Arrays.copyOfRange(arr, shift + n, shift + n + two_size));
                for (int i = 0; i < n + two_size; ++i)
                    arr[shift + i] = arr2[i]; // замена на отсортированное
                shift += n * 2;
            }
            n *= 2;
        }
    }

    private static int[] merge(int[] arr_1, int[] arr_2) {
        int len_1 = arr_1.length, len_2 = arr_2.length;
        int a = 0, b = 0, len = len_1 + len_2; // a, b - счетчики в массивах
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (b < len_2 && a < len_1) {
                if (arr_1[a] > arr_2[b]) result[i] = arr_2[b++];
                else result[i] = arr_1[a++];
            } else if (b < len_2) {
                result[i] = arr_2[b++];
            } else {
                result[i] = arr_1[a++];
            }
        }
        return result;
    }

    public static boolean ifArraysEqual(int[] a, int[] b) {
        if (a.length == b.length) {
            for (int j = 0; j < a.length; j++) {
                if (a[j] != b[j]) return false;
            }
            return true;
        } else {
            return false;
        }
    }
    public static int[] createArray() {
        int arrSize = 2 + (int) (Math.random() * 10);
        return createArray(arrSize);
    }

    public static int[] createArray(int arrSize) {
        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) arr[i] = (int) (Math.random() * 10);
//        System.out.println("Array created: " + Arrays.toString(arr));
        return arr;
    }
}