package ru.bvpotapenko.se.j1.l1;

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
        if (year > 0 && //год больше нуля и делится на 4, но не делится на 100; или год положительный и делится на 400.
                ((year % 4 == 0 && year % 100 != 0) ||
                        (year % 400 == 0))
        ) {
            System.out.println(year + " - является високосным");
        } else
            System.out.println(year + " - не високосный");

    }
}