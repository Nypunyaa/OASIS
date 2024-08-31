package ATMInterface;
import java.util.Scanner;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 1000f;
    int transactions = 0;
    String transactionHistory = "";
    Scanner sc = new Scanner(System.in); 

    public void register() {
        System.out.println("\nEnter your Name: ");
        this.name = sc.nextLine();
        System.out.println("\nEnter your Username: ");
        this.userName = sc.nextLine();
        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration Successful. Please Log in to your Bank Account");
    }

    public boolean login() {
        boolean isLogin = false;
        while (!isLogin) {
            System.out.println("\nEnter your username: ");
            String inputUsername = sc.nextLine();
            if (inputUsername.equals(userName)) {
                while (!isLogin) {
                    System.out.println("\nEnter your password: ");
                    String inputPassword = sc.nextLine();
                    if (inputPassword.equals(password)) {
                        System.out.println("\nLogin Successful");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect Password. Please try again.");
                    }
                }
            } else {
                System.out.println("\nUsername not found. Please try again.");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.println("\nEnter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        sc.nextLine();
        if (balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("\nWithdrawal Successful.");
            String str = amount + " Rs Withdrawn\n";
            transactionHistory = transactionHistory.concat(str);
        } else {
            System.out.println("\nInsufficient Balance.");
        }
    }

    public void deposit() {
        System.out.println("\nEnter Amount to Deposit: ");
        float amount = sc.nextFloat();
        sc.nextLine();
        if (amount <= 1000f) {
            transactions++;
            balance += amount;
            System.out.println("\nDeposit Successful.");
            String str = amount + " Rs Deposited\n";
            transactionHistory = transactionHistory.concat(str);
        } else {
            System.out.println("\nSorry. The deposit limit is 10000.");
        }
    }

    public void transfer() {
        System.out.println("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.println("\nEnter Amount to Transfer: ");
        float amount = sc.nextFloat();
        sc.nextLine();
        if (balance >= amount) {
            if (amount <= 50000f) {
                transactions++;
                balance -= amount;
                System.out.println("\nSuccessfully Transferred to " + recipient);
                String str = amount + " Rs Transferred to " + recipient + "\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nSorry. The transfer limit is 50000.");
            }
        } else {
            System.out.println("\nInsufficient Balance.");
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("\nNo Transactions have occurred.");
        } else {
            System.out.print("\nTransaction History:\n" + transactionHistory);
        }
    }

    public void closeScanner() {
        sc.close();
    }
}

public class ATMInterface {

    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if (flag && (input > limit || input < 1)) {
                    System.out.println("Choose a number between 1 and " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer values.");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n*WELCOME TO ATM INTERFACE*");
        System.out.println("\n1. Register \n2. Exit");
        System.out.println("Choose one option: ");
        int choose = takeIntegerInput(2);

        if (choose == 1) {
            BankAccount b = new BankAccount();
            b.register();
            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.println("Enter your choice: ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("\n*WELCOME BACK, " + b.name + "*");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            System.out.println("Enter your choice: ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        }
                        b.closeScanner(); 
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}
