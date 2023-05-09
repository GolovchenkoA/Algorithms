package ua.holovchenko.art;

public class FindNextTemperatureDay {

    //Source: https://www.youtube.com/watch?v=-59FbGWsCgI
    public static void main(String[] args) {
//        int[] temperatureAndDays = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2}; // 45 iterations for 10 numbers
//
//        int[] nextDaysWithGreaterTemperature = getNextDaysLeftToRight(temperatureAndDays);
//        for (int i = 0; i < nextDaysWithGreaterTemperature.length; i++) {
//            System.out.println(String.format("day %d : next day temperature %d", i + 1, nextDaysWithGreaterTemperature[i]));
//        }

        getNextDaysLeftToRight2(new int[]{1, 2, 3});
    }

    // Complexity: time O(n^2)   RAM: O(n)
    private static void getNextDaysLeftToRight2(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println(array[j]);
                if (i == j) {
                    break;
                }
            }
        }
    }

    private static int[] getNextDaysLeftToRight(int[] temperatureAndDays) {
        int[] nextDaysWithGreaterTemperature = new int[temperatureAndDays.length];

        int counter = 0;

        System.out.print("Counter ");
        for (int i = 0; i < temperatureAndDays.length; i++) {
            for (int j = i + 1; j < temperatureAndDays.length; j++) {
                System.out.print(++counter + " ");
                int currentDayTemperature = temperatureAndDays[i];
                if (currentDayTemperature < temperatureAndDays[j]) {
                    nextDaysWithGreaterTemperature[i] = temperatureAndDays[j];
                    break;
                }
            }
        }
        System.out.println();
        return nextDaysWithGreaterTemperature;
    }
}
