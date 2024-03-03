package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/*
    Ввести с консоли n – размерность матрицы a[n][n].
    Задать значения элементов матрицы в интервале значений от -n до n
    с помощью датчика случайных чисел.

    1. Упорядочить строки (столбцы) матрицы в порядке возрастания
    значений элементов k-го столбца (строки).
*/

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of matrix:");
        int n = sc.nextInt();

        int[][] matrix = new int[n][n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(n*2 + 1) - n;
            }
        }

        System.out.println("Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d ", matrix[i][j]);
            }
            System.out.println();
        }

        System.out.println("Enter k (num of sort-by column):");
        int k = sc.nextInt() - 1;

        Arrays.sort(matrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] row1, int[] row2) {
                return Integer.compare(row1[k], row2[k]); // ascending
            }
        });

        System.out.println("Sorted matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}