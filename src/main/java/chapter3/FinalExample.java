package chapter3;

public class FinalExample {
    int i;
    final int j;

    FinalExample finalExample;

    public FinalExample() {
        i = 1;
        j = 2;
    }

    public void writer() {
        finalExample = new FinalExample();
    }

    public void reader() {

        FinalExample obj = finalExample;
        System.out.println("i=" + obj.i + ",j=" + obj.j);

    }
}
