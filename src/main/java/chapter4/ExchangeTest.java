package chapter4;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author beimo
 * @date 2020/9/10
 */
public class ExchangeTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

//            String B = "This is B";
//            String exchangeResult = exchanger.exchange(B);
//
//            System.out.println("B = " + B);
//            System.out.println("exchangeResult = " + exchangeResult);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        executorService.execute(() -> {
            String A = "This is A";
            try {
                exchanger.exchange(A);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        executorService.execute(() -> {
            String B = "This is B";
            try {
                String exchangeResult = exchanger.exchange(B);
                System.out.println("B = " + B);
                System.out.println("exchangeResult = " + exchangeResult);

                if(!B.equals(exchangeResult)){
                    System.out.println("两个数据不一致");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });


        executorService.execute(() -> {
            String C = "This is C";
            try {
                exchanger.exchange(C);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        executorService.execute(() -> {
            String D = "This is D";
            try {
                String exchangeResult = exchanger.exchange(D);
                System.out.println("D = " + D);
                System.out.println("exchangeResult = " + exchangeResult);

                if(!D.equals(exchangeResult)){
                    System.out.println("两个数据不一致");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

    }

}
