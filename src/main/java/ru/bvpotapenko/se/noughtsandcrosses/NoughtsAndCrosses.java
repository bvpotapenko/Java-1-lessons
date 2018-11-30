package ru.bvpotapenko.se.noughtsandcrosses;

import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class NoughtsAndCrosses {
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();
    private static char[][] map;
    private static int mapWidth = 3;
    private static int mapHeight = 3;
    private static final char DOT_EMPTY = '■';
    private static final char DOT_PLAYER = 'X';
    private static final char DOT_BOT = 'O';
    private static final String DRAW_GAME_MESSAGE = "Ничья!";
    private static final String USER_WON_MESSAGE = "Победил человек!";
    private static final String ROBOT_WON_MESSAGE = "Победил бот!";
    private static final String ROBOT_PLAYED_MESSAGE =  "  ۜ\\(סּںסּَ` )/ۜ Бот походил в клетку: ";
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

    static void initMap(){
        map = new char[mapWidth][mapHeight];
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printMap(){
        for (int k = 0; k < mapWidth + 1; k++) {
            System.out.printf(" %2d", k);
        }
        System.out.println();
        for (int i = 0; i < mapWidth; i++) {
            System.out.printf(" %2d", i+1);
            for (int j = 0; j < mapHeight; j++) {
                System.out.printf(" %2c", map[i][j]);
            }
            System.out.println();
        }
    }

    private static void playerTurn(){
        int x = -1;
        int y = -1;
        do {
            System.out.println(INPUT_COORDINATES_MESSAGE);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }while (!isCellEmpty(x, y));
        map[x][y] = DOT_PLAYER;

    }

    private static boolean isCellEmpty(int x, int y){
        if (x < 0 || y < 0 || x > mapWidth - 1 || y > mapHeight - 1)
            return false;
        return map[x][y] == DOT_EMPTY;
    }
    private static void aiTurn(){
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }while (!isCellEmpty(x, y));
        map[x][y] = DOT_BOT;
        System.out.println(ROBOT_PLAYED_MESSAGE + x + " " + y);

    }

    static boolean isMapFull(){
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    private static boolean checkWin (char dot){
        if (map[0][0] == dot && map[0][1] == dot && map[0][2] == dot)
            return true;
        if (map[1][0] == dot && map[1][1] == dot && map[1][2] == dot)
            return true;
        if (map[2][0] == dot && map[2][1] == dot && map[2][2] == dot)
            return true;

        if (map[0][0] == dot && map[1][0] == dot && map[2][0] == dot)
            return true;
        if (map[0][1] == dot && map[1][1] == dot && map[2][1] == dot)
            return true;
        if (map[0][2] == dot && map[1][2] == dot && map[2][2] == dot)
            return true;

        if (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot)
            return true;
        if (map[0][2] == dot && map[1][1] == dot && map[2][0] == dot)
            return true;
        return false;
    }

    private static int[][] aiLogic(){
        //TODO:
        //Проверка угрозы поражения
        //проверить линии на наличие двух "Х"
        //Поставить "О" в свободную ячейку этой линии.

        //Проверяем построчно, победу:
        //Найти строку/столбец/диагональ с 2х "О" .. 1х "О"
        //Если такой линии нет, то найти строку без "Х"
        //Найти в строке свободный символ.
        //Поставить "О" в найденную позицию.


        //Усиление логики
        //1. Ставить в центр, если он свободен
        //2. Ставя "О" в пустую линию или линию с 1х "Х", выбирать ячейку рядом с "Х".
        //3. Искать выгодные пересечения строк, в которых есть "О"
    return null;
    }


    public static void main( String[] args ){
        initMap();
        printMap();

        while(true) {
            playerTurn();
            System.out.println();
            printMap();
            if(checkWin(DOT_PLAYER)){
                System.out.println(USER_WON_MESSAGE);
                System.out.println(SAD_ROBOT);
                break;
            }
            if(isMapFull()){
                System.out.println(DRAW_GAME_MESSAGE);
                break;
            }
            aiTurn();
            if(checkWin(DOT_BOT)){
                System.out.println(ROBOT_WON_MESSAGE);
                System.out.println(HAPPY_ROBOT);
                break;
            }
            System.out.println();
            printMap();
            if(isMapFull()){
                System.out.println(DRAW_GAME_MESSAGE);
                break;
            }
        }
    }
}
