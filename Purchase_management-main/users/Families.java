package users;

import java.util.*;
import java.io.*;

public class Families implements Serializable{
    
    private static Families familyInstance;
    public Set<User> collection;  
    private String family; 
    public Admin admin; 
    
    public Families(String family, Admin admin){
        this.collection = new LinkedHashSet<>(); 
        this.family = family; 
        this.admin = admin; 
    }

    public Families getInstance(){
        return familyInstance;
    }

    public static void setMenu(Families family){
        int op; 
        do{
            Scanner sc = new Scanner(System.in); 
            System.out.println("You've entered as a " + family.getFamily() + " family member, welcome!");
            System.out.println("[1] Login as a member");
            System.out.println("[2] Loggin as an administrator");
            System.out.println("[3] Register a new member");
            System.out.println("[4] See active members");
            System.out.println("[5] Log out");
            op = sc.nextInt(); 
            switch (op) {
                case 1:
                    Scanner sc2 = new Scanner(System.in); 
                    System.out.println("Type your name: ");
                    String name = sc2.nextLine();
                    for(User user : family.collection){
                        if(name.equals(user.getName())){
                            User.userMenu(family, user);
                        }
                    }
                    System.out.println("Not found");
                    break;
                case 2: 
                    Admin.adminMenu(family, family.admin);
                    break;
                case 3: 
                    User.addUser(family);
                    System.out.println("-------------------");
                    System.out.println("Member registered succesfully!");
                    break;
                case 4: 
                    for(User user : family.collection){
                        System.out.println(user.getName());
                    }
                    break;
                case 5: 
                    try{
                        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./families/" + family.getFamily() + "/family.dat")); 
                        output.writeObject(family);
                        output.close();
                    }
                    catch(IOException e){
                        System.err.println("Error saving to file");
                    }
                    System.out.println("Logged out succesfully!");
                    return; 
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while(true); 
    }

    public String getFamily(){
        return family; 
    }
    public void setFamily(String famliy){
        this.family = famliy; 
    }
}

