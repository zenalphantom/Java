import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.printf("₹%.2f deposited. New balance: ₹%.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            System.out.printf("₹%.2f withdrawn. New balance: ₹%.2f%n", amount, balance);
        }
    }

    public void checkBalance() {
        System.out.printf("Account %s balance: ₹%.2f%n", accountNumber, balance);
    }

    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}

public class SimpleBankApp {
    private static HashMap<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    processTransaction(true);
                    break;
                case 2:
                    processTransaction(false);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for banking with us!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void processTransaction(boolean isDeposit) {
        System.out.print("Enter 10-digit account number: ");
        String accNumber = scanner.nextLine();
        
        // Validate 10 digits
        if (!accNumber.matches("\\d{10}")) {
            System.out.println("Invalid account number! Must be 10 digits.");
            return;
        }
        
        // Get or create account
        BankAccount account = accounts.computeIfAbsent(accNumber, _ -> {
            System.out.println("New account created automatically!");
            return new BankAccount(accNumber);
        });
        
        // Process transaction
        System.out.printf("Enter amount to %s: ₹", isDeposit ? "deposit" : "withdraw");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }

    private static void checkBalance() {
        System.out.print("Enter 10-digit account number: ");
        String accNumber = scanner.nextLine();
        
        BankAccount account = accounts.get(accNumber);
        if (account == null) {
            System.out.println("Account not found! Deposit first to create account.");
        } else {
            account.checkBalance();
        }
    }
}