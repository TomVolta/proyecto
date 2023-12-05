package purchase;

//import java.util.regex.*;

public class FixExpense extends Expense{

    private FixExpense(double amount, String concept, String date){
        this.amount = amount;
        this.concept = concept;
        this.date = date;
    }

    public static FixExpense newFixExpense(double amount, String concept, String date){
        if(Expense.datePattern.matcher(date).matches()){
            return new FixExpense(amount, concept, date);   
        }else{
            return null;
        }
    }

    @Override
    float getPercentage(){
        return (float) (amount*100/Expense.total);
    }
    
}
