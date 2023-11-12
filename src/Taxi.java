import java.util.ArrayList;
import java.util.List;

public class Taxi{

    boolean booked;
    char current_place;
    int free_time;
    int earnings;
    List<String> details;
    int id;
    static int totalTaxi=0;

    public Taxi(){

        totalTaxi = totalTaxi+1;
        id = totalTaxi;
        booked = false;
        current_place = 'A';
        free_time = 6;
        earnings = 0;
        details = new ArrayList<String>();
    }

    public void setDetails(boolean booked,char current_place,int free_time,int earnings,String trip_Details){

        this.booked = booked;
        this.current_place = current_place;
        this.free_time = free_time;
        this.earnings = earnings;
        this.details.add(trip_Details);

    }

    public void printDetails(){
        System.out.println("Taxi number: "+this.id+"       Total_Earnings: "+this.earnings);
        System.out.println("Taxi Id      BookingId   CustomerId   From   To   PickUp_Time   Drop_Time   Amount");
        for(String s:details){
            System.out.println(this.id+"         "+ s);
        }
        System.out.println("===========================================================================");
    }
}