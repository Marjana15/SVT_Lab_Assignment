package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    private Calculator calculator;

    //Question 1
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all tests");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed");
    }

    //Question 2
    @Test
    void add() {
        assertEquals(5, calculator.add(2, 3));
        assertNotEquals(6, calculator.add(2, 3));
    }

    @Test
    void test() {
        Calculator nullCalculator = null;
        assertNull(nullCalculator);
        assertNotNull(calculator);
    }

    @Test
    void booleanAssertions() {
        assertTrue(calculator.add(2, 2)== 4, "2 + 2 = 4");
        assertFalse(calculator.add(2, 2) == 5, "2 + 2 should not equal 5");
    }

    //Question 3
    @Test
    void divide() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
        assertDoesNotThrow(() -> calculator.divide(10, 2));
    }

    //Question 4
    @Test
    void arrayEquals() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void same() {
        Calculator anotherCalculator = calculator;
        assertSame(calculator, anotherCalculator);
    }

    @Test
    void linesMatch() {
        String expected = "Line1\nLine2\nLine3";
        String actual = "Line1\nLine2\nLine3";
        assertLinesMatch(expected.lines().toList(), actual.lines().toList());
    }

    //Question 5
    @Test
    void timeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(50);
            calculator.add(1, 2);
        });
    }

    //Question 6
    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "4, 5, 9",
            "6, 7, 13"
    })
    void parameterizedAdd(int a, int b, int result) {
        assertEquals(result, calculator.add(a, b));
    }

    //Question 7
    @RepeatedTest(5)
    void generateRandomNumber() {
        int number = calculator.generateRandomNumber();
        assertTrue(number >= 0 && number < 100);
    }
}
