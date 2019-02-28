package ru.bvpotapenko.se.j1.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            cats.add(new Cat("Cat_"+i, rnd.nextInt(20)));
        }
        Plate plate = new Plate(100);
        cats.forEach(c -> c.eat(plate));
        cats.forEach(c -> System.out.println(c.getName() + " is happy: " + c.isBellyful()));
        plate.info();
    }
}
