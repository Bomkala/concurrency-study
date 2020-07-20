package chapter4;

/**
 * @author beimo
 * @date 2020/7/20
 */
public class Interrupted {

    public static void main(String[] args) {
        //sleepThread一直休眠
        Thread sleepThread = new Thread(new SleepRunner(),"sleep_thread");
        sleepThread.setDaemon(true);

        //busyThread一直运行
        Thread busyThread = new Thread(new BusyRunner(),"busy_thread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sleepThread.interrupt();
        busyThread.interrupt();

        //sleep thread休眠过程中被中断，会抛出InterruptedException异常，并复位interrupted标识
        System.out.println("sleep thread interrupterd:"+ sleepThread.isInterrupted());
        System.out.println("busy thread interrupterd:"+ busyThread.isInterrupted());
    }


    static class SleepRunner implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {

        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
