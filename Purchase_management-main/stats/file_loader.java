package stats;

import java.util.*;
import java.io.*;

import users.Families;
import users.User;

public class file_loader implements Serializable {

    public static void saveData(Families family, User user){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./families/" + family.getFamily() + "/" + user.getName() + "/purchases/purchaseslist.dat"));
            output.writeObject(user.list);
            output.close();
        }
        catch(IOException e){
            System.err.println("Error saving to file");
        }
    }

    public static List readData(){
        List <User> in = new ArrayList<>(); 
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("nodelist.dat"));
            in = (List<User>) input.readObject(); 
            input.close();
        }
        catch(IOException e){
            System.err.println("Error opening file");
        }
        catch(ClassNotFoundException e2){
            System.err.println("Object read is not a node");
        }
        return in; 
    }
}
