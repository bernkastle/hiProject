package com;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        int a = sumAll(numbers, n -> n > 3);
        System.out.println(a);
    }

    public static int sumAll(List<Integer> numbers, Predicate<Integer> p){
        int total = 0;
        for (int number:numbers){
            if (p.test(number)){
                total += number;
            }
        }
        return total;
    }

}
