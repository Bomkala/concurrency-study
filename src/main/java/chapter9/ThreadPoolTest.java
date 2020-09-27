package chapter9;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author beimo
 * @date 2020/9/10
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 30, 1000, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("poolExecutor-%d").build());

        System.out.println("----------预热前----------");
        printThreadPoolInfo(poolExecutor);

        //预热线程池，并打印线程池情况
        poolExecutor.prestartAllCoreThreads();
        System.out.println("----------预热后----------");
        printThreadPoolInfo(poolExecutor);

        //执行任务后，打印线程池情况
        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(() -> {
                String str = "hello world";
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("----------执行5个任务后----------");
        printThreadPoolInfo(poolExecutor);
    }

    private static void printThreadPoolInfo(ThreadPoolExecutor poolExecutor) {
        long taskCount = poolExecutor.getTaskCount();
        long completedTaskCount = poolExecutor.getCompletedTaskCount();
        int largestPoolSize = poolExecutor.getLargestPoolSize();
        int poolSize = poolExecutor.getPoolSize();
        int activeCount = poolExecutor.getActiveCount();

        System.out.println("taskCount=" + taskCount);
        System.out.println("completedTaskCount=" + completedTaskCount);
        System.out.println("largestPoolSize=" + largestPoolSize);
        System.out.println("poolSize=" + poolSize);
        System.out.println("activeCount=" + activeCount);
    }

}
