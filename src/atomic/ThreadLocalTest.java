package atomic;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final void end() {
        System.out.println("总耗时：" + (System.currentTimeMillis() - TIME_THREADLOCAL.get() + "毫秒"));
    }

    public static void main(String[] args) throws Exception{
        begin();
        TimeUnit.SECONDS.sleep(5);
        end();
    }

}
