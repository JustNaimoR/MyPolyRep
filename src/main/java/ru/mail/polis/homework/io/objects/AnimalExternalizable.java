package ru.mail.polis.homework.io.objects;

/**
 * Дубль класса Animal, для Serializer.serializeWithExternalizable
 * 3 тугрика
 */
public class AnimalExternalizable {
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
