package org.example.counter;

public class RaceConditionDemo {
    public static void main(String[] args) {
        //싱글톤객체
        Counter counter = new Counter();
        //멀티스레드
        Thread t1 = new Thread(counter,"Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        /**
         * 멀티스레드 환경에서 1개의 객체를 공유하게 되면
         * 예상하지 못한 결과 RaceCondition가 발생할 수 있다.
         * RaceCondition : 여러 스레드 또는 프로세스가 1개의 자원에 동시에 접근하려고 경쟁하는 상태
         * -> Thread safety 하지 않음
         */
    }
}
