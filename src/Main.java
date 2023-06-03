import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        /*
            PatikaDev Java102 - Race Of Threads
            Example of a program that divides numbers from 1 to 10000 into arrays of odd and even numbers with 4 separate threads.
            Create an ArrayList of numbers from 1 to 10000 (10 thousand). Then, divide the 10 thousand elements in this ArrayList into 4 equal parts of 2500 elements. Design 4 separate Threads that find odd and even numbers in these 4 separate 2500 element ArrayLists.
            4 Threads will add the even numbers to a common ArrayList.
            4 Threads will add the odd numbers to a common ArrayList.
            The ArrayLists holding the odd and even numbers will be empty when first created and will be two ArrayLists.
            4 Thread will fill the odd and even number ArrayLists when it starts processing its own 2500 element ArrayList.
            https://academy.patika.dev/referral/rfcnr
        */

        int startingNumber = 1, endingNumber = 10000;
        List<Integer> numbers = new ArrayList<>();
        for (int i = startingNumber; i <= endingNumber; i++) {
            numbers.add(i);
        }

        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();

        int threadCount = 4;
        int sublistSize = numbers.size() / threadCount;
        List<Runnable> selectors = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            List<Integer> sublist = numbers.subList(i * sublistSize, (i + 1) * sublistSize);
            Runnable selector = new OddEvenNumberSelector(sublist, evenNumbers, oddNumbers);
            selectors.add(selector);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (Runnable selector : selectors) {
            executorService.execute(selector);
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            // Wait for the process to complete!!!
        }

        System.out.println("\n\nEven Numbers: " + evenNumbers);
        System.out.println("\n\nOdd Numbers: " + oddNumbers);
    }
}