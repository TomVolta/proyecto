package users;

import java.io.*;
import java.util.*;
import stats.*;
import purchase.*;
import users.*;

public class User implements Serializable{
    private String name, pswd;
    private double budget;  
    public List <User> list; 
    private Fun funCategory;
    private Food foodCategory;
    private Services servicesCategory;
    private Reminder reminder;
    private Statistics statistics;

    public User(String name, String pswd, double budget){
        this.name = name; 
        this.pswd = pswd; 
        this.budget = budget; 
        this.list = new ArrayList<>(); 
        funCategory = new Fun();
        foodCategory = new Food();
        servicesCategory = new Services();
        this.reminder = new Reminder(name);
        this.statistics = new Statistics(this);
    }
    
    public static void addUser(Families family){
        Scanner sc = new Scanner(System.in); 
        String name, pswd;
        System.out.println("-------------------");
        System.out.println("Type the name of the new user: ");
        name = sc.nextLine(); 
        //String s = sc.nextLine(); 
        System.out.println("Type the password: ");
        pswd = sc.nextLine(); 
        User user = new User(name, pswd, 0.08f); 
        family.collection.add(user); 
        File f = new File("./families/" + family.getFamily() + "/" + name); 
        if(f.mkdir() != true){
            System.out.println("Directory cannot be created because it already exists");
        }
        File reminders = new File("./families/" + family.getFamily() + "/" + name + "/reminders"); 
        if(reminders.mkdir() != true){
            System.out.println("Directory cannot be created because it already exists");
        }
        File purchases = new File("./families/" + family.getFamily() + "/" + name + "/purchases"); 
        if(purchases.mkdir() != true){
            System.out.println("Directory cannot be created because it already exists");
        }
    }

     public static void userMenu(Families family, User user){
        System.out.println("Welcome " + user.getName() + ", what you wanna do today?");
        do{
            Scanner sc = new Scanner(System.in); 
            int op;

            user.getReminder().loadRemindersFromFile();  // Cargar recordatorios al entrar en userMenu.
            user.getReminder().showReminders();
            
            System.out.println("- CURRENT BUDGET: " + user.getBudget() + "$ (USD)");
            System.out.println("[1] Add purchases");
            System.out.println("[2] View statistics");
            System.out.println("[3] View lasts purchases");
            System.out.println("[4] Add reminder");
            System.out.println("[5] Delete reminder");
            System.out.println("[6] Save data from purchases");
            System.out.println("[7] Exit");
            op = sc.nextInt(); 
            switch (op) {
                case 1:
                    user.addPurchase(sc);
                    break;
                case 2: 
                    user.showStatistics();
                    break;
                case 3: 
                    // last purchases
                    break;
                case 4:
                user.getReminder().RMenu(user);
                    break;
                case 5:
                user.getReminder().showReminders(); // Mostrar recordatorios antes de eliminar
                System.out.println("Enter the index of the reminder to delete: ");
                int reminderIndex = sc.nextInt();
                user.deleteReminder(reminderIndex);
                break;

                case 6: 
                    file_loader.saveData(family, user);
                    break; 
                case 7: 
                    return; 
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while(true); 
    }

    protected void addPurchase(Scanner sc){
        System.out.println("Enter category");
        System.out.println("[1] Fun");
        System.out.println("[2] Food");
        System.out.println("[3] Services");
        String concept, date;
        double amount;
        int period;
        try{
            int op = sc.nextInt(), op2;
            switch(op){
                case 1:
                    System.out.println("[1] Normal expense");
                    System.out.println("[2] Periodic expense");
                    op2 = sc.nextInt();
                    switch (op2){
                        case 1:
                            System.out.println("Enter amount");
                            amount = sc.nextDouble();
                            System.out.println("Enter concept");
                            sc.nextLine();
                            concept = sc.nextLine();
                            System.out.println("Enter date");
                            date = sc.nextLine();
                            
                            this.funCategory.addFixExpense(amount, concept, date);
                            break;
                        case 2:
                            System.out.println("Enter amount");
                            amount = sc.nextDouble();
                            System.out.println("Enter concept");
                            sc.nextLine();
                            concept = sc.nextLine();
                            System.out.println("Enter date");
                            date = sc.nextLine();
                            System.out.println("Enter peridicity in days");
                            period = sc.nextInt();
                            if(Expense.datePattern.matcher(date).matches()){
                                this.funCategory.addRecurrentExpense(amount, concept, date, period);   
                            }else{
                                System.out.println("Invalid date");
                            }
                            
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    System.out.println("[1] Normal expense");
                    System.out.println("[2] Periodic expense");
                    op2 = sc.nextInt();
                    switch (op2){
                        case 1:
                            System.out.println("Enter amount");
                            amount = sc.nextDouble();
                            System.out.println("Enter concept");
                            sc.nextLine();
                            concept = sc.nextLine();
                            System.out.println("Enter date");
                            date = sc.nextLine();
                            
                            this.foodCategory.addFixExpense(amount, concept, date);
                            break;
                        case 2:
                            System.out.println("Enter amount");
                            amount = sc.nextDouble();
                            System.out.println("Enter concept");
                            sc.nextLine();
                            concept = sc.nextLine();
                            System.out.println("Enter date");
                            date = sc.nextLine();
                            System.out.println("Enter peridicity in days");
                            period = sc.nextInt();
                            if(Expense.datePattern.matcher(date).matches()){
                                this.foodCategory.addRecurrentExpense(amount, concept, date, period);   
                            }else{
                                System.out.println("Invalid date");
                            }
                            
                            break;
                        default:
                            break;
                    }

                    break;
                case 3:
                    System.out.println("[1] Normal expense");
                    System.out.println("[2] Periodic expense");
                    op2 = sc.nextInt();
                    switch (op2){
                        case 1:
                            System.out.println("Enter amount");
                            amount = sc.nextDouble();
                            System.out.println("Enter concept");
                            sc.nextLine();
                            concept = sc.nextLine();
                            System.out.println("Enter date");
                            date = sc.nextLine();
                            
                            this.servicesCategory.addFixExpense(amount, concept, date);
                            break;
                        case 2:
                            System.out.println("Enter amount");
                            amount = sc.nextDouble();
                            System.out.println("Enter concept");
                            sc.nextLine();
                            concept = sc.nextLine();
                            System.out.println("Enter date");
                            date = sc.nextLine();
                            System.out.println("Enter peridicity in days");
                            period = sc.nextInt();
                            if(Expense.datePattern.matcher(date).matches()){
                                this.servicesCategory.addRecurrentExpense(amount, concept, date, period);   
                            }else{
                                System.out.println("Invalid date");
                            }
                            
                            break;
                        default:
                            break;
                    }

                    
                    break;
            
                default:
                    break;
            }
        }catch(InputMismatchException e){
            System.out.println("Invalid input");
            return;
        }

    }

    public void showStatistics() {
        Statistics stats = getStatistics();
        stats.calculateStatistics(); // Make sure to calculate statistics before displaying them
    
        System.out.println("Category with highest spending: " + stats.getMaxSpendingCategory());
        System.out.println("Category with lowest spending: " + stats.getMinSpendingCategory());
        System.out.println("Average daily spending: " + stats.getAverageDailySpending());
    }    

    public void deleteReminder(int index) {
        this.reminder.removeReminder(index);
    }

    public void setName(String name){
        this.name = name; 
    }
     public void setpswd(String pswd){
        this.pswd = pswd; 
    } 
    public void setBudget(double budget){
        this.budget = budget; 
    }
    public String getName(){
        return name; 
    }
    public double getBudget(){
        return budget; 
    }

    public Reminder getReminder() {
        return reminder;
    }

    public Food getFoodCategory() {
        return foodCategory;
    }

    public Fun getFunCategory() {
        return funCategory;
    }

    public Services getServicesCategory() {
        return servicesCategory;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
