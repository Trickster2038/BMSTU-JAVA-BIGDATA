package net.alchim31.maven;

import org.apache.spark.sql.*;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Spark init");


        // Create SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("sparkbyexamples.com")
                .master("local[*]")
                .getOrCreate();

        System.out.println("Spark post-init");

        // Create Java SparkContext
        JavaSparkContext jsc = new JavaSparkContext(
                spark.sparkContext());

        // jsc.setLogLevel("OFF");

        // Create RDD
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> rdd = jsc.parallelize(data);

        // Print rdd object
        System.out.println(rdd);

        // Print rdd contents to console
        rdd.collect().forEach(System.out::println);

        // Another RDD example
        List<String[]> dataList = new ArrayList<>();
        dataList.add(new String[] { "California", "CA" });
        dataList.add(new String[] { "New York", "NY" });

        // Create RDD
        JavaRDD<Row> rdd2 = jsc.parallelize(dataList)
                .map((String[] row) -> RowFactory.create(row));

        // Print rdd object
        System.out.println(rdd2);

        // Print RDD contents to console
        rdd2.collect().forEach(System.out::println);

        // Stop the SparkSession and JavaSparkContext
        spark.stop();
        jsc.stop();
    }
}