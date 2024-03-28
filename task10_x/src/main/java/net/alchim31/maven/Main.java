package net.alchim31.maven;

import org.apache.spark.sql.*;
import org.apache.spark.api.java.JavaSparkContext;



public class Main {
    public static void main(String[] args) {

        System.out.println("Spark init");

        // Create SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("sparkbyexamples.com")
                .master("local[*]")
                .getOrCreate();

        spark.sparkContext().setLogLevel("ERROR");

        System.out.println("Spark post-init");

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        // Create Java SparkContext
        JavaSparkContext jsc = new JavaSparkContext(
                spark.sparkContext());

        Dataset<Row> dataset = spark.read().format("csv")
                .option("header", "true")
                .load(System.getProperty("user.dir") + "\\transaction_dataset.csv");

        dataset.createOrReplaceTempView("tx_ether");

        System.out.println("\n\n\n1. sample");

        spark.sql("select * from tx_ether").show();

        System.out.println("\n\n\n2. created contracts top");

        spark.sql("select Address, `Number of Created Contracts`, FLAG from tx_ether order by cast(`Number of Created Contracts` as int) desc").show();

        System.out.println("\n\n\n3. received-rate top");

        spark.sql("select Address, `Avg min between received tnx`, FLAG from tx_ether where `Avg min between received tnx` > 0.00000001 order by `Avg min between received tnx` asc").show();

        System.out.println("\n\n\n4. rate for groups");

        spark.sql("select FLAG, avg(`Avg min between sent tnx`) sent_rate, avg(`Avg min between received tnx`) recieved_rate from tx_ether group by FLAG").show();

        System.out.println("\n\n\n5. most fraud-profitable tokens");

        spark.sql("select ` ERC20 most sent token type`, ` ERC20_most_rec_token_type`, ` ERC20 max val rec` from tx_ether where FLAG = 1 order by cast(` ERC20 max val rec` as int) desc").show();

        System.out.println("\n\n\n6. most honest-profitable tokens");

        spark.sql("select ` ERC20 most sent token type`, ` ERC20_most_rec_token_type`, ` ERC20 max val rec` from tx_ether where FLAG = 0 order by cast(` ERC20 max val rec` as int) desc").show();

        System.out.println("\n\n\n7. most fraud-rated tokens");

        spark.sql("select ` ERC20 most sent token type`, ` ERC20_most_rec_token_type`, count(1) from tx_ether where FLAG = 1 group by ` ERC20 most sent token type`, ` ERC20_most_rec_token_type` order by count(1) desc").show();

        System.out.println("\n\n\n8. most honest-rated tokens");

        spark.sql("select ` ERC20 most sent token type`, ` ERC20_most_rec_token_type`, count(1) from tx_ether where FLAG = 0 group by ` ERC20 most sent token type`, ` ERC20_most_rec_token_type` order by count(1) desc").show();

        System.out.println("\n\n\n9. avg val received top");

        spark.sql("select Address, `avg val received`, FLAG from tx_ether order by cast(`avg val received` as numeric(23,5)) desc").show();

        System.out.println("\n\n\n10. avg val received bottom");

        spark.sql("select Address, `avg val received`, FLAG from tx_ether where `avg val received` > 0.00001 order by cast(`avg val received` as numeric(23,5)) asc").show();

        // Stop the SparkSession and JavaSparkContext
        spark.stop();
        jsc.stop();
    }
}