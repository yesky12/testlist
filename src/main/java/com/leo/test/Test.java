package com.leo.test;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * User: Leo Date: 4/22/13 Time: 9:13 PM
 */
public class Test {
    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        // check for empty or null array
        if (values == null || values.length == 0) {
            return;
        }
        this.numbers = values;
        number = values.length;
        quickSort(0, number - 1);
        System.out.println();
    }

    private void quickSort(int low, int high) {
        int i = low, j = high;
        int pivot = numbers[low + (high - low) / 2];
        while (i <= j) {
            while (numbers[i] < pivot) {
                i++;
            }
            while (numbers[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }

        }
        if (low < j) {
            quickSort(low, j);

        }
        if (i < high) {
            quickSort(i, high);
        }
    }

    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void main(String[] args) {
        int[] number = new int[10];
        Random generator = new Random();
        for (int i = 0; i < number.length; i++) {
            number[i] = generator.nextInt(100);
            System.out.print(number[i] + " ");
        }
        System.out.println();
        Test test = new Test();
        test.sort(number);
        for (int a : number) {
            System.out.print(a + " ");
        }
    }
}