package chapter2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {

    private static AtomicStampedReference<String> reference;

    public static void main(String[] args) throws Exception{
        String aaa = "aaa";
        String bbb = "bbb";

        reference= new AtomicStampedReference<String>(aaa,1);



        Thread t1 = new Thread(()->{
            reference.compareAndSet(aaa,bbb,reference.getStamp(),reference.getStamp() +1);
            reference.set(aaa,reference.getStamp()+1);
            printSelf();
            System.out.println(Thread.currentThread().getName() + "执行完毕");
            printSelf();
        },"线程1");


        Thread t2 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean ccc = reference.compareAndSet(aaa, "ccc", 1, 2);
            System.out.println("替换结果：" + ccc);
            printSelf();
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        },"线程2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void printSelf() {
        System.out.println("------------------------");
        System.out.println("当前对象的值为：" + reference.getReference());
        System.out.println("当前对象的版本戳为：" + reference.getStamp());
        System.out.println("------------------------");

    }
}
