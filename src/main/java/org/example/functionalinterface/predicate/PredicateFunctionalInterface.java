package org.example.functionalinterface.predicate;

import java.util.function.Predicate;

public class PredicateFunctionalInterface {
    public static void main(String[] args) {
        Predicate<Integer> p = (n) -> n % 2 == 0;
        System.out.println(p.test(2));
    }
}
