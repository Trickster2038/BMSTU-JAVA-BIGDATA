package org.example;

/*
    2.	Определить класс Вектор размерности n. Определить несколько конструкторов. 
    Реализовать методы для вычисления модуля вектора, скалярного произведения, сложения,
    вычитания, умножения на константу.
    Объявить массив объектов. Написать метод, который для заданной пары векторов будет определять, 
    являются ли они коллинеарными или ортогональными.
*/

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("=== Vector class work example ===\n");

        Vector[] vectors_array = new Vector[]{
                new Vector(new double[]{1, 0, 0}),
                new Vector(new Vector(new double[]{4, 0, 0})),
                new Vector((ArrayList<Double>) new ArrayList<>(Arrays.asList(0.0, 1.0, 1.0)))
        };

        for(int i = 0; i < vectors_array.length; i++) {
            System.out.printf("Vector[%d]: %s%n%n", i, vectors_array[i]);
            System.out.printf("Vector[%d] * %d: %s%n%n", i, i, vectors_array[i].const_mul(i));
        }

        for(int i = 0; i < vectors_array.length; i++) {
            for(int j = 0; j < vectors_array.length; j++) {
                if (i != j) {
                    showVectorPair(vectors_array[i], vectors_array[j], i, j);
                }
            }
        }
    }

    static void showVectorPair(Vector v1, Vector v2, int v1_idx, int v2_idx) throws Exception {

        System.out.printf("=== Vectors %d-th and %d-th ===\n\n", v1_idx, v2_idx);

        System.out.printf("v%d: %s (len:%7.2f) %nv%d: %s (len:%7.2f)%n%n", v1_idx, v1, v1.module(), v2_idx, v2, v2.module());

        System.out.printf("v%d + v%d: %s%n%n", v1_idx, v2_idx, v1.add(v2));

        System.out.printf("v%d - v%d: %s%n%n", v1_idx, v2_idx, v1.sub(v2));

        System.out.printf("v%d * v%d (scalar): %7.2f%n%n", v1_idx, v2_idx, v1.scalar_mul(v2));

        System.out.printf("v%d and v%d are orthogonal: %b%n%n", v1_idx, v2_idx, v1.isOrtogonal(v2));

        System.out.printf("v%d and v%d are collinear: %b%n%n", v1_idx, v2_idx, v1.isCollinear(v2));

    }
}

class Vector {
    public double[] data;
    public Vector(double[] p_data) {
        this.data = p_data;
    }

    public Vector(ArrayList<Double> p_data) {
        this.data = new double[p_data.size()];
        for (int i = 0; i < p_data.size(); i++) {
            this.data[i] = p_data.get(i);
        }
    }

    public Vector(Vector p_data) {
        this.data = p_data.data.clone();
    }

    public Vector(int k) {
        this.data = new double[k];
        for (int i = 0; i < k; i++) {
            this.data[i] = 0.0;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[ ");
        for (double elem : this.data) {
            s.append(String.format("%7.2f ", elem));
        }
        s.append("]");
        return String.valueOf(s);
    }

    public Vector add(Vector v) throws Exception {
        Vector result = new Vector(new double[this.data.length]);
        if (this.data.length != v.data.length) {
            throw new Exception("Sizes don't match!");
        }
        for(int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] + v.data[i];
        }

        return result;
    }

    public Vector sub(Vector v) throws Exception {
        Vector result = new Vector(new double[this.data.length]);
        if (this.data.length != v.data.length) {
            throw new Exception("Sizes don't match!");
        }
        for(int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] - v.data[i];
        }

        return result;
    }
    public double scalar_mul(Vector v) throws Exception {
        double result = 0;
        if (this.data.length != v.data.length) {
            throw new Exception("Sizes don't match!");
        }
        for(int i = 0; i < this.data.length; i++) {
            result += this.data[i] * v.data[i];
        }

        return result;
    }
    public Vector const_mul(double k) throws Exception {
        Vector result = new Vector(new double[this.data.length]);
        for(int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] * k;
        }
        return result;
    }

    public double module() {
        double result = 0.0F;
        for (double elem : this.data) {
            result += pow(elem, 2);
        }
        return sqrt(result);
    }

    public boolean isCollinear(Vector v) throws Exception {
        double epsilon = 0.001;
        boolean result = true;
        double k = 0;
        boolean k_init = false;

        if (this.data.length != v.data.length) {
            throw new Exception("Sizes don't match!");
        }
        for(int i = 0; i < this.data.length; i++) {
            if(abs(this.data[i]) > epsilon && abs(v.data[i]) > epsilon && !k_init) {
                k = this.data[i] / v.data[i];
                k_init = true;
            } else if (abs(this.data[i]) > epsilon && abs(v.data[i]) > epsilon && k_init) {
                if(abs(this.data[i] / v.data[i] - k) > epsilon) {
                    result = false;
                }
            }

            if(abs(this.data[i]) > epsilon ^ abs(v.data[i]) > epsilon) {
                result = false;
            }
        }
        return result;
    }

    public boolean isOrtogonal(Vector v) throws Exception {
        double epsilon = 0.001;
        return abs(this.scalar_mul(v)) < epsilon;
    }
}