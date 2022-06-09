package com.illidan.tsp;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;

/**
 * @author Illidan
 */
public class TestStream {
    
    @Test
    void testStreamOrder() {
        DoubleStream.of(1, 2, 3, 6, 4, 10, 0)
                // .peek(System.out::print)
                .sorted()
                // .peek(System.out::println)
                .unordered()
                .forEach(System.out::println);
        System.out.println("...");
    }
    
    @Test
    @DisplayName("test double")
    void testDouble() {
        System.out.println((Double) 1.0 == (double) 1.0);
    }
}
