package ru.iam;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Vector Vector1 = new Vector(23, 77, -41);
        Vector Vector2 = new Vector(30, -25, 16);

        System.out.println("Длина вектора Vector1: " + Vector1.Vectorlength());
        System.out.println("Длина вектора Vector2: " + Vector2.Vectorlength());
        System.out.println("Скалярное произведение векторов: " + Vector1.scalar(Vector2));
        System.out.println("Векторное произведение: " + Vector1.vectorMulti(Vector2));
        System.out.println("Косинус угла между векторами: " + Vector1.cos(Vector2));
        System.out.println("Сложение векторов: " + Vector1.sum(Vector2));
        System.out.println("Вычитание векторов: " + Vector1.diff(Vector2));

        System.out.println("Введите число N");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Массив случайных векторов размером " + n + ": ");
        Vector[] arrVector = Vector.RandomVectorsArray(n);
        for (int i = 0; i < arrVector.length; i++) {
            System.out.println(arrVector[i]);
        }
    }
}


class Vector {
    private int x;
    private int y;
    private int z;

    public Vector() {
        this.x = this.y = this.z = 0;
    }

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double Vectorlength() {
        int x = this.x;
        int y = this.y;
        int z = this.z;
        double len = Math.abs(Math.sqrt(x * x + y * y + z * z));
        len = Math.round(len * 100.0) / 100.0;
        return len;
    }

    public int scalar(Vector vector) {
        int x = this.x;
        int y = this.y;
        int z = this.z;
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public Vector vectorMulti(Vector vector) {
        Vector v = new Vector();
        v.x = this.y * vector.z - this.z * vector.y;
        v.y = this.z * vector.x - this.x * vector.z;
        v.z = this.x * vector.y - this.y * vector.x;
        return v;
    }

    public double cos(Vector vector) {
        double cos = this.scalar(vector) / (this.Vectorlength() * vector.Vectorlength());
        cos = Math.round(cos * 100.0) / 100.0;
        return cos;
    }

    public Vector sum(Vector vector) {
        Vector v = new Vector();
        v.x = this.x + vector.x;
        v.y = this.y + vector.y;
        v.z = this.z + vector.z;
        return v;
    }
    public Vector diff(Vector vector) {
        Vector v = new Vector();
        v.x = this.x - vector.x;
        v.y = this.y - vector.y;
        v.z = this.z - vector.z;
        return v;
    }

    public static Vector[] RandomVectorsArray(int n) {
        Vector[] arr = new Vector[n];
        for (int i = 0; i < n; i++) {
            Vector vector = new Vector();
            while (vector.Vectorlength() != n) {
                vector.x = (int)(Math.random() * 20);
                vector.y = (int)(Math.random() * 20);
                vector.z = (int)(Math.random() * 20);
            }
            arr[i] = vector;
        }
        return arr;
    }

    @Override
    public String toString() {
        return "x=" + x +  ", y=" + y +  ", z=" + z;
    }
}
