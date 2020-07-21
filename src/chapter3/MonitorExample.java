package chapter3;

public class MonitorExample {

    int a = 0;

    public synchronized void writer() {
        a++;
    }

    public synchronized int reader() {
        return a;
    }
}
