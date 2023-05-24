package org.example.counter;

public class Counter implements Runnable {
    // 상태를 유지하도록 설계
    private int count = 0;  // 상태값

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getValue() {
        return count;
    }

    // 스레드
    @Override
    public void run() {
        // 동기화를 통해 해결
        synchronized (this) {
            this.increment();
            System.out.println("Value for Thread After increment " + Thread.currentThread().getName() + " " + this.getValue()); //1
            this.decrement();
            System.out.println("Value for Thread at last " + Thread.currentThread().getName() + " " + this.getValue()); //0
        }
    }
}
