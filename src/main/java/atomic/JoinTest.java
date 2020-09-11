package atomic;

public class JoinTest {

    public static void main(String[] args) throws Exception{
        Thread thread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread joinThread = new Thread(new JoinThread(thread), String.valueOf(i));
            joinThread.start();
            thread = joinThread;
        }
        Thread.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate");
    }

    public static class JoinThread implements Runnable {

        Thread previous;

        JoinThread(Thread previous) {
            this.previous = previous;
        }

        @Override
        public void run() {
            try {
                previous.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate");
        }
    }
}
