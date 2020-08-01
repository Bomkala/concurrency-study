package chapter4;

public class JoinTest {
    public static void main(String[] args) throws Exception {

        Thread previous = Thread.currentThread();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Demo(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        Thread.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate");

    }

    static class Demo implements Runnable {

        private Thread thread;

        public Demo(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + "terminate");
        }
    }

}
