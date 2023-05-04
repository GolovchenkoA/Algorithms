package ua.holovchenko.art.recursion;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneNumber {

    public static Map<Integer, List<String>> integerListMap = Map.of(
            0, Collections.emptyList(),
            1, Collections.emptyList(),
            2, List.of("a", "b", "c"),
            3, List.of("d", "e", "f"),
            4, List.of("g", "h", "i"),
            5, List.of("j", "k", "l"),
            6, List.of("m", "n", "o"),
            7, List.of("p", "q", "r", "s"),
            8, List.of("t", "u", "v"),
            9, List.of("w", "x", "y", "z"));

    public static void main(String[] args) {

//        ------------ Example 1
//        List<String> words = generateWords("123");
//        System.out.println(words);

        final List<String> words = new ArrayList<>();

//        ------------ Example 2
//        generateAndupdate("2", words);
//
//        List.of("a", "b", "c").forEach(w -> {
//            if (!words.contains(w)) {
//                throw new IllegalStateException("doesn't contain " + w);
//            };
//        });

        //        ------------ Example 3
        generateAndupdate("29", words);

        List.of("aw", "ax", "ay", "az",
                "bw", "bx", "by","bz",
                "cw", "cx", "cy", "cz").forEach(w -> {
            if (!words.contains(w)) {
                throw new IllegalStateException("doesn't contain " + w);
            }
            ;
        });

        //        ------------ Example 4
        generateAndupdate("23", words);

        List.of("ad", "ae", "af",
                "bd", "be", "bf",
                "cd", "ce", "cf").forEach(w -> {
            if (!words.contains(w)) {
                throw new IllegalStateException("doesn't contain " + w);
            }
            ;
        });

        generateAndupdate("012", words);

        List.of("a", "b", "c").forEach(w -> {
            if (!words.contains(w)) {
                throw new IllegalStateException("doesn't contain " + w);
            }
            ;
        });
    }

    private static void generateAndupdate(String phoneNumber, List<String> words) {
        words.clear();
        words.addAll(PhoneNumber.generateWords(phoneNumber));
        System.out.println(words);
    }

    public static List<String> generateWords(String phoneNumber) {
        List<Integer> numbers = Arrays.asList(phoneNumber.split(""))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        List<List<String>> phoneNumberLettersList = numbers
                .stream()
                .map(integerListMap::get)
                .collect(Collectors.toList());

        List<StringBuilder> allWords = getLetters(phoneNumberLettersList, new ArrayList<>());
        return allWords.stream().map(sb -> sb.toString()).collect(Collectors.toList());
    }

    private static List<StringBuilder> getLetters(List<List<String>> phoneNumberLettersList, List<StringBuilder> allWords) {

        if (phoneNumberLettersList.isEmpty()) {
            return allWords;
        }

        List<String> currentLetters = phoneNumberLettersList.get(0);
        List<List<String>> restLetters = phoneNumberLettersList.subList(1, phoneNumberLettersList.size());

        if (currentLetters.isEmpty()) {
            return getLetters(restLetters, allWords);
        }

        // first iteration
        if (allWords.isEmpty()) {
            if (currentLetters.isEmpty()) {
                return getLetters(restLetters, allWords);
            }

            List<StringBuilder> collect = currentLetters.stream().map(letter -> new StringBuilder(letter)).collect(Collectors.toList());
            allWords.addAll(collect);
            return getLetters(restLetters, allWords);
        }

        List<StringBuilder> allWordsCopy = copyStringBuilder(allWords);
        allWords.clear();
        for (String newLetter : currentLetters) {
            List<StringBuilder> allWordsCopyForLetter = copyStringBuilder(allWordsCopy);
            for (StringBuilder word : allWordsCopyForLetter) {
                StringBuilder newWord = word.append(newLetter);
                allWords.add(newWord);
            }
        }

        // last iteration
        if (restLetters.isEmpty()) {
            return allWords;
        }

        return getLetters(restLetters, allWords);
    }

    private static List<StringBuilder> copyStringBuilder(List<StringBuilder> stringBuilderList) {
        return stringBuilderList.stream().map(StringBuilder::new).collect(Collectors.toList());
    }
}

