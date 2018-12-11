package org.javacream.training.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CollectionsTestWithMethodReference {

    @Test
    public void testWorkingWithList() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Hugo");
        names.add("42");
        names.add("Hugolinde");
        names.add("Emil");
        names.add("Hu");

        Stream<String> stream = names.stream();
        Stream<String> filtered = stream.filter(Utility::testIfFirstLetterIsH);
        Stream<String> transformedToUpperCase = filtered.map(Utility::mapToUpper);
        Stream<Integer> transformedToLength = transformedToUpperCase.map(Utility::mapToLength);
        transformedToLength.forEach(System.out::println);

        Utility u = new Utility();
        Stream<String> stream2 = names.stream();
        Stream<String> filtered2 = stream2.filter(u::nonStaticTestIfFirstLetterIsH);
        Stream<String> transformedToUpperCase2 = filtered2.map(u::nonStaticMapToUpper);
        Stream<Integer> transformedToLength2 = transformedToUpperCase2.map(u::nonStaticMapToLength);
        transformedToLength2.forEach(System.out::println);

    }

    static class Utility {
        public static Integer mapToLength(String s) {
            return s.length();
        }

        public static String mapToUpper(String s) {
            return s.toUpperCase();
        }

        public static boolean testIfFirstLetterIsH(String s) {
            return s.startsWith("H");
        }

        public Integer nonStaticMapToLength(String s) {
            return s.length();
        }

        public String nonStaticMapToUpper(String s) {
            return s.toUpperCase();
        }

        public boolean nonStaticTestIfFirstLetterIsH(String s) {
            return s.startsWith("H");
        }

    }
}
