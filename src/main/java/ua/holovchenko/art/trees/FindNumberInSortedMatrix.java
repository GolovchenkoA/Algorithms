package ua.holovchenko.art.trees;

// source: https://www.youtube.com/watch?v=iUSxT3-d5EU

public class FindNumberInSortedMatrix {

    public static void main(String[] args) {

        int find = 14;
//        sorted array (vertically and horizontally).
//        Number below are greater than numbers above.
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15, 16},
                {2, 5, 8, 12, 19, 22},
                {3, 6, 9, 16, 22, 24},
                {10, 13, 14, 17, 24, 27},
                {18, 21, 23, 26, 30, 36}
        };

//        1st way (the worst) - iterate over each element and find the number. Time complexity: O(n*n)

//        2st way (better) - binary search. Time complexity: O(n log n)
//        first N because we iterate over each row. Log n - because we do not visit each element in row.
//        Go over each row. Find middle and search in a left or right part

//        3rd way (the best) - go through our matrix diagonally. Time complexity: O(n+m)

//        For our case where 14 less than 1 we should start from the top right conner to skip all numbers below

        boolean found = findNumber(matrix, find);
        System.out.println("Found: " + found);
    }

    // Time complexity: O(n+m)
    private static boolean findNumber(int[][] matrix, int find) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int xPointer = matrix[0].length - 1; // first length
        int yPointer = 0; // last element

        int callCounter = 0;
        while (xPointer > 0 && yPointer <= matrix.length) {
            System.out.print("Iteration: " + ++callCounter + " ");
            System.out.println("Number: " + matrix[yPointer][xPointer]);
            if (matrix[yPointer][xPointer] == find) {
                return true;
            }

            if (matrix[yPointer][xPointer] > find) {
                --xPointer;
            } else {
                ++yPointer;
            }
        }

        System.out.print("Iteration: " + ++callCounter + " ");
        System.out.println("Number: " + matrix[yPointer][xPointer]);


        return false;
    }

}
