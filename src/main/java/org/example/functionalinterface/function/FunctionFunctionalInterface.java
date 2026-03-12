package org.example.functionalinterface.function;

import java.util.function.Function;

public class FunctionFunctionalInterface {
    public static void main(String[] args) {
        Function<String, String> f = (name) -> "Hello " + name;
        System.out.println(f.apply("Ranjan!"));
    }
}
