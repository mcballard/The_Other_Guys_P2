package com.businessName.ticketApi;

public class ImpForExp {
    public static void main(String[] args) {
        FunctionalInterfaceExample addition = numOne -> numOne + 5;
        System.out.println(addition.math(5));
        FunctionalInterfaceExample subtraction = numOne -> numOne - 5;
        System.out.println(subtraction.math(5));

        FunctionalInterfaceExample multiLine = numOne -> {
            int result = numOne * 2;
            return result;
        };
    }
}
