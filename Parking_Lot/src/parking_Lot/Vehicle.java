package parking_Lot;

abstract class Vehicle
{
	protected String NumberPlate;  // vehicle attribute
	protected int assigned_id;
	
    public String get_NumberPlate() {
        return NumberPlate;
    }

    /*public void set_NumberPlate(String NumberPlate) {
        this.NumberPlate = NumberPlate;
    }*/
	/*public void set_assigned_id(int id) {
		assigned_id = id;
	}*/
	
    public int get_assigned_id()
    {
		return assigned_id;
	}
}

class Bike extends Vehicle{  // vehicle inherits bike
	
	Bike(){}
	
	Bike(int assigned_id, String NumberPlate){  // constructor
		this.NumberPlate = NumberPlate;
		this.assigned_id = assigned_id;
	}
}

class Truck extends Vehicle{     // vehicle inherits truck
	
	Truck(){}
	Truck(int assigned_id, String NumberPlate){  // constructor
		this.NumberPlate = NumberPlate;
		this.assigned_id = assigned_id;
	}
}

class Car extends Vehicle{   // vehicle inherits car
	
	
	private boolean electric;
	private int size;
	
	Car(){}
	Car(int assigned_id,String NumberPlate, boolean electric,int size){  // constructor with assigned_id,numberplate,electric,size
		this.assigned_id = assigned_id;
		this.NumberPlate = NumberPlate;
		this.electric = electric;
		this.size = size;
	}
	
	public boolean get_electric() {   // getter method
		return electric;
	}
	
	public int get_size() {    // getter method
		return size;
	}
}