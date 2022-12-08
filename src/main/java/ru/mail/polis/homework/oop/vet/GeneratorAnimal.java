package ru.mail.polis.homework.oop.vet;

import ru.mail.polis.homework.oop.vet.Animals.*;

public class GeneratorAnimal {
    private GeneratorAnimal() {}

    /**
     * В зависимости от передоваемой строки, должен геенрировать разные виды дочерних объектов
     * класса Animal. Дочерние классы должны создаваться на следующие наборы строк:
     * - cat
     * - dog
     * - kangaroo
     * - pigeon
     * - cow
     * - shark
     * - snake
     * Так же при реализации классов стоит учитывать являются ли животные дикими или домашними
     * и в зависимости от этого они должны реализовывать тот или иной интерфейс.
     *
     * @param animalType - тип животного которое надо создать
     * @return - соответствующего потомка
     */
    public static Animal generateAnimal(String animalType) {
        if (animalType.contains("cat")) {
            return new Cat(4);
        } else if (animalType.contains("dog")) {
            return new Dog(4);
        } else if (animalType.contains("kangaroo")) {
            return new Kangaroo(2);
        } else if (animalType.contains("pigeon")) {
            return new Pigeon(2);
        } else if (animalType.contains("cow")) {
            return new Cow(4);
        } else if (animalType.contains("shark")) {
            return new Shark(0);
        } else if (animalType.contains("snake")) {
            return new Snake(0);
        } else {
            return null;
        }
    }
}
