package chapter3;

public class FinalReferenceEscapleExample {

    final int i;
    static FinalReferenceEscapleExample obj;

    public FinalReferenceEscapleExample() {
        i = 1;
        obj = this;
    }

    public static void writer() {
        new FinalReferenceEscapleExample();
    }

    public static void read() {
        if(obj != null) {
            int temp = obj.i;
            System.out.println(temp);
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(()->writer());

        Thread t2 = new Thread(()->read());
        t2.start();
        t1.start();
    }
}
