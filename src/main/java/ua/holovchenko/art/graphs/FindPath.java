package ua.holovchenko.art.graphs;

import java.util.*;

// source: Book Grokking Algorithms. Chapter 6
public class FindPath {

    record Person(String name, boolean isProgrammer) {
    }

    public static void main(String[] args) {
        // Task: find the nearest friend who is programmer
//         In this example we use Depth-First Search (DFS) to iterate over a directed graph.
//         This approach allows us to go through 1-st level friends and only after that checks 2nd level friends (Friends of Friends)

        Person me = new Person("me", false);
        Map<Person, List<Person>> directGraph = Map.of(
                me, List.of(new Person("Friend 1", false), new Person("Friend 2", false)),
                new Person("Friend 1", false), List.of(new Person("Friend 1-1", true), new Person("Friend 1-2", false)),
                new Person("Friend 2", false), List.of(new Person("Friend 2-1", true), new Person("Friend 2-2", false)),
//                new Person("Friend 1-1", false), Collections.emptyList(),
                new Person("Friend 1-1", false), List.of(me), // should be an infinity loop if the method implemented incorrectly
                new Person("Friend 1-2", false), Collections.emptyList(),
                new Person("Friend 2-1", false), Collections.emptyList(),
                new Person("Friend 2-2", false), Collections.emptyList()
        );


        boolean firstFound = false;
        List<Person> result = findFriendDFS(directGraph, me, firstFound);

        result.forEach(System.out::println);
    }

    /*
        Depth-First Search (DFS). Time Complexity O(n)
     */
    private static List<Person> findFriendDFS(Map<Person, List<Person>> friends, Person firstPerson, boolean firstFound) {
        ArrayList<Person> people = new ArrayList<>();
        LinkedList<Person> queue = new LinkedList<>();
        Set<Person> visitedNodes = new HashSet<>();
        queue.add(firstPerson);

        int counter = 0;

        while (!queue.isEmpty()){
            Person person = queue.pop();
            System.out.println(String.format("%d Check person : %s", ++counter, person));

            if (visitedNodes.contains(person)) {
                continue;
            }
            visitedNodes.add(person);

            if (person.isProgrammer) {
                if (firstFound) {
                    return List.of(person);
                } else {
                    people.add(person);

                    List<Person> friendsOfFriend = friends.get(person);
                    if (friendsOfFriend != null) {
                        queue.addAll(friendsOfFriend);
                    }
                }
            } else {
                List<Person> friendsOfFriend = friends.get(person);
                if (friendsOfFriend != null) {
                    queue.addAll(friendsOfFriend);
                }
            }
        }

        return people;
    }


};
