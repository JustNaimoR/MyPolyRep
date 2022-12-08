package ru.mail.polis.homework.oop.vet.Animals;

import ru.mail.polis.homework.oop.vet.Animal;
import ru.mail.polis.homework.oop.vet.MoveType;
import ru.mail.polis.homework.oop.vet.WildAnimal;

public class Snake extends Animal implements WildAnimal {
    private String organizationName;

    public Snake(int legs) {
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
        return MoveType.CRAWL;
    }

    @Override
    public String say() {
        return "Shhhh";
    }
}
