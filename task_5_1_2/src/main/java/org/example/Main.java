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
import java.util.Scanner;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Vector class work example ===\n");

        Scanner sc = new Scanner(System.in);

        int m = 0;
        String buf;
        boolean retry_fl = true;

        while(retry_fl) {
            try {
                System.out.println("Enter number of vectors:");
                buf = sc.next();
                m = Integer.parseInt(buf);
                retry_fl = false;
            } catch (NumberFormatException e) {
                System.out.println("Exception: num format is incorrect!");
            }
        }

        Vector[] vectors = new Vector[m];

        for(int i = 0; i < m; i++) {
            retry_fl = true;
            int sz = 0;
            while(retry_fl) {
                try {
                    System.out.printf("Enter size at vectors[%d]:\n", i);
                    buf = sc.next();
                    sz = Integer.parseInt(buf);
                    retry_fl = false;
                } catch (NumberFormatException e) {
                    System.out.println("Exception: num format is incorrect!");
                }
                if (sz > 0) {
                    double[] elems = new double[sz];
                    for(int j = 0; j < sz; j++) {
                        boolean inner_retry_fl = true;
                        while(inner_retry_fl) {
                            try {
                                System.out.printf("Enter elem at vectors[%d][%d]:\n", i, j);
                                buf = sc.next();
                                elems[j] = Double.parseDouble(buf);
                                inner_retry_fl = false;
                            } catch (NumberFormatException e) {
                                System.out.println("Exception: num format is incorrect!");
                            }
                        }
                    }
                    vectors[i] = new Vector(elems);
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = i; j < m; j++) {
                if (i != j) {
                    try {
                        showVectorPair(vectors[i], vectors[j], i, j);
                    } catch (NullPointerException e) {
                        System.out.println("Exception: vector is not init!");
                    } catch (VectorException e) {
                        System.out.printf("VectorException: %s \n", e);
                    }
                    catch (Exception e) {
                        System.out.printf("Unknown exception: %s \n", e);
                    }
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
            throw new VectorException("Sizes don't match!");
        }
        for(int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] + v.data[i];
        }

        return result;
    }

    public Vector sub(Vector v) throws Exception {
        Vector result = new Vector(new double[this.data.length]);
        if (this.data.length != v.data.length) {
            throw new VectorException("Sizes don't match!");
        }
        for(int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] - v.data[i];
        }

        return result;
    }
    public double scalar_mul(Vector v) throws Exception {
        double result = 0;
        if (this.data.length != v.data.length) {
            throw new VectorException("Sizes don't match!");
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
            throw new VectorException("Sizes don't match!");
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

class VectorException extends Exception {
    public VectorException(String message) {
        super(message);
    }
}