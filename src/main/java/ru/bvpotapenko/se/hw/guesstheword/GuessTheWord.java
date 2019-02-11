package ru.bvpotapenko.se.hw.guesstheword;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessTheWord {
    String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    public void start() {
        String word = newWord();
        boolean play = true;
        Scanner sc = new Scanner(System.in);
        System.out.println(Arrays.toString(words));
        System.out.println("The Game started.\n" +
                "Guess the word.\n" +
                "Input your answer.\n" +
                "To stop playing input #.");
        while (play) {
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("#")) play = false;
            if (answer.equalsIgnoreCase(word)) {
                System.out.println("WINNER!\n " +
                        "Play again? Yes(1)\\No(0)");
                if (sc.nextInt() != 1) play = false;
            }else{
                String masked = getMaskedWord(answer, word);
                System.out.println(masked);
            }
        }
        System.out.println("GAME OVER!");
    }

    private String getMaskedWord(String answer, String original) {
        //mask length is 15, or random longer if the word is longer than 15.
        int maskLength = (original.length() > 15) ?
                            original.length() + new Random().nextInt(10) :
                            15;
        StringBuilder sb = new StringBuilder(maskLength);
        for (int i = 0; i < maskLength; i++) {
            if (i < answer.length() && i < original.length()){
                if(answer.charAt(i) == original.charAt(i)){
                    sb.append(answer.charAt(i));
                }else{
                    sb.append("#");
                }
            }else{
                sb.append("#");
            }
        }
        return sb.toString();
    }

    private String newWord() {
        return words[new Random().nextInt(words.length - 1)];
    }
}
