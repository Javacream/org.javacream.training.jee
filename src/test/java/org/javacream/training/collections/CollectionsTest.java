package org.javacream.training.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CollectionsTest {

    //@Test
    public void testList() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Hugo");
        names.add("42");
        names.add("Hugo");
        names.add("Emil");
        Assert.assertEquals("Hugo", names.get(0));
        Assert.assertEquals("Emil", names.get(names.size() - 1));
        Assert.assertEquals(4, names.size());
        //Iteration: for each ... in ...
        for (String name : names) {

        }
        //Iteration: Klassisches for
        for (Integer i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }
    }

    //@Test
    public void testSet() {
        HashSet<String> names = new HashSet<String>();
        names.add("Hugo");
        names.add("42");
        names.add("Hugo");
        names.add("Emil");
        //Assert.assertEquals("Hugo", names.get(0));
        //Assert.assertEquals("Emil", names.get(names.size() - 1));
        Assert.assertEquals(3, names.size());
        //Iteration: for each ... in ...
        for (String name : names) {

        }
        names.add("Hugo");
        names.add("Hugo");
        names.add("Hugo");
        names.add("Hugo");
        names.add("Hugo");
        Assert.assertEquals(3, names.size());
        //Iteration: Klassisches for
//        for (Integer i= 0; i < names.size(); i++ ){
//            System.out.println(names.get(i));
//        }

    }

    //@Test
    public void testMap() {
        HashMap<Integer, String> postalCodes = new HashMap<Integer, String>();
        postalCodes.put(81373, "München");
        postalCodes.put(83666, "Munchen");
        postalCodes.put(83607, "Holzkirchen");
        Assert.assertEquals("München", postalCodes.get(81373));
        Assert.assertEquals(3, postalCodes.size());
        postalCodes.put(83666, "München");
        Assert.assertEquals(3, postalCodes.size());
        for (Integer postalCode : postalCodes.keySet()) {
            System.out.println("Code: " + postalCode + ":" + postalCodes.get(postalCode));
        }

        //InversePostalMap City -> Liste von Integers
        HashMap<String, ArrayList<Integer>> inversePostalCodes = new HashMap<String, ArrayList<Integer>>();
        HashMap nonGeneric = new HashMap();//eher zu vermeiden
    }

    @Test
    public void testWorkingWithList() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Hugo");
        names.add("42");
        names.add("Hugolinde");
        names.add("Emil");
        names.add("Hu");


        names.stream().filter((s) -> s.startsWith("H")).map((s) -> s.toUpperCase()).map((s) -> s.length()).forEach((Integer element) -> {
            System.out.println(element);
        });

        Stream<String> stream = names.stream();
        Stream<String> filtered = stream.filter(  (s) -> s.startsWith("H"));
        Stream<String> transformedToUpperCase = filtered.map((s) -> s.toUpperCase());
        Stream<Integer> transformedToLength = transformedToUpperCase.map((s) -> s.length());
        transformedToLength.forEach(System.out::println);


        class ForEachPrinter implements Consumer<Integer>{

            @Override
            public void accept(Integer element) {
                System.out.println(element);
            }
        }

        class ToLengthMapper implements Function<String, Integer>{

            @Override
            public Integer apply(String s) {
                return s.length();
            }
        }
        class ToUpperMapper implements Function<String, String>{

            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        }
        class FilterFirstLetterH implements Predicate<String>{

            @Override
            public boolean test(String s) {
                return s.startsWith("H");
            }
        }
        Stream<String> stream2 = names.stream();
        Stream<String> filtered2 = stream2.filter( new FilterFirstLetterH());
        Stream<String> transformedToUpperCase2 = filtered2.map(new ToUpperMapper());
        Stream<Integer> transformedToLength2 = transformedToUpperCase2.map(new ToLengthMapper());
        transformedToLength2.forEach(new ForEachPrinter());

    }
}
