package org.example.functionalinterface.consumer;

import java.util.function.Consumer;

public class ConsumerFunctionalInterface {
    public static void main(String[] args) {
        Consumer<String> c = (name) -> System.out.println(name);

        c.accept("Ranjan");
    }
}
