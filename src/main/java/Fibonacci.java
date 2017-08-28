import java.util.LinkedList;
import java.util.List;

public class Fibonacci {

    private final String DELIMETER = " ";

    List<Integer> computedNums;

    public Fibonacci() {
        computedNums = new LinkedList<>();
        computedNums.add(1);
        computedNums.add(1);
    }

    /**
     * Retrun fibonacci number
     * @param index is started from 1 (1, 2, 3 ... )
     * @return fibonacci number on given position (index)
     */
    public int getNumber(int index) {
        throwIndexOutOfBoundIfInvalidIndex(1, 46, index);

        if (isAlreadyComputed(index)){
            return computedNums.get(index - 1);
        }
        return computeFibonacciNumber(index);
    }

    private void throwIndexOutOfBoundIfInvalidIndex(int fromIndex, int toIndex, int fibonacciIndex) {
        if(fibonacciIndex < fromIndex || fibonacciIndex > toIndex)
            throw new IndexOutOfBoundsException("Allowed indexes = [" + fromIndex + ", " + toIndex + "] (inclusive)! " +
                    "Your Index: '" +fibonacciIndex +"'");
    }

    private boolean isAlreadyComputed(int fibonacciIndex) {
        return fibonacciIndex -1 < computedNums.size();
    }

    private int computeFibonacciNumber(int fibonacciIndex) {
        int computingNumber = 0;
        int a = computedNums.get(computedNums.size() - 2);
        int b = computedNums.get(computedNums.size() - 1);

        for (int i = computedNums.size(); i < fibonacciIndex; i++){
            computingNumber = a + b;
            a = b;
            b = computingNumber;
            computedNums.add(computingNumber);
        }

        return computingNumber;
    }

    /**
     * Average value is computed from first fibonacci number up to toIndex number (inclusive)
     * @param toIndex (inclusive) and is started from 1 (1, 2, 3, ..., toIndex)
     * @return average value of fibonacci numbers
     */
    public float averageValue(int toIndex) {
        throwIndexOutOfBoundIfInvalidIndex(1, 44, toIndex);

        float sum = 0;
        if (!isAlreadyComputed(toIndex)){
            getNumber(toIndex);
        }

        for (int i = 0; i < toIndex ; i++) {
            sum += computedNums.get(i);
        }

        return sum/toIndex;
    }

    /**
     * @param toIndex  (inclusive) and is started from 1 (1, 2, 3, ..., toIndex)
     * @return string of fibonacci number delimited by space
     */
    public String getNumbers(int toIndex) {
        getNumber(toIndex); //check index and compute numbers if necessary
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toIndex ; i++) {
            sb.append(computedNums.get(i)).append(DELIMETER);
        }
        removeLastSpace(sb);
        return sb.toString();
    }

    private void removeLastSpace(StringBuilder sb) {
        int i = sb.lastIndexOf(DELIMETER);
        sb.delete(i, i + 1);
    }
}
