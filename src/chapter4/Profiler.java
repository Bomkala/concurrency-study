package chapter4;

import java.util.concurrent.TimeUnit;

public class Profiler {
    public static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };


    public static void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static Long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }


    public static void main(String[] args) throws Exception{
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + "ms");
    }
}
