package ru.bvpotapenko.se.j1.extoop;

import java.util.Random;

public class Cat extends Animal {
    public Cat(){
        super.type = AnimalType.CAT;
        super.jumpLim = new Random().nextFloat() + 1.0f;
        super.runLim = new Random().nextInt(100) + 100;
        super.swimLim = 0;
    }
}
