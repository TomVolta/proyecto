package purchase;

import java.util.LinkedList;

abstract class AbstractCategory{

    double total;
    LinkedList<FixExpense> FixExpenses;
    LinkedList<RecurrentExpense> RecurrentExpenses;

    public double getTotal(){
        return total;
    }

    public LinkedList<FixExpense> getFixExpenses(){
        return FixExpenses;
    }

    public LinkedList<RecurrentExpense> getRecurrentExpenses(){
        return RecurrentExpenses;
    }

}