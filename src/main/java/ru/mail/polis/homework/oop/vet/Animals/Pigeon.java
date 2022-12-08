package ru.mail.polis.homework.oop.vet.Animals;

import ru.mail.polis.homework.oop.vet.Animal;
import ru.mail.polis.homework.oop.vet.MoveType;
import ru.mail.polis.homework.oop.vet.WildAnimal;

public class Pigeon extends Animal implements WildAnimal {

    private String organizationName;

    public Pigeon(int legs) {
        super(legs);
    }

    @Override
    public String getOrganizationName() {
        return organizationName;
    }

    @Override
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public MoveType moveType() {
        return MoveType.FLY;
    }

    @Override
    public String say() {
        return "curls-curls";
    }

}
