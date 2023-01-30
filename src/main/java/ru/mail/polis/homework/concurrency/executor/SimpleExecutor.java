package ru.mail.polis.homework.concurrency.executor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Нужно сделать свой executor с ленивой инициализацией потоков до какого-то заданного предела.
 * Ленивая инициализация означает, что если вам приходит раз в 5 секунд задача, которую вы выполняете 2 секунды,
 * то вы создаете только один поток. Если приходит сразу 2 задачи - то два потока.  То есть, если приходит задача
 * и есть свободный запущенный поток - он берет задачу, если такого нет, то создается новый поток.
 *
 * Задачи должны выполняться в порядке FIFO
 * Потоки после завершения выполнения задачи НЕ умирают, а ждут.
 *
 * Max 10 тугриков
 */
public class SimpleExecutor implements Executor {
    private final int maxThreadCount;
    private AtomicInteger freeThreads = new AtomicInteger(0);
    private boolean isActive = true;

    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private final List<Thread> threadList = new CopyOnWriteArrayList<>();

    public SimpleExecutor(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    /**
     * Ставит задачу в очередь на исполнение, если надо - создает новый поток.
     * 8 тугриков
     */
    @Override
    public void execute(Runnable command) {
        if (!isActive)
            throw new RejectedExecutionException();

        if (freeThreads.get() == 0 && threadList.size() < maxThreadCount) {
            synchronized (this) {
                if (!isActive) {
                    return;
                }
                if (freeThreads.get() == 0 && threadList.size() < maxThreadCount) {
                    Thread newThread = new MyThread();
                    threadList.add(newThread);
                    newThread.start();
                }
            }
        }

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
     * 1 тугрик за метод
     */
    public void shutdownNow() {
        isActive = false;
        for (Thread thread : threadList) {
            thread.interrupt();
        }
    }

    /**
     * Должен возвращать количество созданных потоков.
     */
    public int getLiveThreadsCount() {
        return threadList.size();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            freeThreads.incrementAndGet();
            while (!Thread.currentThread().isInterrupted() && (isActive || taskQueue.size() != 0)) {

                try {

                    Runnable task = taskQueue.take();
                    freeThreads.decrementAndGet();
                    task.run();
                    freeThreads.incrementAndGet();

                } catch (InterruptedException e) {}

            }
        }
    }

}