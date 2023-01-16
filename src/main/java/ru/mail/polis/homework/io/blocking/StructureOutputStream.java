package ru.mail.polis.homework.io.blocking;

import java.io.*;
import java.util.Arrays;

/**
 * Вам нужно реализовать StructureOutputStream, который умеет писать данные в файл.
 * Писать поля нужно ручками, с помощью массива байт и методов {@link #write(int)}, {@link #write(byte[])} и так далее
 * 3 тугрика
 */
public class StructureOutputStream extends FileOutputStream {

    public StructureOutputStream(File name) throws FileNotFoundException {
        super(name);
    }

    /**
     * Метод должен вернуть записать прочитанную структуру.
     */
    public void write(Structure structure) throws IOException {
        byte[] byteObj;

        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(structure);
            }
            byteObj = b.toByteArray();
        }

        // Записываем количество занимаемых байт под размер объекта
        int kBytes = byteObj.length / 256 + (byteObj.length % 256 != 0? 1: 0);
        write(kBytes);
        // Записываем размер объекта
        for (int i = 0; i < kBytes; i++) {
            write((byteObj.length & (0b11111111 << (kBytes - i - 1) * 8)) >> (kBytes - i - 1) * 8);
        }
        //write(byteObj.length);
        write(byteObj);

        /*
        ObjectOutputStream oos = new ObjectOutputStream(this);
        ByteArrayOutputStream bais = new ByteArrayOutputStream(oos);

        oos.writeObject(structure);
        oos.close();
        */
    }

    /**
     * Метод должен вернуть записать массив прочитанных структур.
     */
    public void write(Structure[] structures) throws IOException {
        for (Structure cur: structures) {
            write(cur);
        }
    }
}