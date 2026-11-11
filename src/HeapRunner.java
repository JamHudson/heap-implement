import java.util.Scanner;

/**
 * Interactive runner program for testing your Heap implementation.
 * 
 * Commands:
 *   - Add a value
 *   - Pop the front value
 *   - Peek at the front value
 *   - Check the size
 *   - Check whether the heap is empty
 *   - Quit
 */
public class HeapRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Construct the heap.
        Heap heap = new Heap();

        System.out.println("=== Heap Tester ===");

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("a") || choice.equals("add")) {
                System.out.print("Enter an integer to add: ");
                String input = scanner.nextLine().trim();
                int value;
                try {
                    value = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("That wasn't a valid integer. Try again.");
                    continue;
                }
 
                // Add value to heap.
                heap.add(value);
                System.out.println("Added "+(value)+" to heap.");
 
            } else if (choice.equals("p") || choice.equals("pop")) {
                try {
                    int result = heap.pop();
                    System.out.println("Popped: "+result);
                } catch (IllegalStateException error) {
                    System.out.println("Heap is empty, cannot pop.");
                }

            } else if (choice.equals("k") || choice.equals("peek")) {
                try {
                    int result = heap.peek();
                    System.out.println("Peeked: "+result);
                } catch (IllegalStateException error) {
                    System.out.println("Heap is empty, cannot peek.");
                }

            } else if (choice.equals("s") || choice.equals("size")) {
                int size = heap.size();
                System.out.println("Size: "+size);

            } else if (choice.equals("e") || choice.equals("empty")) {
                boolean isEmpty = heap.isEmpty();
                System.out.println("Is Empty: "+isEmpty);
            } else if (choice.equals("q") || choice.equals("quit")) {
                break;

            } else {
                System.out.println("Unknown command. Please try again.");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    /**
     * Prints the menu of available commands.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("---- Commands ----");
        System.out.println("  a / add   - add a value to the heap");
        System.out.println("  p / pop   - pop the front value off the heap");
        System.out.println("  k / peek  - peek at the front value of the heap");
        System.out.println("  s / size  - print the size of the heap");
        System.out.println("  e / empty - check whether the heap is empty");
        System.out.println("  q / quit  - exit the program");
        System.out.print("Enter command: ");
    }
}