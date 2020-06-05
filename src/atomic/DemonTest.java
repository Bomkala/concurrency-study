package atomic;

import java.util.concurrent.TimeUnit;

public class DemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                System.out.println("后台线程运行结束");
            }
        }, "后台线程");
        thread.setDaemon(true);
        thread.start();
    }
}
