import java.util.List;

public class OddEvenNumberSelector implements Runnable{
    private final List<Integer> numbers;
    private final List<Integer> evenNumbers;
    private final List<Integer> oddNumbers;

    public OddEvenNumberSelector(List<Integer> numbers, List<Integer> evenNumbers, List<Integer> oddNumbers) {
        this.numbers = numbers;
        this.evenNumbers = evenNumbers;
        this.oddNumbers = oddNumbers;
    }

    @Override
    public void run() {
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                synchronized (evenNumbers) {
                    evenNumbers.add(number);
                }
            } else {
                synchronized (oddNumbers) {
                    oddNumbers.add(number);
                }
            }
        }
    }
}
