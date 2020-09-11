package chapter3;

/**
 * @author beimo
 * @date 2020/7/11
 */
public class UnsafeLazyInitialization {


    private static Instance instance;


    //线程不安全
    public static Instance getInstanceUnsafely() {

        if (instance == null) {
            instance = new Instance();
        }
        return instance;
    }

    //线程安全
    public synchronized static Instance getInstanceSynchronized() {
        if (instance == null) {
            instance = new Instance();
        }

        return instance;
    }

    /**
     * 双重检查(这种初始化方法也会存在线程安全问题)
     * instance = new Instance();包含1、分配内存空间 2、初始化对象 3、将对象引用传递给instance三个步骤。
     * 其中2和3会出现重排序，导致对象引用传递后但是对象没有初始化完成。
     * 其他线程有可能获取未完全初始化的对象并使用，从而出现空指针问题
     */
    public synchronized static Instance doubleCheck() {

        if (instance == null) {
            synchronized (UnsafeLazyInitialization.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }


}
