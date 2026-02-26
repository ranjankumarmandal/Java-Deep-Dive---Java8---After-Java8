package org.example.functionalinterface.supplier;

import java.util.function.Supplier;

public class SupplierFunctionalInterface {
    public static void main(String[] args) {
        Supplier<String> s = () -> "Ranjan";
        System.out.println(s.get());
    }
}
