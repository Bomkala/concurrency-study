package chapter2;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {


    public static void main(String[] args) {

        Student st1 = new Student("小红", 20);
        AtomicReference<Student> atomicReference = new AtomicReference<Student>(st1);

        Thread t1 = new Thread(() -> {
            Student st2 = new Student("小明", 23);
            boolean b = atomicReference.compareAndSet(st1, st2);
            System.out.println(Thread.currentThread().getName() + "执行结果：" + b);
        },"小明线程");


        Thread t2 = new Thread(() -> {
            Student st2 = new Student("小刚", 27);
            boolean b = atomicReference.compareAndSet(st1, st2);
            System.out.println(Thread.currentThread().getName() + "执行结果：" + b);

        },"小刚线程");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(atomicReference.get());

    }


}


class Student {

    private String name;

    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
