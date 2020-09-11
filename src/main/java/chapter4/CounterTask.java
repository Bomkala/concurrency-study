package chapter4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author beimo
 * @date 2020/8/17
 */
public class CounterTask extends RecursiveTask<Integer> {

    private static final Integer THRESHOLD = 2;//阈值
    private int start;
    private int end;

    public CounterTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;

        //如果任务足够小就直接计算
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            //如果任务超出阈值，则分裂子任务处理
        } else {
            int middle = (start + end) / 2;
            CounterTask leftTask = new CounterTask(start, middle);
            CounterTask rightTask = new CounterTask(middle + 1, end);

            //执行子任务
            leftTask.fork();
            rightTask.fork();

            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //创建任务
        CounterTask counterTask = new CounterTask(1,10);

        //执行任务
        ForkJoinTask<Integer> result = forkJoinPool.submit(counterTask);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
