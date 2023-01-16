package ru.mail.polis.homework.io.objects;


/**
 * Класс должен содержать несколько полей с примитивами (минимум 2 булеана и еще что-то), строками, энамами и некоторыми сапомисными объектами (не энам).
 * Всего должно быть минимум 6 полей с разными типами.
 * Во всех полях, кроме примитивов должны поддерживаться null-ы
 * 1 тугрик
 */
public class Animal {
    public boolean wild;
    public boolean hasWool;
    public int legs;
    public String name;
    public SomeClass someClass;
    enum Type {
        CAT,
        DOG,
        OTHER
    }



    public static class SomeClass {

    }

}