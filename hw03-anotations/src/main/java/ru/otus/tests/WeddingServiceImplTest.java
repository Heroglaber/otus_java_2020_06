package ru.otus.tests;

import ru.otus.example1.model.Family;
import ru.otus.example1.services.HumanGenerator;
import ru.otus.example1.services.HumanGeneratorImpl;
import ru.otus.example1.services.WeddingService;
import ru.otus.example1.services.WeddingServiceImpl;
import ru.otus.testsframework.After;
import ru.otus.testsframework.Before;
import ru.otus.testsframework.Test;


public class WeddingServiceImplTest {

    HumanGenerator humanGenerator = null;
    WeddingService weddingService = null;

    @Before
    public void setUp() {
        humanGenerator = new HumanGeneratorImpl();
        weddingService = new WeddingServiceImpl(humanGenerator);
    }

    @Test
    public void shouldReturnFamilyWithMaleHusbandAndFemaleWife() throws Exception {
        Family family = weddingService.doWedding();

        if(family.getHusband().getSex() != 'm') {
            throw new Exception();
        }

        if(family.getWife().getSex() != 'f') {
            throw new Exception();
        }
    }

    @Test
    public void shouldThrowException() throws Exception {
        throw new Exception();
    }

    @Test
    public void shouldGenerateNewFamilies() throws Exception {
        Family family = weddingService.doWedding();
        Family family2 = weddingService.doWedding();

        if(family.equals(family2)) {
            throw new Exception();
        }
    }

    @After
    public void tearDown() {
        weddingService = null;
        humanGenerator = null;
    }
}