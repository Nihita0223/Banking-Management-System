import java.util.Scanner;

class BankAccount {
    private String accountHolderName;
    private double balance;
    private String accountNumber;

    // Constructor to initialize a bank account
    public BankAccount(String accountHolderName, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = 0.0; // Initial balance is 0
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // Method to check the balance
    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }

    // Getters for account holder details
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        while (true) {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Create new account
                    scanner.nextLine();  // Consume newline left by nextInt
                    System.out.print("Enter Account Holder Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    account = new BankAccount(name, accountNumber);
                    System.out.println("Account created successfully.");
                    break;

                case 2:
                    // Deposit money
                    if (account == null) {
                        System.out.println("No account found. Please create an account first.");
                    } else {
                        System.out.print("Enter amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    }
                    break;

                case 3:
                    // Withdraw money
                    if (account == null) {
                        System.out.println("No account found. Please create an account first.");
                    } else {
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                    }
                    break;

                case 4:
                    // Check balance
                    if (account == null) {
                        System.out.println("No account found. Please create an account first.");
                    } else {
                        account.checkBalance();
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting the system. Thank you for using our services.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
