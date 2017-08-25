
public class Main {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int index = 20;
        System.out.println("Fibonacci numbers: " + fibonacci.getNumbers(index));
        System.out.println("Average of numbers: " + fibonacci.averageValue(index));
    }
}
