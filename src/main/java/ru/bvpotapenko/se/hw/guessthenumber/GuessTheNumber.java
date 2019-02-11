package ru.bvpotapenko.se.hw.guessthenumber;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    private int number;
    Random rnd = new Random();

    public void start() {
        boolean tryAgain = true;
        boolean win = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("The Game started. \nGuess the number from the range: [0, 9]. \nYou've got 3 attempts.");
        int answer;
        while (tryAgain) {
            number = rnd.nextInt(10);
            for (int i = 0; i < 3; i++) {
                answer = scanner.nextInt();
                if (answer == number) {
                    System.out.println("WIN!");
                    win = true;
                } else if (answer < number) {
                    System.out.println("too small");
                } else {
                    System.out.println("too big");
                }
            }
            System.out.println("Try again? yes - 1 \\ no - 0");
            if (scanner.nextInt() != 1) tryAgain = false;
        }
    }
}
