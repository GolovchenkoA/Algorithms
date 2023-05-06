package ua.holovchenko.art;

import java.util.*;

public class Find2NumbersForResultSum {

    public static void main(String[] args) {
        int[] numbers = {-1, 5, 2, 8};
        int expectedSum = 7;

        // ITERATION OVER COLLECTION AND SUB-COLLECTIONS
        // the most straightforward
        // Complexity: time: O(n^2)  RAM: O(1)
        int[] sumNumbers = getSumIterateOverAllNumbers(numbers, expectedSum);
        System.out.println("Numbers (multiple iterations): ");
        for (int sumNumber : sumNumbers) {
            System.out.println(sumNumber);
        }

        // ITERATION OVER COLLECTION ONLY ONE TIME
        // Faster, but use more memory
        // Complexity: time: O(n)  RAM: O(n)
        sumNumbers = getSumIterateOneTime(numbers, expectedSum);
        System.out.println("Numbers (1 iteration): ");
        for (int sumNumber : sumNumbers) {
            System.out.println(sumNumber);
        }

        // BINARY SEARCH
        // Slower than previous, but use less memory.
        // Complexity: time: O(n log n)  RAM: O(1)
        sumNumbers = getSumBinarySearch(numbers, expectedSum);
        System.out.println("Numbers (binary search): ");
        for (int sumNumber : sumNumbers) {
            System.out.println(sumNumber);
        }


    }

    private static int[] getSumBinarySearch(int[] numbers, int expectedSum) {
        Arrays.sort(numbers);

        // start from the beginning and iterate over the array
        for (int i = 0; i < numbers.length; i++) {
            int diff = expectedSum - numbers[i];
            int leftBorderIndex = i + 1; // next after current number
            int rightBorderIndex = numbers.length - 1;

            while (leftBorderIndex <= rightBorderIndex) {
                int arrayMiddleIndex = leftBorderIndex + (rightBorderIndex-leftBorderIndex)/2; // in original example it was: leftBorderIndex + )rightBorderIndex-1)/2
                int arrayMiddleElement = numbers[arrayMiddleIndex];
                if (numbers[arrayMiddleIndex] == diff) {
                    return new int[] {numbers[i], arrayMiddleElement};
                } else if (diff < arrayMiddleElement) {
                    rightBorderIndex = arrayMiddleIndex - 1;
                } else {
                    leftBorderIndex = arrayMiddleIndex + 1;
                }
            }
        }

        return new int[0];
    }

    private static int[] getSumIterateOneTime(int[] numbers, int expectedSum) {
        HashSet<Integer> processedNumbers = new HashSet<>();

        for (int number : numbers) {
            int diff = expectedSum - number;
            if (processedNumbers.contains(diff)) {
                return new int[] {number, diff};
            } else {
                processedNumbers.add(number);
            }
        }

        return new int[0];
    }

    private static int[] getSumIterateOverAllNumbers(int[] numbers, int expectedSum) {

            for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; i < numbers.length; i++) {
                if (numbers[i] + numbers[j] == expectedSum) {
                    return new int[]{numbers[i], numbers[j]};
                }
            }
            }

        return new int[0];
    }
}
