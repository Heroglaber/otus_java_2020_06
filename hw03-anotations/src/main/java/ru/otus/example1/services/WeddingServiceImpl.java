package ru.otus.example1.services;

import ru.otus.example1.model.Family;

public class WeddingServiceImpl implements WeddingService {

    private final HumanGenerator humanGenerator;

    public WeddingServiceImpl(HumanGenerator humanGenerator) {
        this.humanGenerator = humanGenerator;
    }

    @Override
    public Family doWedding() {
        return new Family(humanGenerator.generate('m'),
                humanGenerator.generate('f'));
    }
}
