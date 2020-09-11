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

        System.out.println("【预热前】可用线程数为:" + poolExecutor.getPoolSize());
        poolExecutor.prestartAllCoreThreads();
        System.out.println("【预热后】可用线程数为:" + poolExecutor.getPoolSize());


        poolExecutor.execute(()->{
            System.out.println("this is a hehe");
        });

        poolExecutor.getActiveCount();

    }

}
