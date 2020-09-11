package chapter1;

import java.util.concurrent.TimeUnit;

public class ConcurrencyTest {

    private static long count = 1000000000;

    private static final String PRINT_FORMAT = "---------count:%d---------";

    public static void main(String[] args) throws Exception {

        //由于线程创建和切换上下文的代价，在count较小时并发优势不大。count较大时才体现成多线程的强大之处


        //1000
        count = 1000;
        System.out.println(String.format(PRINT_FORMAT,count));
        concurrency();
        serial();

        //10万
        count = 1000*1000;
        System.out.println(String.format(PRINT_FORMAT,count));
        concurrency();
        serial();

        //10亿
        count = 1000*1000*1000;
        System.out.println(String.format(PRINT_FORMAT,count));
        concurrency();
        serial();

        TimeUnit.SECONDS.sleep(10);


    }


    private static void concurrency() throws InterruptedException {

        long start = System.currentTimeMillis();

        Thread t = new Thread(() -> {

            int a = 0;
            for (int i = 0; i < count; i++) {
                a += 5;
            }
        });


        t.start();

        int b = 0;
        for (int i = 0; i < count; i++) {
            b -= 6;
        }

        t.join();
        System.out.println("concurrency方法总共耗时:" + (System.currentTimeMillis() - start));

    }


    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (int i = 0; i < count; i++) {
            b -= 6;
        }
        System.out.println("serial方法耗时:" + (System.currentTimeMillis() - start));


    }

}
