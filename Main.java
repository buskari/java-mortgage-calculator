import java.util.Scanner;
import java.text.NumberFormat;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] Args) {
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Anual Interest Rate: ", 1, 30);
        byte years = (byte) readNumber("Periods (Years): ", 1, 30);

        printMortgage(principal, annualInterest, years);
        printPaymentSchedule(principal, annualInterest, years);
    }

    public static void printMortgage(int principal, float annualInterest, byte years) {
        double mortgage = calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("Mortgage".toUpperCase());
        System.out.println("------------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    public static void printPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println();
        System.out.println("Payment Schedule".toUpperCase());
        System.out.println("------------");
        for (short month = 1; month <= (years * MONTHS_IN_YEAR); month++) {
            double balance = calculateBalance(principal, annualInterest, years, month);
            String balanceFormatted = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(balanceFormatted);
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                return value;
            System.out.println("Enter a value between " + min + " and " + max);
        }
    }

    public static double calculateMortgage(int principal, float annualInterest, byte years) {
        double monthlyInterest = annualInterest / MONTHS_IN_YEAR / PERCENT;
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        double mortgage = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return mortgage;
    }

    public static double calculateBalance(int principal, float annualInterest, byte years, short numberOfPaymentsMade) {
        double monthlyInterest = annualInterest / MONTHS_IN_YEAR / PERCENT;
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments)
                        - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return balance;
    }
}