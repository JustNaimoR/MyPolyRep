package ru.mail.polis.homework.io.objects;


/**
 * Дубль класса Animal, для Serializer.serializeWithMethods
 * 3 тугрика
 */
public class AnimalWithMethods {
    public boolean wild;
    public boolean hasWool;
    public int legs;
    public String name;
    public Animal.SomeClass someClass;
    enum Type {
        CAT,
        DOG,
        OTHER
    }



    public static class SomeClass {

    }
}
