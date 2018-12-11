package org.javacream.training.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CollectionsDataTest {

    @Test public void testList(){
        ArrayList<Data> names = new ArrayList<Data>();
        Data element1 = new Data("Hugo");
        names.add(element1);
        names.add(new Data("42"));
        names.add(new Data("Hugo"));
        names.add(new Data("Emil"));
        Assert.assertEquals(element1, names.get(0));
        Assert.assertEquals(new Data("Emil"), names.get(names.size() - 1));
        Assert.assertEquals(4, names.size());
        //Iteration: for each ... in ...
        for (Data name: names){

        }
        //Iteration: Klassisches for
        for (Integer i= 0; i < names.size(); i++ ){
            System.out.println(names.get(i));
        }
    }

    @Test public void testSet(){
        HashSet<Data> names = new HashSet<Data>();
        names.add(new Data("Hugo"));
        names.add(new Data("42"));
        names.add(new Data("Hugo"));
        names.add(new Data("Emil"));
        //Assert.assertEquals("Hugo", names.get(0));
        //Assert.assertEquals("Emil", names.get(names.size() - 1));
        Assert.assertEquals(3, names.size());
        //Iteration: for each ... in ...
        for (Data name: names){

        }
        //Iteration: Klassisches for
//        for (Integer i= 0; i < names.size(); i++ ){
//            System.out.println(names.get(i));
//        }

    }
    @Test public void testMap(){
        HashMap<Data, String> postalCodes = new HashMap<Data, String>();
        postalCodes.put(new Data("81373"), "München");
        postalCodes.put(new Data("83666"), "Munchen");
        postalCodes.put(new Data("83607"), "Holzkirchen");
        Assert.assertEquals("München", postalCodes.get(new Data("81373")));
        Assert.assertEquals(3, postalCodes.size());
        postalCodes.put(new Data("83666"), "München");
        Assert.assertEquals(3, postalCodes.size());
        for (Data postalCode: postalCodes.keySet()){
            System.out.println("Code: " + postalCode + ":" + postalCodes.get(postalCode));
        }

        //InversePostalMap City -> Liste von Integers
        HashMap<String, ArrayList<Integer>> inversePostalCodes = new HashMap<String, ArrayList<Integer>>();
        HashMap nonGeneric = new HashMap();//eher zu vermeiden
    }

}
