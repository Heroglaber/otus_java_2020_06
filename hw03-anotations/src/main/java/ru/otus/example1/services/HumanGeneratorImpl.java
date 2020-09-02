package ru.otus.example1.services;

import ru.otus.example1.model.Human;

public class HumanGeneratorImpl implements HumanGenerator {

    @Override
    public Human generate(char sex) {
        return new Human(sex);
    }
}
