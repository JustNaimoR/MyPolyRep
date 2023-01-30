package ru.mail.polis.homework.concurrency.executor;

import java.util.Queue;
import java.util.concurrent.*;
/**
 * Нужно сделать свой executor с одним вечным потоком. Пока не вызовут shutdown или shutdownNow
 *
 * Задачи должны выполняться в порядке FIFO
 *
 * Max 6 тугриков
 */
public class SingleExecutor implements Executor {

    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private volatile boolean isActive = true;
    private final Thread singleThread = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted() && (isActive || taskQueue.size() != 0)
                /*!Thread.currentThread().isInterrupted() || (fallAsleep && taskQueue.size() != 0)*/ ) {

                try {
                    taskQueue.take().run();
                } catch (InterruptedException e) {}

        }
    });

    // Запуск потока при создании экземпляра класса
    public SingleExecutor() {
        singleThread.start();
    }

    /**
     * Метод ставит задачу в очередь на исполнение.
     * 3 тугрика за метод
     */
    @Override
    public void execute(Runnable command) {
        if (!isActive)
            throw new RejectedExecutionException();

        taskQueue.add(command);
    }

    /**
     * Дает текущим задачам выполниться. Добавление новых - бросает RejectedExecutionException
     * 1 тугрик за метод
     */
    public void shutdown() {
        isActive = false;
    }

    /**
     * Прерывает текущие задачи. При добавлении новых - бросает RejectedExecutionException
     * 2 тугрика за метод
     */
    public void shutdownNow() {
        isActive = false;
        singleThread.interrupt();
    }

//    public static void main(String[] args) throws InterruptedException {
//        SingleExecutor singleExecutor = new SingleExecutor();
//        for (int i = 0; i < 10; i++) {
//            if (i < 2) {
//                singleExecutor.taskQueue.add(() -> {});
//            } else {
//                System.out.println(singleExecutor.taskQueue.take());
//            }
//
//            System.out.println("Attempt " + i);
//        }
//
//        System.out.println("This is it!");
//    }

}