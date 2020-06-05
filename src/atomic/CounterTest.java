package atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterTest {
    AtomicInteger atomicNum = new AtomicInteger(0);
    int num = 0;

    public static void main(String[] args) {

        final CounterTest counterTest = new CounterTest();


        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for(int j = 0; j < 100; j++) {
                    counterTest.add();
                    counterTest.atomicAdd();
                }
            });
            threadList.add(thread);
        }

        for (Thread t:threadList) {
            t.start();
        }

        for (Thread t:threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("运行结束，atomicNum=%s,num=%s",counterTest.atomicNum,counterTest.num));

    }

    private void add() {
        num++;
    }

    private void atomicAdd() {
        for (;;) {
            int i = atomicNum.get();
            boolean flag = atomicNum.compareAndSet(i, ++i);
            if(flag) {
                break;
            }

        }
    }
}
