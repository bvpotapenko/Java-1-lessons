import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {

    @Test
    void task3_expressionSolver_CORRECT_ARGS_FLOAT_RETURNED() {
        //Expected data
        float expected1 = 33.88889f;
        float expected2 = 2.75f;

        //Actual result
        float actual1 = Main.task3_solveExpression(5, 6, 7, 9); //33.88889
        float actual2 = Main.task3_solveExpression(1, 2, 3, 4); //2.75


        //Testing
        Assertions.assertEquals(expected1, actual1, 0.000001f);
        Assertions.assertEquals(expected2, actual2, 0.000001f);
    }

    @Test
    void task3_expressionSolver_DEVIDE_BY_ZERO_Infinity_RETURNED() {
        //Expected data
        float expected = Float.valueOf("Infinity");

        //Actual result
        float actual = Main.task3_solveExpression(4, 10, 47, 0); //NaN

        //Testing
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void task4_isSumFrom10to20_3_12_TRUE_RETURNED() {
        Assertions.assertTrue(Main.task4_isSumFrom10to20(3, 12));
    }

    @Test
    void task4_isSumFrom10to20_5_4_FALSE_RETURNED() {
        Assertions.assertFalse(Main.task4_isSumFrom10to20(5, 4));
    }

    @Test
    void task5_isPosOrNeg_10_IS_POSITIVE_RETURNED() {
        //Expected
        String expected = "10 is positive\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //Actual
        Main.task5_isPosOrNeg(10);

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task5_isPosOrNeg_neg10_IS_NEGATIVE_RETURNED() {
        //Expected
        String expected = "-10 is negative\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //Actual
        Main.task5_isPosOrNeg(-10);

        Assertions.assertEquals(expected, outContent.toString());
    }
    @Test
    void task6_isNegative_neg1_TRUE_RETURNED() {
        Assertions.assertTrue(Main.task6_isNegative(-1));
    }
    @Test
    void task6_isNegative_7_FALSE_RETURNED() {
        Assertions.assertFalse(Main.task6_isNegative(7));
    }

    @Test
    void task7_printHello_HELLO_IVAN_RETURNED() {
        //Expected
        String expected = "Привет, Иван!\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task7_printHello("Иван");

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task7_printHello_HELLO_STEPAN_RETURNED() {
        //Expected
        String expected = "Привет, Степан!\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task7_printHello("Степан");

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task8_isLeapYear_1600_LEAP_RETURNED() {
        //Expected
        String expected = "1600 - является високосным\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task8_isLeapYear(1600);

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task8_isLeapYear_1500_NOT_LEAP_RETURNED() {
        //Expected
        String expected = "1500 - не високосный\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task8_isLeapYear(1500);

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task8_isLeapYear_1604_LEAP_RETURNED() {
        //Expected
        String expected = "1604 - является високосным\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task8_isLeapYear(1604);

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task8_isLeapYear_2100_NOT_LEAP_RETURNED() {
        //Expected
        String expected = "2100 - не високосный\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task8_isLeapYear(2100);

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task8_isLeapYear_2000_LEAP_RETURNED() {
        //Expected
        String expected = "2000 - является високосным\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task8_isLeapYear(2000);

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task8_isLeapYear_0_NOT_LEAP_RETURNED() {
        //Expected
        String expected = "0 - не високосный\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task8_isLeapYear(0);

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task8_isLeapYear_100_NOT_LEAP_RETURNED() {
        //Expected
        String expected = "100 - не високосный\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task8_isLeapYear(100);

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void task8_isLeapYear_2018_NOT_LEAP_RETURNED() {
        //Expected
        String expected = "2018 - не високосный\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //actual
        Main.task8_isLeapYear(2018);

        Assertions.assertEquals(expected, outContent.toString());
    }

}