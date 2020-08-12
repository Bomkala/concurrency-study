package chapter3;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class RecorderExample {

    int a = 0;
    boolean flag = false;

    public void writer() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a = 1;
        flag = true;
    }

    public void reader() {
        if (flag) {
            int i = a * a;
            System.out.println(i);
        }
    }

    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            RecorderExample recorderExample = new RecorderExample();
            Thread t1 = new Thread(() -> {
                recorderExample.writer();
            });

            Thread t2 = new Thread(() -> {
                recorderExample.reader();
            });

            t2.start();
            t1.start();
        }
    }
}
