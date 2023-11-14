import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Perceptron {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int mode = 0;
        File file_final = new File("E:\\old projects\\Perceptron\\data\\perceptron.data");
        File file_test = new File("E:\\old projects\\Perceptron\\data\\perceptron.test.data");
        double[][] arr_final = Reader.fileReader(file_final);
        double[][] arr_test = Reader.fileReader(file_test);
        double[] arr_expected_test = Reader.expectedOutputReader(file_test);
        double[] arr_expected_final = Reader.expectedOutputReader(file_final);
        double bias = 2.0;
        double[] weight = new double[arr_final.length];
        for (int i = 0; i < weight.length; i++) {
            weight[i] = (Math.random() * (1 + 1) - 1);
        }
        double[] predicted_final;
        System.out.println("Enter the learning rate:");
        double alfa = sc.nextDouble();
        int count = 0;
        do {
             predicted_final = netCal_final(arr_final, weight, bias, alfa, arr_expected_final);
            count++;
        }while (count < 15);
        Accuracy(arr_expected_final, predicted_final);
        double[] predicted_test = netCal_test(arr_test, weight, bias, alfa, arr_expected_test);
        Accuracy(arr_expected_test, predicted_test);
    }

    public static double[] netCal_final(double[][] arr, double[] weight, double bias, double alfa, double[] expected) {
        double[] net = new double[arr.length];
        double[] y = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                net[i] += arr[i][j] * weight[j];
            }
            net[i] -= bias;
            if (net[i] >= 0) {
                y[i] = 1;
            } else {
                y[i] = 0;
            }
            for (int k = 0; k < arr[i].length; k++) {
                weight[k] = weight[k] + alfa * (expected[i] - y[i]) * arr[i][k];
            }
            bias = bias - alfa * (expected[i] - y[i]);
        }
        return y;
    }

    public static double[] netCal_test(double[][] arr, double[] weight, double bias, double alfa, double[] expected) {
        double[] net = new double[arr.length];
        double[] y = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                net[i] += arr[i][j] * weight[j];
            }
            net[i] -= bias;
            if (net[i] >= 0) {
                y[i] = 1;
            } else {
                y[i] = 0;
            }
        }
        return y;
    }

    public static double Accuracy(double[] expected, double[] predicted) {
        double count = 0;
        double accuracy = 0;
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] == predicted[i]) {
                count++;
            }
            accuracy = count / expected.length * 100;
            System.out.println("Accuracy is " + accuracy + "%");
        }
        return accuracy;
    }

    public static void mode2(double[] arr_expected, double[] predicted) {
        Accuracy(arr_expected, predicted);
        Scanner sc = new Scanner(System.in);
        double[] vector = new double[4];
        double net = 0;
        double y = 0;
        double bias = 2.0;
        double[] weight = new double[4];
        System.out.println("Enter expected value: 0 - Iris-setosa, 1 - Iris-versicolor");
        double expected = sc.nextDouble();
        for (int i = 0; i < weight.length; i++) {
            weight[i] = (Math.random() * (1 + 1) - 1);
        }
        System.out.println("Enter the learning rate; -1 to exit");
        double alfa = sc.nextDouble();
        System.out.println("Enter your vector:");
        for (int i = 0; i < vector.length; i++) {
            vector[i] = sc.nextDouble();
        }
        System.out.println();
        for (int i = 0; i < vector.length; i++) {
            net += weight[i] * vector[i] - bias;
        }
        if (net >= 0) {
            y = 1;
        } else {
            y = 0;
        }
        for (int k = 0; k < 4; k++) {
            weight[k] = weight[k] + alfa * (expected - y) * vector[k];
        }
        bias = bias - alfa * (expected - y);

        if (y == 0) {
            System.out.println("Iris-setosa");
        } else {
            System.out.println("Iris-versicolor");
        }
    }
}



