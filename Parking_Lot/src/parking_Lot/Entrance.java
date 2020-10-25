package parking_Lot;

import java.time.LocalDateTime;
import java.time.format.*;

class Entrance{
	
	public int size;
	public boolean electric;
	public boolean handicap;
	public int vehicle_type;
	public String NumberPlate;
	public String in_time;
	public int assigned_id;
	
	public void set_time(){         // setter method
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss"); // date and time
        LocalDateTime now = LocalDateTime.now();  
        in_time = now.format(dtf);  
    }
	
	public void print_ticket() {
		System.out.println("This is your id - " +  assigned_id); // Assigned id
		System.out.println("Your time of entry - " + in_time); // time
	}
}