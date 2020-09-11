package chapter4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * @author beimo
 * @date 2020/7/11
 */
public class MulitiThread {

    public static void main(String[] args) {

        //获取当前运行的所有线程
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        for (int i = 0; i < threadInfos.length / 2; i++) {
            ThreadInfo temp = threadInfos[i];
            threadInfos[i] = threadInfos[threadInfos.length - 1 - i];
            threadInfos[threadInfos.length - 1 - i] = temp;
        }

        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(String.format("线程id:%s,线程名称:%s",
                    threadInfo.getThreadId(), threadInfo.getThreadName()));
        }


    }

}
