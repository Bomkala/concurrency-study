package chapter4;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author beimo
 * @date 2020/8/18
 */
public class AtomicReferenceTest {

    private static AtomicReference<User> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user1 = new User("小明", 15);
        atomicReference.set(user1);

        User user2 = new User("小红", 18);
        atomicReference.compareAndSet(user1, user2);
        System.out.println(atomicReference.get());
    }


    static class User {

        private String name;
        private Integer age;

        public User(String name, Integer age) {
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
