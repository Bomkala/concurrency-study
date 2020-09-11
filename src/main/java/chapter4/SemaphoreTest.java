package chapter4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author beimo
 * @date 2020/9/10
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static Semaphore semaphore = new Semaphore(10);

    private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) {

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.execute(() -> {
                        try {
                            semaphore.acquire();
                            System.out.println(System.currentTimeMillis() + "save data");
                            semaphore.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }

        executor.shutdown();

    }


}
