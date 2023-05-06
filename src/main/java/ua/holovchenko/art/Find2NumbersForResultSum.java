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

        // BINARY SEARCH
        // Slower than previous, but use less memory.
        // Complexity: time: O(n log n)  RAM: O(1)
        sumNumbers = getSumBinarySearch(numbers, expectedSum);
        System.out.println("Numbers (binary search): ");
        for (int sumNumber : sumNumbers) {
            System.out.println(sumNumber);
        }


        // 2 Pointers
        // The most efficient algorithm !!!!!!!!!!!!!!!!!!!!!
        // Complexity: time: O(n)  RAM: O(1)
        int[] numbers2 = {7, -1, 5, 2, 8, -3, 3};
        int expectedSum2 = 0;
        sumNumbers = getSum2Pointers(numbers2, expectedSum2);
//        System.out.println("sorted array: ");
//        for (int i : numbers2) {
//            System.out.print(i + " ");
//        }
        System.out.println("Numbers (2 pointers): ");
        for (int sumNumber : sumNumbers) {
            System.out.println(sumNumber);
        }

    }

    /**
     * This algorithm uses 2 pointers for left and right element.
     * If sum of this elements equals to expected sum - returns result.
     *
     * Complexity: time: O(n)  RAM: O(1)
     * @param numbers
     * @param expectedSum
     * @return
     */
    private static int[] getSum2Pointers(int[] numbers, int expectedSum) {
        Arrays.sort(numbers); // array must be sorted. From smallest to largest numbers

        int leftBorderIndex = 0; // next after current number
        int rightBorderIndex = numbers.length - 1;

        for (int i = 0; i < numbers.length; i++) {
            int sum = numbers[leftBorderIndex] + numbers[rightBorderIndex];
            if (sum == expectedSum) {
                return new int[]{numbers[leftBorderIndex], numbers[rightBorderIndex]};
            }

            if (sum < expectedSum) { // move left border and point to a greater number
                leftBorderIndex += 1;
            } else {  // move right border and point to a smallest number
                rightBorderIndex -= 1;
            }
        }



        return new int[0];
    }

    /**
     *  method uses binary search and sorted array
     *  Uses 2 pointers for left and right border. And pointer on middle index.
     *  Complexity: time: O(n log n)  RAM: O(1)
     * @param numbers
     * @param expectedSum
     * @return
     */
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
