import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个可重用固定线程数的线程池
        // ExecutorService pool = Executors.newFixedThreadPool(5);

        // 创建一个单任务线程池
        // ExecutorService pool = Executors.newSingleThreadExecutor();

        // 创建一个可变尺寸的线程池
        // ExecutorService pool = Executors.newCachedThreadPool();

        // 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 100; i++) {
            // pool.execute(new MyThread(i + 1));
            pool.schedule(new MyThread(i + 1), i * (i + 1) / 2, TimeUnit.SECONDS);
        }

        // 关闭线程池
        pool.shutdown();
    }
}

class MyThread extends Thread {

    private int taskId;

    public MyThread(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("线程：" + Thread.currentThread().getName() + "正在执行任务，任务编号：" + taskId + ", current time is: "
                + new Date().getMinutes() + ":" + new Date().getSeconds());
    }
}
