package ru.bvpotapenko.se.j1.extoop;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        for (int i = 0; i < 3; i++) {
            float jumpHeight = new Random().nextFloat();
            int runDistance = new Random().nextInt(1000);
            int swimDistance = new Random().nextInt(15);
            System.out.println("TRIAL " + (i+1));
            System.out.println("jumpHeight: " + df.format(jumpHeight));
            System.out.println("runDistance " + runDistance);
            System.out.println("swimDistance " + swimDistance );
            System.out.println();
            Dog dog = new Dog();
            dog.jump(jumpHeight);
            dog.run(runDistance);
            dog.swim(swimDistance);
            System.out.println();
            Cat cat = new Cat();
            cat.jump(jumpHeight);
            cat.run(runDistance);
            cat.swim(swimDistance);
            System.out.println();
        }
    }
}
