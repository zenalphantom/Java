import java.util.HashMap;
import java.util.Scanner;

public class BankingApp {
    private static HashMap<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Banking Application =====");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performTransaction(true);
                    break;
                case 3:
                    performTransaction(false);
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using our banking services!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.println("\n--- Create New Account ---");
        
        String accountNumber;
        while (true) {
            System.out.print("Enter 10-digit account number: ");
            accountNumber = scanner.nextLine();
            if (accountNumber.matches("\\d{10}")) {
                break;
            }
            System.out.println("Invalid account number! Must be exactly 10 digits.");
        }

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
            return;
        }

        System.out.print("Enter account holder name: ");
        BankAccount newAccount = new BankAccount(accountNumber);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created successfully!");
        newAccount.display();
    }

    private static void performTransaction(boolean isDeposit) {
        String transactionType = isDeposit ? "Deposit" : "Withdraw";
        System.out.println("\n--- " + transactionType + " Money ---");
        
        System.out.print("Enter 10-digit account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found! Please create an account first.");
            return;
        }

        System.out.print("Enter amount to " + transactionType.toLowerCase() + ": ₹");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }

    private static void checkBalance() {
        System.out.println("\n--- Check Balance ---");
        
        System.out.print("Enter 10-digit account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found!");
        } else {
            account.display();
        }
    }
}
