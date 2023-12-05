package users;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Reminder {

    private List<String> recurrentExpenses;
    private String userFileName;
    private String FILE_PATH;

    public Reminder(String userFileName) {
        this.userFileName = userFileName;
        this.recurrentExpenses = new ArrayList<>();
        this.FILE_PATH = "./families/" + userFileName + "/reminders.txt";
    }

    public void loadRemindersFromFile() {
        String filePath = "./families/" + userFileName + "/reminders.txt";
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                recurrentExpenses.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No reminders found.");
        }
    }

    public void remindUser() {
        LocalDate today = LocalDate.now();
    
        for (String reminder : recurrentExpenses) {
            String[] parts = reminder.split(",");
            LocalDate paymentDate = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
            // Remind the user one day before the payment
            if (paymentDate.minus(1, ChronoUnit.DAYS).isEqual(today)) {
                System.out.println("Reminder: Next payment for " + parts[1] +
                        " of " + parts[2] + " scheduled for tomorrow.");
            }
        }
    }
    
    public void RMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the date of the next payment (format yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        LocalDate paymentDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Enter the reason for the payment: ");
        String expenseName = scanner.nextLine();

        System.out.println("Enter the amount of the payment: ");
        double amount = scanner.nextDouble();

        String newExpense = paymentDate + "," + expenseName + "," + amount;
        addExpense(newExpense);
        saveRemindersToFile();
        System.out.println("Recurrent expense added successfully");
    }

    public void RMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the date of the next payment (format yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        LocalDate paymentDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Enter the reason for the payment: ");
        String expenseName = scanner.nextLine();

        System.out.println("Enter the amount of the payment: ");
        double amount = scanner.nextDouble();

        String newExpense = paymentDate + "," + expenseName + "," + amount;
        addExpense(newExpense);
        saveRemindersToFile();
        System.out.println("Recurrent expense added successfully");
    }

    public void showReminders() {
        if (recurrentExpenses.isEmpty()) {
            System.out.println("No reminders found.");
        } else {
            System.out.println("Reminders:");
            for (String reminder : recurrentExpenses) {
                System.out.println(reminder);
            }
        }
    }

    public void removeReminder(int index) {
        if (index >= 0 && index < recurrentExpenses.size()) {
            recurrentExpenses.remove(index);
            saveRemindersToFile();
            System.out.println("Reminder deleted successfully.");
        } else {
            System.out.println("Invalid reminder index.");
        }
    }

    private void addExpense(String reminder) {
        recurrentExpenses.add(reminder);
    }

    private void saveRemindersToFile() {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (String reminder : recurrentExpenses) {
                writer.println(reminder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
