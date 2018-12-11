package org.javacream.training.interfaces;

public class ToUppercaseStringTransformerAlgorithmTopLevel implements StringTransformerAlgorithm {

    @Override
    public String transform(String payload) {
        return payload.toUpperCase();
    }
}
