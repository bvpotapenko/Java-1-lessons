package ru.bvpotapenko.se.noughtsandcrosses;

import java.text.MessageFormat;
import java.util.Scanner;

/**
 * Noughts And Crosses game.
 */
public class NoughtsAndCrosses {
    private static Scanner sc = new Scanner(System.in);
    private static BotLogic botLogic;
    private static char[][] map;
    private static int mapWidth = 3;
    private static int mapHeight = 3;
    private static int dotsToWin = 3;
    private static int minDotsToWin = 3;
    private static int maxMapWidth = 99;
    private static int minMapWidth = 3;
    private static int maxMapHeight = 99;
    private static int minMapHeight = 3;
    private static final char DOT_EMPTY = ' ';//'■';
    private static final char DOT_PLAYER = 'X';
    private static final char DOT_BOT = 'O';
    private static final String DRAW_GAME_MESSAGE = "Ничья!";
    private static final String USER_WON_MESSAGE = "Победил человек!";
    private static final String ROBOT_WON_MESSAGE = "Победил бот!";
    private static final String ROBOT_PLAYED_MESSAGE = "  ۜ\\(סּںסּَ` )/ۜ Бот походил в клетку: ";
    private static final String INPUT_COORDINATES_MESSAGE = "Введите координаты хода через пробел: x y";
    private static final String SAD_ROBOT = "     ___T_\n" +
            "    | d b |\n" +
            "    |__=__|\n" +
            "}-. /\\--o/\\ .-{\n" +
            "   \" |___| \"\n" +
            "      |_|\n" +
            "     (ooo)";
    private static final String HAPPY_ROBOT = "    ___T_ \n" +
            "  d[ o 0 ]b\n" +
            "()\\|__u__|/()\n" +
            "  \\\\| []|//\n" +
            "    |___|\n" +
            "    |_|_|\n" +
            "    /_|_\\";


    static void initMap() {
        map = new char[mapWidth][mapHeight];
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        sb = new StringBuilder();
        sb.append("+----");
        for (int k = 0; k < mapWidth; k++) {
            sb.append("+---");
        }
        sb.append("+%n");
        String rowSeparator = sb.toString();
        System.out.printf(rowSeparator);
        System.out.print("|    |");
        for (int k = 1; k < mapWidth + 1; k++) {
            System.out.print(MessageFormat.format(" {0} |", k));
        }
        System.out.println(" (X-axis)");
        System.out.printf(rowSeparator);
        for (int i = 0; i < mapHeight; i++) {
            //System.out.print(MessageFormat.format("| {0} ", i + 1));
            System.out.printf("| %2d ", i + 1);
            for (int j = 0; j < mapWidth; j++) {
                if (j == mapWidth - 1) {
                    System.out.print(MessageFormat.format("| {0} |", map[j][i]));
                } else {
                    System.out.print(MessageFormat.format("| {0} ", map[j][i]));
                }
            }
            System.out.println();
            System.out.printf(rowSeparator);
        }
        System.out.println("(Y-axis)");
    }

    private static void playerTurn() {
        int x = -1;
        int y = -1;
        do {
            System.out.println(INPUT_COORDINATES_MESSAGE);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            sc.nextLine();
        } while (!isCellEmpty(x, y));
        map[x][y] = DOT_PLAYER;

    }

    private static boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > map.length - 1 || y > map[0].length - 1)
            return false;
        return map[x][y] == DOT_EMPTY;
    }

    private static void aiTurn() {
        int x, y;
        botLogic.updateMap(map);
        botLogic.processNextStep();
        x = botLogic.getX();
        y = botLogic.getY();
        map[x][y] = DOT_BOT;
        System.out.println(ROBOT_PLAYED_MESSAGE + (x + 1) + " " + (y + 1));

    }

    static boolean isMapFull() {
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        boolean playAgain = true;
        while (playAgain) {
            boolean isFieldSet = false;
            while (!isFieldSet) {
                System.out.println("Field size is set to 3x3.\n" +
                        "Set another field size? y/n");
                String command = sc.nextLine();
                if (command.equalsIgnoreCase("y")) {
                    System.out.println("Input width. Must be >= 3 AND <= " + maxMapWidth);
                    mapWidth = sc.nextInt();
                    System.out.println("Input height. Must be >= 3 AND <= " + maxMapHeight);
                    mapHeight = sc.nextInt();
                    sc.nextLine();
                }
                if (mapWidth >= minMapWidth && mapWidth <= maxMapWidth && mapHeight >= minMapHeight && mapHeight <= maxMapHeight) {
                    isFieldSet = true;
                } else {
                    mapWidth = 3;
                    mapHeight = 3;
                    System.out.println("Wrong field size.");
                }
            }
            boolean isWinAmountSet = false;
            while (!isWinAmountSet) {
                System.out.println("To win you need 3 tokens in a line.\n" +
                        "Set another win rule? y/n");
                String command = sc.nextLine();
                if (command.equalsIgnoreCase("y")) {
                    System.out.println("Input the amount of tokens to win. Must be >= " + minDotsToWin + " AND <= "
                            + Math.min(mapWidth, mapHeight));
                    dotsToWin = sc.nextInt();
                    sc.nextLine();

                }
                if (dotsToWin >= minDotsToWin && dotsToWin <= Math.min(mapWidth, mapHeight)) {
                    isWinAmountSet = true;
                } else {
                    dotsToWin = minDotsToWin;
                    System.out.println("Wrong win rule.");
                }
            }
            initMap();
            printMap();
            botLogic = new BotLogic(map, DOT_EMPTY, DOT_BOT, DOT_PLAYER, dotsToWin);

            while (true) {
                playerTurn();
                System.out.println();
                printMap();
                if (botLogic.checkWin(map, DOT_PLAYER)) {
                    System.out.println(USER_WON_MESSAGE);
                    System.out.println(SAD_ROBOT);
                    break;
                }
                if (isMapFull()) {
                    System.out.println(DRAW_GAME_MESSAGE);
                    break;
                }
                aiTurn();
                if (botLogic.checkWin(map, DOT_BOT)) {
                    printMap();
                    System.out.println(ROBOT_WON_MESSAGE);
                    System.out.println(HAPPY_ROBOT);
                    break;
                }
                System.out.println();
                printMap();
                if (isMapFull()) {
                    System.out.println(DRAW_GAME_MESSAGE);
                    break;
                }
            }
            System.out.println("Play again? y/n");
            if (!"y".equalsIgnoreCase(sc.nextLine()))
                playAgain = false;
        }
    }
}
