package chapter4;

import java.util.concurrent.TimeUnit;

/**
 * @author beimo
 * @date 2020/7/21
 */
public class Shutdown {

    public static void main(String[] args) throws Exception{
        Runner one = new Runner();
        Thread oneThread = new Thread(one,"CountThread---001");
        oneThread.start();
        TimeUnit.SECONDS.sleep(1);
        oneThread.interrupt();

        Runner two = new Runner();
        Thread twoThread = new Thread(two,"CountThread---002");
        twoThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cannel();
    }


    private static class Runner implements Runnable {

        private long i;

        private volatile boolean on = true;

        @Override
        public void run() {
            while(on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cannel() {
            on = false;
        }

    }
}
