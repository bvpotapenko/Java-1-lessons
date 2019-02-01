package ru.bvpotapenko.se.j1.l1;

import java.time.LocalDate;
import java.util.Random;

public class Main {

    //Task 1: Создать пустой проект в IntelliJ IDEA и прописать метод main();
    public static void main(String[] args) {

        //Task 2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte bt = -128;
        short sht = 32767;
        int i = 1;
        long l = 9223372036854775807L;
        float f = 0.1234567f;
        double d = 123.4567891011121314151617181920;
        boolean bool = true;
        char ch = 'c';
        String str = "String";

        //Tests:
        System.out.println("Task 3: решить выражение a * (b + (c / d)); 5 * (6 + (7 / 9)) =  " + task3_solveExpression(5, 6, 7, 0));
        System.out.println();

        System.out.println("Task 4: проверить, что сумма от 10 до 20. 3+12 " + task4_isSumFrom10to20(3, 12));
        System.out.println("Task 4: проверить, что сумма от 10 до 20 5+4 " + task4_isSumFrom10to20(5, 4));
        System.out.println();

        System.out.println("Task 5: положительное или отрицательное? 10: ");
        task5_isPosOrNeg(10);
        System.out.println("Task 5: положительное или отрицательное? -10: ");
        task5_isPosOrNeg(-10);
        System.out.println();

        System.out.println("Task 6: является отрицательным? -1 " + task6_isNegative(-1));
        System.out.println("Task 6: является отрицательным? 7 " + task6_isNegative(7));
        System.out.println();

        System.out.println("Task 7: Поздороваться с Иваном ");
        task7_printHello("Иван");
        System.out.println("Task 7: Поздороваться со Степаном ");
        task7_printHello("Степан");
        System.out.println();

        System.out.println("Task 8: проверить на високосность года");
        task8_isLeapYear(1600);
        task8_isLeapYear(1500);
        task8_isLeapYear(1604);
        task8_isLeapYear(2100);
        task8_isLeapYear(2000);
        task8_isLeapYear(100);
        task8_isLeapYear(4000);
        task8_isLeapYear(2400);
        task8_isLeapYear(2018);

        System.out.println("Task 9: доказать (!(a && b) && (a || b)) || ((a && b) || !(a || b)) = true");
        System.out.println("false || (a && !b) || (!a && b) || (a && b) || (!a && !b)");
        System.out.println("Для любой пары значений а и б в выражении найдётся одна конъюнкция обращающаяся в истину.");
        task9_isTrue(false, false);
        task9_isTrue(true, true);
        task9_isTrue(true, false);
        task9_isTrue(false, true);

        System.out.println("Task 10: проверить делимость А на Б");
        task10_checkВivisibility(10, 5);
        task10_checkВivisibility(5, 10);

        System.out.println("Переменные a и b типа int. Упростите выражение (!(a < b) && !(a > b)).");
        System.out.println("Упрощается до (a == b) по закону де Моргана");

        System.out.println("Task 11: Проверить три числа (стороны трегольника)");
        task11_checkThreeNumbers(3, 4, 5);

        System.out.println("Task 12: Напишите метод, который получает на вход два целых числа и выводит случайное число в этом диапазоне.");
        task12_getRnd(2, 7);

        System.out.println("Task 13: Напишите метод, который выводит сумму двух случайных чисел от 1 до 6. (имитация броска кубиков)");
        task13_rollTheDies();
        task13_rollTheDies();
        task13_rollTheDies();

        System.out.println("Task 14: Напишите метод, который получает на вход число t и выводит в консоль результат выражения\n" +
                " sin(2t) + sin(3t)");
        task14_sin(5);

        System.out.println("Task 15: Напишите метод, который получает на вход целочисленные m и d и проверяет, что введённые m-месяц и d-день, лежат в промежутке от 20.03 до 20.06.");
        task15_checkDate(4, 15);

        System.out.println("Task 16: Напишите метод, который получает на вход 3 целых числа, сравнивает их, и выводит в консоль \"Все три числа равны\" или \"не равны\".");
        task16_compareThreeNumbers(1, 2, 3);
        task16_compareThreeNumbers(3, 3, 3);

        System.out.println("Task 17: Напишите метод, который проверяет, что переменные x и y типа double лежат строго в пределах от 0 до 1");
        task17_checkDoubleInBounds(0.5d, 0.99d);
    }

    //Task 3: Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    //где a, b, c, d – входные параметры этого метода;
    public static float task3_solveExpression(int a, int b, int c, int d) {
        return a * (b + ((float) c / d)); //приводим один из операндов к типу float,
        // чтоб значение всего выражения не округлилось
    }

    //Task 4: Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в
    //пределах от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
    public static boolean task4_isSumFrom10to20(int a, int b) {
        return (a + b) >= 10 && (a + b) <= 20; // СУММА больше 10 И СУММА меньше 20 Да/Нет?
    }

    //Task 5. Написать метод, которому в качестве параметра передается целое число, метод должен
    //напечатать в консоль положительное ли число передали, или отрицательное; Замечание:
    //ноль считаем положительным числом.
    public static void task5_isPosOrNeg(int i) {
        //Если параметр меньше нуля, то выведем [i] is negative, иначе [i] is positive
        System.out.println(i < 0 ? i + " is negative" : i + " is positive");
    }

    //Task 6. Написать метод, которому в качестве параметра передается целое число, метод должен
    //вернуть true, если число отрицательное;
    public static boolean task6_isNegative(int i) {
        return i < 0; //Если меньше нудя, то вернём true
    }


    //Task 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
    //метод должен вывести в консоль сообщение «Привет, указанное_имя!»;

    public static void task7_printHello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    //Task 8. *Написать метод, который определяет является ли год високосным, и выводит сообщение в
    //консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й –
    //високосный.
    public static void task8_isLeapYear(int year) {
        //год положительный; 
        // делится на 4, но не делится на 100;
        // делится на 400.
        final boolean check1 = year > 0;
        final boolean check2 = year % 4 == 0 && year % 100 != 0;
        final boolean check3 = year % 400 == 0;
        final boolean check4 = check1 && check2 || check1 && check3;
        String answer = check4 ? year + " - является високосным" : (year + " - не високосный");
        System.out.println(answer);
    }

    private static void task9_isTrue(boolean a, boolean b) {
        System.out.println((!(a && b) && (a || b)) || ((a && b) || !(a || b)));
    }

    private static void task10_checkВivisibility(int a, int b) {
        System.out.println(a % b == 0);
    }

    private static void task11_checkThreeNumbers(int a, int b, int c) {
        System.out.println((a + b > c) && (a + c > b) && (a + c > b));
    }

    private static void task12_getRnd(int a, int b) {
        System.out.println(Math.random() * (b - a) + a);
    }

    private static void task13_rollTheDies() {
        System.out.println(new Random().nextInt(6) + new Random().nextInt(6) + 2);
    }

    private static void task14_sin(int t) {
        System.out.println(Math.sin(2 * t) + Math.sin(3 * t));
    }

    private static void task15_checkDate(int m, int d) {
        LocalDate date = LocalDate.of(2019, m, d);
        System.out.println(
                date.isAfter(LocalDate.of(2019, 3, 20)) &&
                        date.isBefore(LocalDate.of(2019, 6, 20))
        );

    }

    private static void task16_compareThreeNumbers(int a, int b, int c) {
        boolean check = a == b && b == c;
        System.out.println(check ? "Все три числа равны" : "не равны");
    }

    private static void task17_checkDoubleInBounds(double x, double y) {
        System.out.println( Double.compare(x, 0.0d) == 1 &&
                Double.compare(x, 1.0d) == -1 &&
                Double.compare(y, 0.0d) == 1 &&
                Double.compare(y, 1.0d) == -1);
    }
}