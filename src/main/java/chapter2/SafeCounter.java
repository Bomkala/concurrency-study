package chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounter {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private int num = 0;


    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        final SafeCounter safeCounter = new SafeCounter();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j <= 100; j++) {
                    safeCounter.unSafeCount();
                    safeCounter.safeCount11();
                }
            });
            threadList.add(t);
        }

        for (Thread t : threadList) {
            t.start();
        }

        for (Thread t : threadList) {
            t.join();
        }
        System.out.println("num=" + safeCounter.num);
        System.out.println("atomicInteger=" + safeCounter.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }


    private void unSafeCount() {
        num++;
    }



    private void safeCount11() {
        for (;;) {
            int i = atomicInteger.get();
            boolean flag = atomicInteger.compareAndSet(i, ++i);
            if(flag) {
                break;
            }
        }
    }


}
