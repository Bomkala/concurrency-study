package chapter4;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author beimo
 * @date 2020/8/18
 */
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<MyUser> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(
            MyUser.class,"age");

    public static void main(String[] args) {

        MyUser myUser = new MyUser("小王",15);
        atomicIntegerFieldUpdater.getAndIncrement(myUser);
        System.out.println(myUser.getAge());

        System.out.println(UUID.randomUUID().toString().replace("-",""));

    }


    static class MyUser {

        private String name;
        public volatile int age;

        public MyUser(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
