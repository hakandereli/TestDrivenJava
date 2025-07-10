package org.company.unit.example1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PerClassExample {

    private int counter = 0;

    @BeforeAll
    void init() {
        System.out.println("BeforeAll (PER_CLASS) - static değil");
    }

    @Test
    void test1() {
        counter++;
        System.out.println("Test1 (PER_CLASS) - counter = " + counter);
    }

    @Test
    void test2() {
        counter++;
        System.out.println("Test2 (PER_CLASS) - counter = " + counter);
    }
}

/**
 * BeforeAll (PER_CLASS) - static değil
 * Test1 (PER_CLASS) - counter = 1
 * Test2 (PER_CLASS) - counter = 2
 */
