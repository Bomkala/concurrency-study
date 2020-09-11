package chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author beimo
 * @date 2020/7/11
 */
public class ThreadPriorityTest {

    private static boolean notStart = true;

    private static boolean notEnd = true;


    public static void main(String[] args) throws Exception {
        List<Job> jobList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobList.add(job);

            Thread t = new Thread(job, "Thread---" + i);
            t.start();
        }

        //线程开始执行count++
        notStart = false;

        //放开10秒
        TimeUnit.SECONDS.sleep(3);

        //设置停止标识
        notEnd = false;

        //打印执行结果
        for (Job job : jobList) {
            System.out.println("Job Priority:" + job.priority + ",Job count:" + job.JobCount);
        }


    }


    static class Job implements Runnable {

        private int priority;

        private int JobCount;

        public Job(int prioroty) {
            this.priority = prioroty;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }

            while (notEnd) {
                Thread.yield();
                JobCount++;
            }


        }
    }

}


