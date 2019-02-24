package ru.bvpotapenko.se.j1.extoop;

import java.util.Random;

public class Dog  extends Animal{
    public Dog() {
        super.type = AnimalType.DOG;
        super.jumpLim = new Random().nextFloat()/2;
        super.runLim = new Random().nextInt(200) + 400;
        super.swimLim = new Random().nextInt(5) + 5;
    }
}
