package chapter4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author beimo
 * @date 2020/8/18
 */
public class AtomicIntegerTest {

    private static AtomicInteger i = new AtomicInteger(0);


    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Task(),"aaa");
            t.start();
            t.join();
        }
        System.out.println(i.get());
    }


    private static class Task implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 10; j++) {
                i.getAndIncrement();
            }
        }
    }

}
