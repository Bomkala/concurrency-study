package chapter1;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {

    private final Object ALock = new Object();

    private final Object BLock = new Object();


    public static void main(String[] args) {

        new DeadLockDemo().deadLock();
    }


    private void deadLock() {

        Thread t1 = new Thread(() -> {
            synchronized (ALock) {

                System.out.println(Thread.currentThread().getName() + "获取到A锁了");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (BLock) {
                    System.out.println(Thread.currentThread().getName() + "获取到B锁了");
                }

            }
        });

        t1.start();



        Thread t2 = new Thread(() -> {
            synchronized (BLock) {

                System.out.println(Thread.currentThread().getName() + "获取到B锁了");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (ALock) {
                    System.out.println(Thread.currentThread().getName() + "获取到A锁了");
                }

            }
        });

        t2.start();


    }


}
