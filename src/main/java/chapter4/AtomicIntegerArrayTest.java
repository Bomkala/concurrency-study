package chapter4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author beimo
 * @date 2020/8/18
 */
public class AtomicIntegerArrayTest {

    static int[] value = new int[]{1, 2};

    //原子数组会复制一份入参的数组，后续操作都是对新数组做处理
    static AtomicIntegerArray array = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        int num = array.getAndSet(0, 3);
        System.out.println("原有原子数组第一个元素:" + num);
        System.out.println("新的原子数组第一个元素:" + array.get(0));
        System.out.println("初始数组第一个元素:" + value[0]);
    }

}
