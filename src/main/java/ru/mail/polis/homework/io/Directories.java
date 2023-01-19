package ru.mail.polis.homework.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Directories {

    /**
     * Реализовать рекурсивное удаление всех файлов и директорий из директории по заданному пути.
     * Метод должен возвращать количество удаленных файлов и директорий.
     * Если директории по существующему пути нет, то возвращаем 0.
     * Написать двумя способами. С использованием File
     * 2 тугрика
     */
    public static int removeWithFile(String path) {
        File mainFile = new File(path);
        if (!mainFile.exists())
            return 0;
        int res = 1;

        if (mainFile.isFile()) {
            if (!mainFile.delete());
        } else if (mainFile.isDirectory()) {
            for (File cur: mainFile.listFiles()) {
                res += removeWithFile(cur.getPath());
            }
            if (!mainFile.delete());
        }
        return res;
    }

    /**
     * С использованием Path
     * 2 тугрика
     */
    public static int removeWithPath(String path) throws IOException {
        Path mainPath = Paths.get(path);

        if (Files.isRegularFile(mainPath)) {
            Files.delete(mainPath);
            return 1;
        } else if (Files.isDirectory(mainPath)) {
            int res = 1;
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(mainPath)) {
                for (Path cur: stream) {
                    if (Files.isRegularFile(mainPath)) {
                        Files.delete(cur);
                        res++;
                    } else if (Files.isDirectory(mainPath)) {
                        res += removeWithPath(cur.normalize().toString());
                    }
                }
            }
            Files.delete(mainPath);
            return res;
        }

        return 0;
    }
}
