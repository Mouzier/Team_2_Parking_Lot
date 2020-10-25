package parking_Lot;

public class Customer {
		private boolean handicap;
		private int assigned_id;
		private String in_time;
		private int vehicle_type;
		private int floor = -1;
		
		
		Customer(boolean handicap, String in_time, int assigned_id,int vehicle_type){   // constructor with handicap,in_time,assigned_id,vehicle_type
			this.handicap = handicap;
			this.in_time = in_time;
			this.assigned_id = assigned_id;
			this.vehicle_type = vehicle_type;
		}
		
		public void set_floor(int floor) {   // setter method
			this.floor = floor;
		}
		
		public int get_floor() {           // getter method
			return floor;
		}
		
		public boolean get_handicap(){    // creating boolean
	        return handicap;
	    }
		
		public int get_assigned_id() {    // getter method
			return assigned_id;
		}
		
		public int get_vehicle_type() {   // getter method
			return vehicle_type;
		}
		
		public String get_time() {      // getter method
			return in_time;
		}
}
