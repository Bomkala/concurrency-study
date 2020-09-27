package chapter9;

/**
 * @author beimo
 * @date 2020/9/11
 */
public class CpuTotalTest {

    public static void main(String[] args) {
        int total = Runtime.getRuntime().availableProcessors();
        System.out.println("当前线程的cpu总数为：" + total);
    }

}
