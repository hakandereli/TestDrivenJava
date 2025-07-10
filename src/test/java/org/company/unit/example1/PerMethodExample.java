package org.company.unit.example1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class PerMethodExample {

    private int counter = 0;

    @BeforeAll
    static void init() {
        System.out.println("BeforeAll (PER_METHOD) - static");
    }

    @Test
    void test1() {
        counter++;
        System.out.println("Test1 (PER_METHOD) - counter = " + counter);
    }

    @Test
    void test2() {
        counter++;
        System.out.println("Test2 (PER_METHOD) - counter = " + counter);
    }
}

/**
 * BeforeAll (PER_METHOD) - static
 * Test1 (PER_METHOD) - counter = 1
 * Test2 (PER_METHOD) - counter = 1
 */
