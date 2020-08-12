package chapter4;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author beimo
 * @date 2020/8/12
 */
public class CacheDemo {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static HashMap<Object, Object> map = new HashMap<>();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();


    //获取指定元素
    public  Object get(Object key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    //替换元素，并返回旧值
    public  Object set(Object key, Object value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public  void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        String key = "111";
        CacheDemo cacheDemo = new CacheDemo();
        cacheDemo.set(key,"helloworld");

        System.out.println(String.format("第一次获取元素,key:%s,value:%s",key,cacheDemo.get(key)));
        cacheDemo.clear();
        System.out.println(String.format("清空后获取元素,key:%s,value:%s",key,cacheDemo.get(key)));

    }

}
