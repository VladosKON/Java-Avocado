package com.avocado;

import java.io.*;
import java.util.*;

// Задача 30. В файле data_avocado_sale.txt содержится информация о продаже авокадо. Формат
// файла следующий: в каждых трех строках записаны: дата продажи, цена за штуку, количество
// проданных в этот день фруктов. Найти минимальную и максимальную цену авокадо. Найти
// общую сумму, вырученную за продажу фруктов. Найти среднее количество проданных в день
// фруктов. Найти кол-во дней в которые вырученная от продажи сумма превысила заданное
// значение, которое вводит пользователь.

public class Main {
    public static void main(String[] args) throws IOException {
        double[] data = readData("src/com/data/data_avocado_sale.txt");
        int[] dataCount = readDataCount("src/com/data/data_avocado_sale.txt");
//        System.out.println(Arrays.toString(data));
//        System.out.println(Arrays.toString(dataCount));
        findMax(data);
        findMin(data);
        totalPrice(data);
        findAverageCount(dataCount);
        double findPrice = enterNum();
        findCountDate(data, findPrice);
    }

    // Поиск максимальной цены на авокадо
    private static void findMax(double[] data) {
        double max = data[0];
        for (double x : data) {
            if (x > max) max = x;
        }
        System.out.println("Max price = " + max);
    }

    // Поиск минимальной цены на авокадо.
    private static void findMin(double[] data) {
        double min = data[0];
        for (double x : data) {
            if (x < min) min = x;
        }
        System.out.println("Min price = " + min);
    }

    // Поиск общей суммы, вырученной за продажу фруктов.
    private static void totalPrice(double[] data) {
        double totalPrice = 0.0;
        for (int i = 0; i < data.length; i++) {
            totalPrice += data[i];
        }
        System.out.format("Total price = %.1f\n", totalPrice);
    }

    // Поиск среднего количества проданных в день фруктов
    private static void findAverageCount(int[] dataCount){
        double totalCount = 0;
        double averageCount = 0.0;
        for (int x : dataCount){
            totalCount += x;
        }
        averageCount = totalCount/dataCount.length;
        System.out.format("Average sold count = %.1f", averageCount);
    }

    // Функция ввода числа пользователем.
    public static double enterNum(){
        double x=-1;
        Scanner s = new Scanner(System.in);
        while(x<=0) {
            System.out.println("\nEnter price: ");
            if (s.hasNextDouble()) {
                x = s.nextDouble();
                if(x>0)
                    return x;
                else
                    System.out.println("Negative!");
            }
            else {
                System.out.println("It is not number!");
                String tmp = s.nextLine();
            }
        }
        return x;
    }

    // Поиск кол-ва дней.
    public static void findCountDate(double[] data, double findPrice){
        int count = 0;
        for (double x : data){
            if (x > findPrice){
                count++;
            }
        }
        System.out.println("Numbers of days = " + count);
    }

    public static double[] readData(String fileName) {
        double[] price = new double[100];
        int i = 0;
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useLocale(Locale.ENGLISH);
            String str;
            if (sc.hasNext())
                str = sc.nextLine();
            else throw new IOException();
            while (sc.hasNext()) {
                // Регулярное выражение, равное DD.MM.YYYY
                if (str.matches("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)")) {
                    if (sc.hasNextDouble()) {
                        price[i] = sc.nextDouble();
                        i++;
                        if (i == price.length) {
                            double[] price1 = new double[price.length * 2];
                            System.arraycopy(price, 0, price1, 0, price.length);
                            price = price1;
                        }
                        str = sc.nextLine();
                    } else throw new IOException();
                } else {
                    str = sc.nextLine();
                }
                if (sc.hasNext()) str = sc.nextLine();
                else break;
                if (sc.hasNext()) str = sc.nextLine();
                else break;
            }
        } catch (IOException e) {
            System.out.println("file error");
        }
        return Arrays.copyOf(price, i);
    }

    public static int[] readDataCount(String fileName) {
        int[] count = new int[100];
        int i = 0;
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useLocale(Locale.ENGLISH);
            String str;
            if (sc.hasNext())
                str = sc.nextLine();
            else throw new IOException();
            while (sc.hasNext()) {
                // Регулярное выражение, равное DD.MM.YYYY
                if (str.matches("(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)")) {
                    str = sc.nextLine();
                    if (sc.hasNextInt()) {
                        count[i] = sc.nextInt();
                        i++;
                        if (i == count.length) {
                            int[] count1 = new int[count.length * 2];
                            System.arraycopy(count, 0, count1, 0, count.length);
                            count = count1;
                        }
                    } else throw new IOException();
                }
                if (sc.hasNext()) str = sc.nextLine();
                else break;
                if (sc.hasNext()) str = sc.nextLine();
                else break;
            }
        } catch (IOException e) {
            System.out.println("file error");
        }
        return Arrays.copyOf(count, i);
    }
}
