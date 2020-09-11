package atomic;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo02 {

    public static void main(String[] args) {
        Thread t1 = new ThreadDemo01();
        Thread t2 = new ThreadDemo01();
        System.out.println(Thread.currentThread().getName());
        t1.start();
        t2.start();
    }
    public static class ThreadDemo01 extends Thread {
        public static ReentrantLock lock = new ReentrantLock();
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
