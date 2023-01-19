package ru.mail.polis.homework.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class CopyFile {

    /**
     * Реализовать копирование папки из pathFrom в pathTo. Скопировать надо все внутренности
     * Файлы копировать ручками через стримы. Используем новый API
     * В тесте для создания нужных файлов для первого запуска надо раскомментировать код в setUp()
     * 3 тугрика
     */
    public static void copyFiles(String pathFrom, String pathTo) throws IOException {
        File from = new File(pathFrom);
        File to = new File(pathTo);

        if (from.isDirectory()) {
            if (!to.exists()) {
                boolean result = to.mkdirs();
                if (!result) {
                    return;
                }
            }

            if (from.listFiles() != null) {
                for (File cur: from.listFiles()) {
                    copyFiles(cur.getPath(), pathTo + "\\" + cur.getName());
//                    if (cur.isFile()) {
//                        copyFiles(cur.getPath(), pathTo + cur.getName());
////                        File newFile = new File(pathTo + cur.getName());
////
////                        if (newFile.createNewFile()) {
////                            try (FileInputStream fis = new FileInputStream(from);
////                                 FileOutputStream fos = new FileOutputStream(newFile)) {
////                                fos.write(fis.readAllBytes());
////                            }
////                        }
//                    } else if (cur.isDirectory()) {
//                        copyFiles(cur.getPath(), );
//                    }
                }
            }
        } else if (from.isFile()) {
            File newFile = new File(pathTo);
            // Создание папки для файла (По другому файл не создать)
            if (!new File(newFile.getParent()).mkdirs());

            if (newFile.createNewFile()) {
                try (FileInputStream fis = new FileInputStream(from);
                        FileOutputStream fos = new FileOutputStream(newFile)) {
                    fos.write(fis.readAllBytes());
                }
            }
        }

         /*
         if (!to.exists()) {
             boolean result = to.mkdirs();
             if (!result) {
                 return;
             }
         }

        if (from.isDirectory() && from.listFiles() != null) {
            for (File fileEntry : Objects.requireNonNull(from.listFiles())) {
                File cur = new File(pathTo + fileEntry.getName());

                if (cur.createNewFile()) {
                    try (FileInputStream fis = new FileInputStream(fileEntry);
                         FileOutputStream fos = new FileOutputStream(cur)) {
                        fos.write(fis.readAllBytes());
                    }
                }
            }
        } else if (from.isFile()) {
            File cur = new File(pathTo);

            if (cur.createNewFile()) {
                try (FileInputStream fis = new FileInputStream(from);
                     FileOutputStream fos = new FileOutputStream(cur)) {
                    fos.write(fis.readAllBytes());
                }
            }
        }
        */
    }

    /*
    public static void main(String[] args) throws IOException {
        File file = new File("src\\test\\resources\\directories\\copy1\\first\\file.txt");
        file.createNewFile();
    }
    */

}