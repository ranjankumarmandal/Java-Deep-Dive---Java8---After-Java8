package org.example.functionalinterface.customFunctionalInterface;

@FunctionalInterface
interface customFuncInterface {
    void print();
}

public class CustomFumctionalInterface {
    public static void main(String[] args) {
        customFuncInterface cfi = () -> System.out.println("Hello Ranjan");
        cfi.print();
    }
}
