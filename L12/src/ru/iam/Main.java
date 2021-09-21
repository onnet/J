package ru.iam;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
    // 1. Напишите программу, в которой запускается 10 потоков и каждый из них выводит числа от 0 до 100.
//        for (int i = 0; i < 10; i++) {
//            new PrintTo100Thread().start();
//        }
    // 2. Выведете состояние потока перед его запуском, после запуска и во время выполнения.
//        Thread mySlowThread = new JustSlowThread();
//        System.out.println("Before start: " + mySlowThread.getState());
//        mySlowThread.start();
//        mySlowThread.join();
//        System.out.println("After join: " + mySlowThread.getState());

    // 3. Дан класс Counter......
//        Counter counter = new Counter();
//        for (int i = 0; i < 100; i++) {
//            new MyIncrementer(counter).start();
//        }
//        Thread.sleep(3000);
//        System.out.println(counter.count);
        // 4.
        Object lock = new Object();
        Thread myRunnableThread1 = new Thread(new MyRunnable(lock));
        Thread myRunnableThread2 = new Thread(new MyRunnable(lock));

        myRunnableThread1.start();
        myRunnableThread2.start();

    }

    static class PrintTo100Thread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        }
    }
    static class JustSlowThread extends Thread{
        @Override
        public void run() {
            System.out.println("Beginning of run: " + this.getState());
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End of run: " + this.getState());
        }
    }
}

class Counter {
    volatile int count = 0;
    public void increment() {
        count = count + 1;
    }
    public int getCount() {
        return count;
    }
}

class MyIncrementer extends Thread{

    private Counter counter;

    public MyIncrementer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        synchronized (counter) {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }
    }

}

class MyRunnable implements Runnable {
    private Object lock;

    public MyRunnable(Object lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        while (true) {
           synchronized (lock) {
              lock.notify();
              System.out.println(Thread.currentThread().getName());
              try {
                lock.wait();
                } catch (InterruptedException e) {
                   e.printStackTrace();
              }
           }
        }
    }
}
