package org.example;

/*
    1.	Определить класс Вектор размерности n.
    Реализовать методы сложения, вычитания, умножения, инкремента, декремента,
    индексирования.

    Определить массив из m объектов.
    Каждую из пар векторов передать в методы, возвращающие их
    скалярное произведение и длины.
    Вычислить и вывести углы между векторами.
*/

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
                            } catch (Exception e) {
                                System.out.printf("Unknown exception: %s \n", e);
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
                    } catch (Exception e) {
                        System.out.printf("Unknown exception: %s \n", e);
                    }
                }
            }
        }
    }

    static void showVectorPair(Vector v1, Vector v2, int v1_idx, int v2_idx) throws Exception {

        System.out.printf("=== Vectors %d-th and %d-th ===\n\n", v1_idx, v2_idx);

        System.out.printf("v%d: %s (len:%7.2f) %nv%d: %s (len:%7.2f)%n%n", v1_idx, v1, v1.spaceLength(), v2_idx, v2, v2.spaceLength());

        System.out.printf("v%d + v%d: %s%n%n", v1_idx, v2_idx, v1.add(v2));

        System.out.printf("v%d - v%d: %s%n%n", v1_idx, v2_idx, v1.sub(v2));

        System.out.printf("v%d * v%d (scalar): %7.2f%n%n", v1_idx, v2_idx, v1.scalar_mul(v2));

        System.out.printf("angle(v%d, v%d)[degrees]: %7.2f%n%n", v1_idx, v2_idx, v1.angle(v2));

        System.out.printf("v%d++: %s %nv%d--: %s%n%n", v1_idx, v1.inc(), v1_idx, v2.dec());

    }
}

class Vector {
    public double[] data;
    public Vector(double[] p_data) {
        this.data = p_data;
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
    public Vector inc() {
        Vector result = new Vector(new double[this.data.length]);
        for (int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] + 1;
        }
        return result;
    }

    public Vector dec() {
        Vector result = new Vector(new double[this.data.length]);
        for(int i = 0; i < this.data.length; i++) {
            result.data[i] = this.data[i] - 1;
        }
        return result;
    }
    public double getByIdx(int i) {
        return this.data[i];
    }

    public void setByIdx(int i, double val) {
        this.data[i] = val;
    }

    public double spaceLength() {
        double result = 0.0F;
        for (double elem : this.data) {
            result += pow(elem, 2);
        }
        return sqrt(result);
    }

    public double angle(Vector v) throws Exception {
        return Math.toDegrees(
                acos(
                        this.scalar_mul(v) / (this.spaceLength() * v.spaceLength())
                )
        );
    }
}

class VectorException extends Exception {
    public VectorException(String message) {
        super(message);
    }
}