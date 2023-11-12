import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main{
    static CustomerDetails cd = new CustomerDetails();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        List<Taxi> taxis = createTaxis(4);
        boolean enter = true;

        while(enter){
            System.out.print("1. Booking \n2. Print details \n3. Exit \n");
            int choice = scan.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter name: ");
                    String name = scan.next();
                    System.out.println("Enter pickUp Point: ");
                    char pickUp_point = scan.next().charAt(0);
                    System.out.println("Enter drop Point: ");
                    char drop_point = scan.next().charAt(0);
                    System.out.println("Enter pickUp_Time: ");
                    int pickUp_Time = scan.nextInt();

                    if(pickUp_point < 'A' || pickUp_point > 'F' || drop_point < 'A' || drop_point > 'F'){
                        System.out.println("Service not provide to this place now....");
                        return;
                    }

                    if(pickUp_Time<6 || pickUp_Time>21){
                        System.out.println("Serive not available for this time now....");
                        return;
                    }

                    cd.SetCustomerDetails(name,pickUp_point,drop_point,pickUp_Time);

                    List<Taxi> freeTaxis = freeTaxi(pickUp_point,drop_point,pickUp_Time,taxis);

                    if(freeTaxis.isEmpty()){
                        System.out.println("No taxi available now.....");
                        return;
                    }

                    Collections.sort(freeTaxis,(a, b) -> a.earnings - b.earnings);
                    booking(pickUp_point,drop_point,pickUp_Time,freeTaxis);
                    break;
                case 2:
                    for(Taxi t:taxis){
                        t.printDetails();
                    }
                    break;
                case 3:
                    enter = false;
                    break;
            }
        }
    }



    public static List<Taxi> createTaxis(int n){
        List<Taxi> taxis = new ArrayList<Taxi>();
        for(int i=1;i<=n;i++){
            Taxi taxi = new Taxi();
            taxis.add(taxi);
        }
        return taxis;
    }

    public static List<Taxi> freeTaxi(char pickUp_point,char drop_point,int pickUp_Time,List<Taxi> taxi){
        List<Taxi> freeTaxis = new ArrayList<Taxi>();
        for(Taxi t : taxi){
            if(t.free_time <= pickUp_Time && Math.abs((pickUp_point - '0') - (t.current_place - '0'))<=pickUp_Time-t.free_time){
                freeTaxis.add(t);
            }
        }
        return freeTaxis;
    }

    public static void booking(char pickUp_point,char drop_point,int pickUp_Time,List<Taxi> freetaxis){
        Taxi booking = null;
        int min = Integer.MAX_VALUE;
        int distanceBetweenPickUpAndDrop = 0;
        char current_spot = 'S';
        int free_time = 0;
        int earnings = 0;


        String trip_details = " ";

        for(Taxi t : freetaxis){
            int distanceBetweenTaxiAndCustomer = Math.abs((cd.pickUp_point -'0') - (t.current_place - '0'))*15;
            if(distanceBetweenTaxiAndCustomer<min){
                booking = t;
                distanceBetweenPickUpAndDrop = Math.abs((cd.drop_point - '0')-(cd.pickUp_point - '0'))*15;
                earnings = (distanceBetweenPickUpAndDrop - 5)*10+100;
                current_spot = cd.drop_point;
                int drop_time = cd.pickUp_time+distanceBetweenPickUpAndDrop/15;
                free_time = drop_time;
                trip_details =  cd.customerId+"       "+cd.customerId+"        "+pickUp_point+"         "+drop_point+"       "+pickUp_Time+"      "+drop_time+"      "+earnings;
                min = distanceBetweenTaxiAndCustomer;
            }
        }
        booking.setDetails(true,current_spot,free_time,booking.earnings+earnings,trip_details);

        System.out.println("Taxi: "+booking.id+" is alloted....");
    }
}