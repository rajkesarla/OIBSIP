import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ATMinterface {
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, Integer> userBalances = new HashMap<>();
    private static Map<String, List<String>> userTransactionHistory = new HashMap<>();

    public static void main(String[] args) {
        initializeUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Multi-User ATM");

        while (true) {
            System.out.print("Enter user id: ");
            String userId = scanner.nextLine();

            System.out.print("Enter user pin: ");
            String userPin = scanner.nextLine();

            if (authenticateUser(userId, userPin)) {
                System.out.println("Login successful for user: " + userId);

                // ATM functionality menu
                while (true) {
                    printMenu();
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            showTransactionsHistory(userId);
                            break;
                        case 2:
                            System.out.print("Enter withdrawal amount: ");
                            int withdrawalAmount = scanner.nextInt();
                            withdraw(userId, withdrawalAmount);
                            break;
                        case 3:
                            System.out.print("Enter deposit amount: ");
                            int depositAmount = scanner.nextInt();
                            deposit(userId, depositAmount);
                            break;
                        case 4:
                            System.out.print("Enter transfer amount: ");
                            int transferAmount = scanner.nextInt();
                            System.out.print("Enter recipient's user id: ");
                            String recipientUserId = scanner.next();
                            transfer(userId, recipientUserId, transferAmount);
                            break;
                        case 5:
                            System.out.println("Thank you for using the ATM. Goodbye!");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid user id or pin. Login failed.");
            }
        }
    }

    private static void initializeUsers() {
        // Define user credentials and initial balances
        userCredentials.put("raj", "1234");
        userCredentials.put("vardhan", "5678");
        userCredentials.put("ashok", "9876");

        userBalances.put("raj", 1000);
        userBalances.put("vardhan", 1500);
        userBalances.put("ashok", 2000);

        // Initialize transaction history for each user
        userTransactionHistory.put("raj", new ArrayList<>());
        userTransactionHistory.put("vardhan", new ArrayList<>());
        userTransactionHistory.put("ashok", new ArrayList<>());
    }

    private static boolean authenticateUser(String userId, String userPin) {
        String storedPin = userCredentials.get(userId);
        return storedPin != null && storedPin.equals(userPin);
    }

    private static void printMenu() {
        System.out.println("\nATM Functionality Menu");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    private static void showTransactionsHistory(String userId) {
        System.out.println("Showing transaction history for user: " + userId);
        List<String> history = userTransactionHistory.get(userId);
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : history) {
                System.out.println(transaction);
            }
        }
    }

    private static void withdraw(String userId, int amount) {
        int balance = userBalances.get(userId);
        if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            balance -= amount;
            userBalances.put(userId, balance);

            String transaction = "Withdrawal of " + amount + " successful.";
            userTransactionHistory.get(userId).add(transaction);

            System.out.println(transaction);
        }
    }

    private static void deposit(String userId, int amount) {
        int balance = userBalances.get(userId);
        balance += amount;
        userBalances.put(userId, balance);

        String transaction = "Deposit of " + amount + " successful.";
        userTransactionHistory.get(userId).add(transaction);

        System.out.println(transaction);
    }

    private static void transfer(String senderUserId, String recipientUserId, int amount) {
        int senderBalance = userBalances.get(senderUserId);
        if (amount > senderBalance) {
            System.out.println("Insufficient balance for the transfer. Transfer failed.");
        } else {
            int recipientBalance = userBalances.get(recipientUserId);

            senderBalance -= amount;
            recipientBalance += amount;

            userBalances.put(senderUserId, senderBalance);
            userBalances.put(recipientUserId, recipientBalance);

            String transaction = "Transfer of " + amount + " to " + recipientUserId + " successful.";
            userTransactionHistory.get(senderUserId).add(transaction);
            userTransactionHistory.get(recipientUserId).add("Received " + amount + " from " + senderUserId);

            System.out.println(transaction);
        }
    }
}
