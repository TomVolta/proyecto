package purchase;

public interface CategoryFactory {

    void addFixExpense(double amount, String concept, String date);
    void addRecurrentExpense(double amount, String concept, String date, int period);
    
}
