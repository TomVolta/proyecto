package purchase;

public class RecurrentExpense extends Expense{

    private int period;

    private RecurrentExpense(double amount, String concept, String date, int period){
        this.amount = amount;
        this.concept = concept;
        this.date = date;
        this.period = period;
    }

    public static RecurrentExpense newRecurrentExpense(double amount, String concept, String date, int period){
        if(Expense.datePattern.matcher(date).matches()){
            return new RecurrentExpense(amount, concept, date, period);   
        }else{
            return null;
        }
    }

    @Override
    float getPercentage() {
        return (float)(amount*(30/period)*100/Expense.total);
    }
        
}