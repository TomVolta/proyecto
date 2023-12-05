package purchase;

import java.util.regex.*;

public abstract class  Expense {
    
    public static double total = 0;
    public static Pattern datePattern = Pattern.compile("[0-3]\\d/[0-1]\\d/\\d{4}");
    protected double amount;
    protected String concept;
    protected String date;

    abstract float getPercentage();

    public double getAmount(){
        return amount;
    }
    public String getConcept(){
        return concept;
    }
    public String getDate(){
        return date;
    }

}
