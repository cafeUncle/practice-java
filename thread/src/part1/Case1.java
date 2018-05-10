package part1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Case1 {
    public static void main(String[] args) {

        ExecutorService excutorService = Executors.newCachedThreadPool();

        TaskerC<Integer> tasker = new TaskerC<Integer>();
        TaskerC2<Integer> tasker2 = new TaskerC2<Integer>();

        System.out.println(1);

        Future<Integer> submitResult = excutorService.submit(tasker);
        Future<Integer> submitResult2 = excutorService.submit(tasker2);

        System.out.println(2);

        // 不shutdown的话，进程会一直运行，不会结束    shutdown方法会等待线程执行完毕再结束，所以可提前调用
        excutorService.shutdown();

//        excutorService.shutdownNow();

        System.out.println(excutorService.isShutdown()); // 调用了就会变成true

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(666);
                }
            }
        }).start(); // 或者这样也可以证明get方法是否阻塞所有线程。发现，在子线程执行的两条输出中依然有666输出，所以结果表明只阻塞主线程。

        try {
            System.out.println(3);
            // get方法会阻塞主线程
            System.out.println("task2运行结果" + submitResult2.get());
            System.out.println("如果get阻塞所有线程的话，此时线程2应该结束了,并且尚未输入子线程1正在计算.既然输出了，说明只是阻塞主线程");
            System.out.println("task1运行结果" + submitResult.get());
            System.out.println(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
        } catch (Exception e) {
        }

        System.out.println("所有任务执行完毕");

        // 不shutdown的话，进程会一直运行，不会结束
//        excutorService.shutdown();

    }
}

class TaskerC<T> implements Callable {

    public T call() throws Exception {
        Thread.sleep(3000);
        System.out.println("子线程1在进行计算");
        Thread.sleep(3000);
        Integer sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return (T) sum;
    }
}

class TaskerC2<T> implements Callable {

    public T call() throws Exception {
        Thread.sleep(3000);
        System.out.println("子线程2在进行计算");
        Thread.sleep(3000);
        Integer sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return (T) sum;
    }
}

class TaskerT extends Thread {


}

class TaskerR implements Runnable {

    public void run() {

    }
}
