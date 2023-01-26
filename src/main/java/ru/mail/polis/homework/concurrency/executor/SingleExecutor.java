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

    private final LinkedBlockingDeque<Runnable> taskDeque = new LinkedBlockingDeque<>();
    private int taskCount = 0;
    private boolean allDone = false;
    private boolean play = true;
    private boolean fallAsleep = false;
    private final Thread singleThread = new Thread(() -> {
        while (play) {

            // Ожидания потоком новой задачи
            while (taskCount == 0);

            if (play) {
                taskCount--;
                taskDeque.pop().run();
            }
            if (fallAsleep) {
                while (taskCount != 0) {
                    taskDeque.pop().run();
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

        taskDeque.addLast(command);
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

        SingleExecutor executor = new SingleExecutor();
        Runnable task1 = () -> System.out.println("Task1!");
        Runnable task2 = () -> {
            try {
                Thread.sleep(1_000);
                System.out.println("Task2 Done!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable task3 = () -> System.out.println("Task3!");
        Runnable task4 = () -> System.out.println("Task4!");

        //executor.execute(task1);
        executor.execute(task2);
        executor.shutdownNow();
        System.out.println("Done!");
    }

}