package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/*
    Ввести с консоли n – размерность матрицы a[n][n].
    Задать значения элементов матрицы в интервале значений от -n до n
    с помощью датчика случайных чисел.

    2. Выполнить циклический сдвиг заданной матрицы на k
    позиций вправо (влево, вверх, вниз).
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

        System.out.println("Enter k (shift positions):");
        int k = sc.nextInt();

        for (int[] row : matrix) {
            int len = row.length;
            int[] temp = new int[len];

            for (int j = 0; j < len; j++) {
                temp[(j + k) % len] = row[j];
            }

            System.arraycopy(temp, 0, row, 0, len);
        }

        System.out.println("Shifted matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}