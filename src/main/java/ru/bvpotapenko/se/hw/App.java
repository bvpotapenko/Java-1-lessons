package ru.bvpotapenko.se.hw;

import ru.bvpotapenko.se.hw.guessthenumber.GuessTheNumber;
import ru.bvpotapenko.se.hw.guesstheword.GuessTheWord;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a game:\n" +
                "1 - Guess the number\n" +
                "2 - Guess the word\n" +
                "0 - Quit");
        switch (scanner.nextLine()) {
            case "1":
                new GuessTheNumber().start();
                break;
            case "2":
                new GuessTheWord().start();
                break;
        }
    }
}
