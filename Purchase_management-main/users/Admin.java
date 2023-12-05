package users;

//import java.io.*;
import java.util.*;

public class Admin extends User{
    public static int n; 
    private Reminder reminder;

    public Admin(String name, String pswd, double budget){
        super(name, pswd, budget);
        this.reminder = new Reminder(name);
    }

    public static void adminMenu(Families family, Admin admin){
        System.out.println("Welcome " + admin.getName() + ", what you wanna do today?");
        Scanner sc = new Scanner(System.in); 
        do{

            admin.getReminder().loadRemindersFromFile();  // Cargar recordatorios al entrar en userMenu.
            admin.getReminder().showReminders();

            int op; 
            System.out.println("[1] Add purchases");
            System.out.println("[2] Modify budget");
            System.out.println("[3] View statistics");
            System.out.println("[4] Add reminder");
            System.out.println("[5] Delete reminder");
            System.out.println("[6] Save data from family members");
            System.out.println("[7] Delete family member from family set");
            System.out.println("[8] Exit");
            op = sc.nextInt(); 
            switch (op) {

                case 1:
                    admin.addPurchase(sc);
                    break;
                case 2:
                    Scanner sc2 = new Scanner(System.in);
                    String name; 
                    boolean found = false; 
                    System.out.println("Type the family member: ");
                    name = sc2.nextLine(); 
                    for(User user : family.collection){
                        if(name.equals(user.getName())){
                            modifyBudget(user);
                            found = true;  
                        }
                    }
                    if(!found){
                        System.out.println("The desired user is not registered");
                    }else{
                        System.out.println("Budget succesfully modified");
                    }
                    break;
                case 3: 
                    // STATISTICS
                    break;
                case 4:
                    admin.getReminder().RMenu(admin);
                break;
                case 5:
                    admin.getReminder().showReminders(); // Mostrar recordatorios antes de eliminar
                    System.out.println("Enter the index of the reminder to delete: ");
                    int reminderIndex = sc.nextInt();
                    admin.deleteReminder(reminderIndex);
                break;
                case 6: 
                    // file_loader.saveData(family);
                    break;
                case 7: 
                    Scanner sc3 = new Scanner(System.in); 
                    String name3; 
                    boolean found3 = false; 
                    System.out.println("Type the family member: ");
                    name3 = sc3.nextLine(); 
                    for(User user : family.collection){
                        if(name3.equals(user.getName())){
                            deleteNode(user, family.collection);
                            found3 = true;  
                        }
                    }
                    if(!found3){
                        System.out.println("The desired user is not registered");
                    }else{
                        System.out.println("Member succesfully deleted");
                    }
                case 8: 
                    return; 
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while(true); 
    }

    public static void modifyBudget(User user){
        Scanner sc = new Scanner(System.in); 
        double b; 
        System.out.println("Current budget: " + user.getBudget());
        System.out.println("New budget: ");
        b = sc.nextDouble();
        user.setBudget(b);
        
    }

    public void deleteReminder(int index) {
        this.reminder.removeReminder(index);
    }

    public Reminder getReminder() {
        return reminder;
    }

    public static void deleteNode(User user, Set<User> set){
        set.remove(user);
    }


}
