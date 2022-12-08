package ru.mail.polis.homework.oop.vet.Animals;

import ru.mail.polis.homework.oop.vet.Animal;
import ru.mail.polis.homework.oop.vet.MoveType;
import ru.mail.polis.homework.oop.vet.Pet;
import ru.mail.polis.homework.oop.vet.WildAnimal;

public class Cow extends Animal implements Pet, WildAnimal {

    private String organizationName;
    public Cow(int legs) {
        super(legs);
    }

    @Override
    public String say() {
        return "Moo-Moo";
    }

    @Override
    public String getOrganizationName() {
        return organizationName;
    }

    @Override
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}