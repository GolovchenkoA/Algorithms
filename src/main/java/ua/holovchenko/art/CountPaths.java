package ua.holovchenko.art;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CountPaths {

//source: https://www.youtube.com/watch?v=GhiRlhPlJ9Q
// S - start
// F - finish
//   We can move only up or left
//   Count possible ways to get the Finish
//|---|---|---|---|---|---|---|---|---|---|
//|   |   |   |   |   |   |   |   |   | F |
//|---|---|---|---|---|---|---|---|---|---|
//|   |   |   |   |   |   |   |   |   |   |
//|---|---|---|---|---|---|---|---|---|---|
//| S |   |   |   |   |   |   |   |   |   |
//|---|---|---|---|---|---|---|---|---|---|
//  Example:
//  for width and height = 2, possible ways only 2
//  for width = 3 and height = 2, possible ways only 3

//    Rules:
//    1. paths(x,y) = paths(x, y-1) + paths(x -1, y). All possible paths is the sum of paths at two squares by the Finish
//    2. paths(x,0) = paths(0,y) = 0  i.e. At (start) x = 1 and y = 1 paths count = 0

    public static void main(String[] args) {

        AtomicInteger functionCallCounter = new AtomicInteger(0);

        int possiblePaths = 0;
        // Call recursive function start from the Finish
//        possiblePaths = paths(2, 2, functionCallCounter); // result 2, calls: 5
//        possiblePaths = paths(2, 3, functionCallCounter); //result 9
        possiblePaths = paths(4, 3, functionCallCounter); //result 10 calls: 49 // Time complexity: O(2^(x+y))
        functionCallCounter.set(0);
        System.out.println("possiblePaths : " + possiblePaths);

        possiblePaths = pathsImproved(4, 3, new int[4 + 1][3 + 1], functionCallCounter); //result 10 calls: 23 // Time complexity: O(n*m)
        functionCallCounter.set(0);
        System.out.println("possiblePaths (improved) : " + possiblePaths);

    }

    /**
     * Count of paths for a square is sum of paths for squares on the right side and below
     * !!!! This function repeats calculation for some squares.  Complexity: O(2^(x+y))
     */
    private static int paths(int x, int y, AtomicInteger functionCallCounter) {
        System.out.println("c: " + functionCallCounter.incrementAndGet()); // count every call
        if (x < 1 || y < 1) {
            return 0;
        }

//        System.out.println("c: " + functionCallCounter.incrementAndGet()); // do not count case where x or y is negative

        if (x == 1 && y == 1) { // Start
            return 1;
        }
        return paths(x - 1, y, functionCallCounter) + paths(x, y - 1, functionCallCounter);
    }

    /**
     * Count paths with caching results. Time complexity: O(n*m)
     */
    private static int pathsImproved(int x, int y, int[][] visitedSquares, AtomicInteger functionCallCounter) {
        System.out.println("c: " + functionCallCounter.incrementAndGet()); // count every call
        if (x < 1 || y < 1) {
            return 0;
        }

//        System.out.println("c: " + functionCallCounter.incrementAndGet()); // do not count case where x or y is negative

        if (x == 1 && y == 1) { // Start
            return 1;
        }

        if (visitedSquares[x][y] != 0) {
            String msg = String.format("value already calculated for [%d,%d] ", x, y);
            System.out.println(msg);
            return visitedSquares[x][y]; // already calculated value
        }

        visitedSquares[x][y] =  pathsImproved(x - 1, y, visitedSquares, functionCallCounter) + pathsImproved(x, y - 1, visitedSquares, functionCallCounter);
        return visitedSquares[x][y];
    }
}
