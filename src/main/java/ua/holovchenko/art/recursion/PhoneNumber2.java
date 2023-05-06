package ua.holovchenko.art.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PhoneNumber2 {

    public static void main(String[] args) {

        //        ------------ Example 3

        LinkedList<List<String>> letters = new LinkedList<>(); //29
        letters.add(0, List.of("a", "b", "c"));
        letters.add(1, List.of("w", "x", "y", "z"));
        List<String> words = generateAndupdate(letters);

        System.out.println("generated words: ");
        words.forEach(System.out::println);

        List.of("aw", "ax", "ay", "az",
                "bw", "bx", "by","bz",
                "cw", "cx", "cy", "cz").forEach(w -> {
            if (!words.contains(w)) {
                throw new IllegalStateException("doesn't contain " + w);
            }
            ;
        });
    }

    private static List<String> generateAndupdate(LinkedList<List<String>> allLetters) {
        if (allLetters.size() == 1) {
            return allLetters.get(0);
        }

        List<String> current = allLetters.pop();
        List<String> allWords = new ArrayList<>();
        List<String> generatedWords = generateAndupdate(allLetters);

        for (String singleLetter : current) {
            generatedWords.forEach(generatedWord -> allWords.add(singleLetter + generatedWord));
        }

        return allWords;
    }
}
