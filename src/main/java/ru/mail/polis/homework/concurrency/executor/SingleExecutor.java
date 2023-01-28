package ru.mail.polis.homework.concurrency.executor;

import java.util.concurrent.*;
/**
 * Нужно сделать свой executor с одним вечным потоком. Пока не вызовут shutdown или shutdownNow
 *
 * Задачи должны выполняться в порядке FIFO
 *
 * Max 6 тугриков
 */
public class SingleExecutor implements Executor {

    private final LinkedBlockingQueue<Runnable> taskDeque = new LinkedBlockingQueue<>();
    private int taskCount = 0;
    private boolean allDone = false;
    private volatile boolean play = true;
    private boolean fallAsleep = false;
    private final Thread singleThread = new Thread(() -> {
        while (play) {

            // Ожидания потоком новой задачи
            while (taskCount == 0);

            if (play) {
                taskCount--;
//                taskDeque.take();
//                taskDeque.pop().run();
            }
            if (fallAsleep) {
                while (taskCount != 0) {
//                    taskDeque.pop().run();
                    taskCount--;
                }

                play = false;
            }

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
        if (!play || fallAsleep)
            throw new RejectedExecutionException();

//        taskDeque.addLast(command);
        taskCount++;
    }

    /**
     * Дает текущим задачам выполниться. Добавление новых - бросает RejectedExecutionException
     * 1 тугрик за метод
     */
    public void shutdown() {
        fallAsleep = true;
        taskCount++;
    }

    /**
     * Прерывает текущие задачи. При добавлении новых - бросает RejectedExecutionException
     * 2 тугрика за метод
     */
    public void shutdownNow() {
        play = false;
        if (taskCount == 0)
            taskCount++;
    }

    public static void main(String[] args) {

        try {
            throw new Exception();
            //System.out.println("1");
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            System.out.println("2");
        }
        System.out.println("3");
    }

}