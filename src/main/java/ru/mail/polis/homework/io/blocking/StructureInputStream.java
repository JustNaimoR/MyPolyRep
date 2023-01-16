package ru.mail.polis.homework.io.blocking;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Вам нужно реализовать StructureInputStream, который умеет читать данные из файла.
 * Читать поля нужно ручками, с помощью массива байт и методов {@link #read()}, {@link #read(byte[])} и так далее
 * 3 тугрика
 */
public class StructureInputStream extends FileInputStream {

    private Structure[] structures;

    public StructureInputStream(File fileName) throws FileNotFoundException {
        super(fileName);
    }

    /**
     * Метод должен вернуть следующую прочитанную структуру.
     * Если структур в файле больше нет, то вернуть null
     */
    public Structure readStructure() throws IOException {
        int sizeOFsize = read();
        if (sizeOFsize == -1)
            // End of the file
            return null;
        int size = 0;
        for (int i = 0; i < sizeOFsize; i++) {
            size += read();
            if (i + 1 != sizeOFsize)
                size <<= 8;
        }
        byte[] byteObj = new byte[size];

        int result = read(byteObj);

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteObj))) {
            return (Structure) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод должен вернуть все структуры, которые есть в файле.
     * Если файл уже прочитан, но возвращается полный массив.
     */
    public Structure[] readStructures() throws IOException {
        getChannel().position(0);

        List<Structure> list = new ArrayList<>();

        Structure cur = readStructure();
        while (cur != null) {
            list.add(cur);
            cur = readStructure();
        }

        if (list.size() != 0)
            return list.toArray(new Structure[0]);
        else
            return new Structure[0];

        /*Structure cur = readStructure();
        while(cur != null) {
            list.add(cur);
            cur = readStructure();
        }

        return list.size() == 0? new Structure[0]: (Structure[]) list.toArray();*/
    }
}