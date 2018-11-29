package ru.bvpotapenko.se;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homework2 {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int itemsAmount = readIntFromConsole(reader); //Размер массива для задачи про поиск Мах и Min
    private static List<Integer> iArr = getListWithN_RundomItems(itemsAmount); //Массив для задачи про поиск Max и Min

    public static void main(String[] args) {

        //Задачи 1 - 4 пропущены из-за тривиальности решения.

        //Задача 5. Выводим максимальный и минимальный элемент массива
        printMaxAndMinIn_iArray();

        //Задача 6. Поиск точки баланса в массиве
        findBalanceInArray(new int[]{0, 0, 0, 0});
        findBalanceInArray(new int[]{1, 2, 2, 1});
        findBalanceInArray(new int[]{2, 1, 2, 1});
        findBalanceInArray(new int[]{2, 1, 1, 1, 1});
        findBalanceInArray(new int[]{0});
        findBalanceInArray(new int[]{2, 5, 1, 1, 1});
        findBalanceInArray(new int[]{4, 1, 1, 1, 1});
        findBalanceInArray(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 1});
        findBalanceInArray(new int[]{});

        //Задача 7. Сдвинуть массив на N позиций
        System.out.println(shiftArray(new ArrayList<Object>(Arrays.asList(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})), 3));
    }

    //Читаем с консоли размер будущего массива
    private static int readIntFromConsole(BufferedReader reader) {
        String buffer;

        try {
            System.out.println("Введите количество элементов массива: ");
            buffer = reader.readLine();
            if (!buffer.isEmpty())
                return Integer.parseInt(buffer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The Array is initializing as empty");
        }

        return 0;
    }

    //Заполняем массив случайными величинами 0..50
    private static List<Integer> getListWithN_RundomItems(int n) {
        List<Integer> iArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int newItem = (int) (Math.random() * 50);
            iArr.add(newItem);
        }
        return iArr;
    }

    //Задача 5. Выводим максимальный и минимальный элемент массива
    public static void printMaxAndMinIn_iArray() {
        if (iArr.size() > 0) {

            int max = iArr.get(0);
            int min = iArr.get(0);

            for (Integer item : iArr) {
                max = item > max ? item : max;
                min = item < min ? item : min;
            }

            System.out.println("Список элементов массива: " + iArr);
            System.out.println("Max = " + max + "; \n Min = " + min + ";");

        } else System.out.println("Array is empty");
    }

    public static void findBalanceInArray(int[] iArrToBalance) {
        String result = "There is nothing to balance, the array is empty";
        if (iArrToBalance.length > 0) {
            int i = 0;
            int j = iArrToBalance.length - 1;
            int sumLeft = iArrToBalance[0];
            int sumRight = iArrToBalance[j];

            while (i + 1 < j) {
                if (sumLeft < sumRight) {
                    i++;
                    sumLeft += iArrToBalance[i];
                    continue;
                }
                if (sumLeft > sumRight) {
                    j--;
                    sumRight += iArrToBalance[j];
                    continue;
                }

                final boolean condition1 = sumLeft == sumRight; //подсчитанные суммы равны
                final boolean condition2 = (i + 1) != (j - 1); //между индексами больше 1-го элемента
                final boolean condition3 = iArrToBalance[i + 1] == 0; //следующий элемент для левого индекса == 0
                final boolean condition4 = condition1 && (condition2 || condition3);

                if (condition4) {
                    if (condition3) { //следующий элемент нулевой
                        i++;
                        sumLeft += iArrToBalance[i];
                    } else {
                        i++;
                        j--;
                        sumLeft += iArrToBalance[i];
                        sumRight += iArrToBalance[j];
                    }
                }
            }//END while

            final boolean isBalanced = sumLeft == sumRight && i < j;

            if (isBalanced) {
                result = showBalancePoint(i, iArrToBalance);
            } else result = "there is no balance point in: " + Arrays.toString(iArrToBalance);
        }
        System.out.println(result);
    }

    //Заполняем строку элементами массива с указанием позиции баланса
    private static String showBalancePoint(int balancePosition, int[] iArrayToBalance) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < iArrayToBalance.length; i++) {
            sb.append(" " + iArrayToBalance[i]);
            if (i == balancePosition) sb.append("  || "); //Вставляем позицию баланса
        }
        sb.append("}");
        return sb.toString();
    }

    //Позитивный сдвиг
    public static List<Object> shiftArray (ArrayList<Object> arr, int shift){
        if (arr.isEmpty() || shift == 0) return arr;
        System.out.println(arr.toString());
        for (int i = 0; i < shift; i++) {
            arr = putItemInArray(
                    putItemInArray(
                            arr,
                            arr.get(arr.size()-2),
                            arr.size()-1),
                    arr.get(arr.size()-1),
                    0);
        }
        System.out.println(arr.toString());
        return arr;
    }
 //   метод: положить в ячейку массива значение, вернуть массив. но перед складыванием рекурсивно перекинуть предыдущий
     private static ArrayList<Object> putItemInArray(ArrayList<Object> arr, Object item, int position){
        if (position -1 > 0){
            arr.set(position, item);
            putItemInArray(arr, arr.get(position-2), position-1);
        }
        return arr;
     }

}
