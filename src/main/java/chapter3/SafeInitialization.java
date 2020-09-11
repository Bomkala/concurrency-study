package chapter3;

/**
 * @author beimo
 * @date 2020/7/11
 */
public class SafeInitialization {

    private static volatile Instance instance;

    /**
     * 获取对象实例
     * 对象初始化步骤如下：1、分配内存空间 2、初始化对象 3、将对象引用传递给instance
     * 这种方式是线程安全的,因为其使用volatile修饰实例变量可以保证2和3不会被重排序，参考StoreStore内存屏障
     */
    public static Instance getInstance() {
        if(instance == null) {
            synchronized (SafeInitialization.class) {
                if(instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }

}
