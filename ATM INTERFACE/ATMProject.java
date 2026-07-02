import java.util.Scanner;

/**
 * Represents a user's bank account with basic operations.
 */
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
}

/**
 * Represents the ATM machine with user interface and operations.
 */
class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the ATM session with a menu-driven interface.
     */
    public void start() {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
            handleChoice(choice);
        } while (choice != 4);

        System.out.println("\nThank you for using the ATM. Goodbye!");
    }

    private void displayMenu() {
        System.out.println("\n==============================");
        System.out.println("         ATM INTERFACE        ");
        System.out.println("==============================");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print(" Choose an option from (1–4): ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("⚠️ Invalid input. Please enter a number between 1–4.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> performWithdraw();
            case 2 -> performDeposit();
            case 3 -> System.out.println(" Current Balance: " + account.getBalance());
            case 4 -> {} // Exit
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void performWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getValidAmount();
        if (account.withdraw(amount)) {
            System.out.println("✅ Withdrawal successful. Remaining Balance: " + account.getBalance());
        } else {
            System.out.println(" Withdrawal failed. Ensure sufficient balance and valid amount.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = getValidAmount();
        if (account.deposit(amount)) {
            System.out.println(" Deposit successful. New Balance: " + account.getBalance());
        } else {
            System.out.println("Deposit failed. Amount must be positive.");
        }
    }

    private double getValidAmount() {
        while (!scanner.hasNextDouble()) {
            System.out.println(" Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}

/**
 * Main class to run the ATM project.
 */
public class ATMProject {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount userAccount = new BankAccount(1000.0);

        // Connect ATM with the user's bank account
        ATM atm = new ATM(userAccount);

        // Start the ATM interface
        atm.start();
    }
}
