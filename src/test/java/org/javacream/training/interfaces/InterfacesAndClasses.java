package org.javacream.training.interfaces;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class InterfacesAndClasses {
    @Test
    public void testTopLevel() {
        StringTransformerAlgorithm impl = new ToUppercaseStringTransformerAlgorithmTopLevel();
        String result = impl.transform("Hugo");
        Assert.assertEquals("HUGO", result);
        transformIntern("TopLevel", impl);
    }

    @Test
    public void testInner() {
        StringTransformerAlgorithm impl = new ToUppercaseStringTransformerAlgorithmInner();
        String result = impl.transform("Hugo");
        Assert.assertEquals("HUGO", result);
        transformIntern("Inner", impl);
    }

    @Test
    public void testMethod() {
        class ToUppercaseStringTransformerAlgorithmMethod implements StringTransformerAlgorithm {
            @Override
            public String transform(String payload) {
                return payload.toUpperCase();
            }
        }
        StringTransformerAlgorithm impl = new ToUppercaseStringTransformerAlgorithmMethod();
        String result = impl.transform("Hugo");
        Assert.assertEquals("HUGO", result);
        transformIntern("Method", impl);

    }

    @Test
    public void testAnonymous() {
        StringTransformerAlgorithm impl = new StringTransformerAlgorithm() {
            @Override
            public String transform(String payload) {
                return payload.toUpperCase();
            }
        };
        String result = impl.transform("Hugo");
        Assert.assertEquals("HUGO", result);
        transformIntern("Anonymous", impl);
        transformIntern("Anonymous", new StringTransformerAlgorithm() {
            @Override
            public String transform(String payload) {
                return payload.toUpperCase();
            }
        });


    }

    @Test
    public void testLambda() {
        StringTransformerAlgorithm impl = (String s) -> {
            return s.toUpperCase();
        };
        transformIntern("Lambda", impl);
    }

    private void demo() {
        //message ist eine Referenz auf ein Objekt vom Typ String
        //"...": String-Literal
        String message = "Hugo";
        Integer number = 42;
        String[] names = {"A", "B", "C"};
        //sta ist eine Referenz auf einen Algorithmus vom Type StringTransformer
        //(params) -> impl,: Funktionsliteral
        StringTransformerAlgorithm impl = (String s) -> {
            return s.toUpperCase();
        };
        transformIntern("Lambda", impl);
    }

    private void transformIntern(String type, StringTransformerAlgorithm sta) {
        System.out.println(type + ": " + sta.transform("Emil"));
    }

    public static class ToUppercaseStringTransformerAlgorithmInner implements StringTransformerAlgorithm {

        @Override
        public String transform(String payload) {
            return payload.toUpperCase();
        }
    }

}
