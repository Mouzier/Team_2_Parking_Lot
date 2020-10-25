package parking_Lot;

//class for describing the components for parking_spot

public class Parking_spot
{
    //boolean variables for asssigning the values for vacancy and charging point
	private boolean vacancy = true;
	private boolean charge_point = false;
    
    //integer variables for parking spot id and unique assigned id
    
    private long parking_id;
    private int assigned_id;
    
    //set function for the vacancy status
    
    public void set_vacancy_status(boolean status){vacancy = status;}
    
    //get function for getting the vacancy status
    
    public boolean get_vacancy_status(){return vacancy;}

    //set function for id
    
    public void set_id(long id){this.parking_id = id;}
    

    //get function for getting the id
    
    public long get_id(){
        return parking_id;
    }

    //set function for the charging point status
    
    public void set_charge_point_status(boolean status){
        charge_point = status;
    }
    
    //get function for getting the charging point status
    
    public boolean get_charge_point_status(){
        return charge_point;
    }

    //set function for the assigned id
    
    public void set_assigned_id(int id){
        assigned_id = id;
    }
    
    //get function for getting the assigned id
    
    public int get_assigned_id(){
        return assigned_id;
    }
    
}