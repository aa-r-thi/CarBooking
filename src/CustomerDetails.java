public class CustomerDetails {
    static int id = 1;
    int customerId;
    String name;
    char pickUp_point;
    char drop_point;
    int pickUp_time;

    public void SetCustomerDetails(String name,char pickUp_point,char drop_point,int pickUp_time){
        this.customerId = id++;
        this.name = name;
        this.pickUp_point = pickUp_point;
        this.drop_point = drop_point;
        this.pickUp_time = pickUp_time;
    }

}
