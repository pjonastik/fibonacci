import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FibonacciTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    Fibonacci fibonacci;

    @Before
    public void setUp() throws Exception {
        fibonacci = new Fibonacci();
    }

    /* getNumber */

    @Test
    public void getNumberShouldFailOnNegativeIndexes() throws Exception {
        getNumberWithExpectedExceptionAndMessageForIndex(-1);
    }

    private void getNumberWithExpectedExceptionAndMessageForIndex(int fibonacciIndex) {
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("Allowed indexes = [1, 46] (inclusive)! " +
                "Your Index: '" +fibonacciIndex +"'");

        fibonacci.getNumber(fibonacciIndex);
    }

    @Test
    public void getNumberShouldFailOnZeroIndex() throws Exception {
        getNumberWithExpectedExceptionAndMessageForIndex(0);
    }

    @Test
    public void getNumberShouldFailOnIndexesGreaterThan46() throws Exception {
        getNumberWithExpectedExceptionAndMessageForIndex(47);
    }

    @Test
    public void getNumber_ShouldReturnCorrectValues() throws Exception {
        assertThat(fibonacci.getNumber(1), is(1));
        assertThat(fibonacci.getNumber(2), is(1));
        assertThat(fibonacci.getNumber(3), is(2));
        assertThat(fibonacci.getNumber(4), is(3));
        assertThat(fibonacci.getNumber(20), is(6765));
    }

    @Test
    public void getNumberShouldBeConsistent() throws Exception {
        assertThatGetNumberIsConsistent(3, 1, 1);
        assertThatGetNumberIsConsistent(3, 2, 1);
        assertThatGetNumberIsConsistent(3, 6, 8);
        assertThatGetNumberIsConsistent(3, 20, 6765);

        assertThatGetNumberFailsConsistently(3, -1);
        assertThatGetNumberFailsConsistently(3, 0);
        assertThatGetNumberFailsConsistently(3, 47);
    }

    private void assertThatGetNumberIsConsistent(int testCount, int fibonacciIndex, int expectedValue) {
        for (int i = 0; i < testCount; i++) {
            assertThat(fibonacci.getNumber(fibonacciIndex), is(expectedValue));
        }
    }

    private void assertThatGetNumberFailsConsistently(int testCount, int fibonacciIndex) {
        for (int i = 0; i < testCount; i++) {
            try {
                fibonacci.getNumber(fibonacciIndex);
                fail();
            } catch (Exception e){}
        }
    }

    /* getNumbers */

    @Test
    public void getNumbersShouldFailOnNegativeIndexes() throws Exception {
        getNumbersWithExpectedExceptionAndMessageForIndex(-1);
    }

    private void getNumbersWithExpectedExceptionAndMessageForIndex(int fibonacciIndex) {
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("Allowed indexes = [1, 46] (inclusive)! " +
                "Your Index: '" +fibonacciIndex +"'");

        fibonacci.getNumbers(fibonacciIndex);
    }

    @Test
    public void getNumbersShouldFailOnZeroIndex() throws Exception {
        getNumbersWithExpectedExceptionAndMessageForIndex(0);
    }

    @Test
    public void getNumbersShouldFailOnIndexesGreaterThan46() throws Exception {
        getNumbersWithExpectedExceptionAndMessageForIndex(47);
    }

    @Test
    public void getNumbersShouldReturnCorrectValues() throws Exception {
        String nums = fibonacci.getNumbers(1);
        assertThat(nums, is("1"));

        nums = fibonacci.getNumbers(2);
        assertThat(nums, is("1 1"));

        nums = fibonacci.getNumbers(3);
        assertThat(nums, is("1 1 2"));

        nums = fibonacci.getNumbers(20);
        assertThat(nums, is("1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765"));
    }

    @Test
    public void getNumbersShouldBeConsistent() throws Exception {
        assertThatGetNumbersIsConsisent(3, 1, "1");
        assertThatGetNumbersIsConsisent(3, 2, "1 1");
        assertThatGetNumbersIsConsisent(3, 6, "1 1 2 3 5 8");

        assertThatGetNumbersFailsConsistently(3, -1);
        assertThatGetNumbersFailsConsistently(3, 0);
        assertThatGetNumbersFailsConsistently(3, 47);
    }

    private void assertThatGetNumbersIsConsisent(int testCount, int fibonacciIndex, String expectedValue) {
        for (int i = 0; i < testCount; i++) {
            assertThat(fibonacci.getNumbers(fibonacciIndex), is(expectedValue));
        }
    }

    private void assertThatGetNumbersFailsConsistently(int testCount, int fibonacciIndex) {
        for (int i = 0; i < testCount; i++) {
            try {
                fibonacci.getNumbers(fibonacciIndex);
                fail();
            } catch (Exception e){}
        }
    }

    /* averageValue*/

    @Test
    public void averageValueShouldFailOnNegativeIndexes() throws Exception {
        averageValueWithExpectedExceptionAndMessageForIndex(-1);
    }

    private void averageValueWithExpectedExceptionAndMessageForIndex(int fibonacciIndex) {
        expectedException.expect(IndexOutOfBoundsException.class);
        expectedException.expectMessage("Allowed indexes = [1, 44] (inclusive)! " +
                "Your Index: '" +fibonacciIndex +"'");

        fibonacci.averageValue(fibonacciIndex);
    }

    @Test
    public void averageValueShouldFailOnZeroIndex() throws Exception {
        averageValueWithExpectedExceptionAndMessageForIndex(0);
    }

    @Test
    public void averageValueShouldFailOnIndexGreaterThan44() throws Exception {
        averageValueWithExpectedExceptionAndMessageForIndex(45);
    }

    @Test
    public void averageValueShouldReturnCorrectValues() throws Exception {
        assertThat(fibonacci.averageValue(1), is(1f));
        assertThat(fibonacci.averageValue(2), is(1f));
        assertThat(fibonacci.averageValue(3), is((4f/3f)));
        assertThat(fibonacci.averageValue(5), is(2.4f));
        assertThat(fibonacci.averageValue(20), is(885.5f));
    }

    @Ignore
    @Test
    public void findMaxFibonacciIndexValue() throws Exception {
        int lastComputed = 0;
        int i = 20;
        for (; i < Integer.MAX_VALUE; i++ ){
            lastComputed = fibonacci.getNumber(i);
            if ( lastComputed < 0 ) {
                break;
            }
        }
        System.out.println("Error index is: " + i);                         //Error index is: 47
        System.out.println("Last computed value: " + lastComputed);     //Last computed value: -1323752223
    }

    @Ignore
    @Test
    public void findMaxSumOfFibonacciNumbers() throws Exception {
        int i = 1;
        int lastComputed = 0;
        for (; i < Integer.MAX_VALUE; i++ ) {
            lastComputed += fibonacci.getNumber(i);
            if ( lastComputed < 0 ) {
                break;
            }
        }
        System.out.println("Error index is: " + i);                         //Error index is: 45
        System.out.println("Last computed value: " + lastComputed);     //Last computed value: -1323752224
    }
}