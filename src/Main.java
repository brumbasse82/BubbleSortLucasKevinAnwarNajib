package org.example;
import java.util.concurrent.TimeUnit;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String filePath = "semisorted.txt";

        int[] numbers = readNumbersFromFile(filePath);

        // Sorter tallene
        bubbleSort(numbers);// Start tidsmåling med System.nanoTime()
        long startTime = System.nanoTime();

        // Stop tidsmåling
        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        // Udskriv tiden, det tog at sortere arrayet
        System.out.println("Tid til at sortere: " + duration + " ns");

        // Print de sorterede tal
        for (int number : numbers) {
            System.out.println(number);

        }
    }

    public static void bubbleSort(int[] array) {

        int n = array.length;
        boolean swapped;

        do {
            swapped = false;

            for (int i = 0; i < n - 1; i++) {

                if (array[i] > array[i + 1]) {

                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    swapped = true;
                }
            }

            n--;

        } while (swapped);
    }

    public static int[] readNumbersFromFile(String filePath) {

        try {

            String fileContent = new String(
                    Files.readAllBytes(Paths.get(filePath))
            );

            String[] stringNumbers = fileContent.split(",");

            int[] numbers = new int[stringNumbers.length];

            for (int i = 0; i < stringNumbers.length; i++) {
                numbers[i] = Integer.parseInt(stringNumbers[i].trim());
            }

            return numbers;

        } catch (IOException e) {

            System.out.println("Fejl under læsning af fil: " + e.getMessage());
            return new int[0];

        } catch (NumberFormatException e) {

            System.out.println("Fejl under konvertering af tal: " + e.getMessage());
            return new int[0];
        }
    }
}