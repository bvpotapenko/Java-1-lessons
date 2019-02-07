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
        Integer[] testArr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Shift = 3");
        System.out.println(Arrays.toString(shiftArray(testArr1, 3)));

        System.out.println("Shift = 1");
        testArr1 = Arrays.stream(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10} ).boxed().toArray( Integer[]::new );
        System.out.println(Arrays.toString(shiftArray(testArr1, 1)));

        System.out.println("Shift = 5");
        testArr1 = Arrays.stream(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10} ).boxed().toArray( Integer[]::new );
        System.out.println(Arrays.toString(shiftArray(testArr1, 5)));

        System.out.println("Shift = 23");
        testArr1 = Arrays.stream(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10} ).boxed().toArray( Integer[]::new );
        System.out.println(Arrays.toString(shiftArray(testArr1, 23)));

        System.out.println("Shift = -1");
        testArr1 = Arrays.stream(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10} ).boxed().toArray( Integer[]::new );
        System.out.println(Arrays.toString(shiftArray(testArr1, -1)));

        System.out.println("Shift = -3");
        testArr1 = Arrays.stream(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10} ).boxed().toArray( Integer[]::new );
        System.out.println(Arrays.toString(shiftArray(testArr1, -3)));
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

    public static Object[] shiftArray(Object[] arr, int shift) {
        if (arr.length == 0 || shift == 0) return arr;
        Object tempItem; //swap-var
        int tempItemIndex; //original position of swap-var in the array
        int newPosition; // A new index for current item
        int arrSize = arr.length;
        int counterItemsShifted = 0; //Amount of array items being shifted already
        int currentPosition = 0; //A position in the array we process right now

        if (shift < 0) {
            shift = arrSize + shift; //Заменяем отрицательное смещение циклическим положительным
        } else {
            shift = shift > arrSize ? shift%arrSize : shift; //сокращаем количество полных колец сдвига
        }

        while (counterItemsShifted < arrSize) {
            //Step 1. Новая итерация,  temp  чист
            newPosition = currentPosition + shift;
            tempItem = arr[newPosition]; //Saved an item from the destination position...
            tempItemIndex = newPosition; //...and put there in item from current
            arr[newPosition] = arr[currentPosition];
            counterItemsShifted++;

            //Step 2. продолжаем итерацию с текущим temp
            int fromPosition = currentPosition - shift < 0 ? arrSize - shift + currentPosition : currentPosition - shift;
            int emptyIndex = currentPosition;
            while (fromPosition != tempItemIndex) { //while we didn't come to the original position of the tempItem...
                arr[emptyIndex] = arr[fromPosition];//...we continue to shift items
                counterItemsShifted++;

                emptyIndex = fromPosition; //remember new empty item
                //Вычисляем ячейку-донор для новой свободной ячейки
                fromPosition = fromPosition - shift < 0 ? arrSize - shift + fromPosition : fromPosition - shift;
            }
            arr[emptyIndex] = tempItem;
            counterItemsShifted++;
            if (currentPosition + 1 < arrSize) currentPosition++;
            else break; //we managed to go through all the array

        }

        return arr;
    }

}
