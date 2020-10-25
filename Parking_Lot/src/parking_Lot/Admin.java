package parking_Lot;

public class Admin
{
	private int password;
	
	Admin(){}
	
    Admin(int password)
    {          
		this.password = password;   //constructor with password
	}
	
    public boolean check_password(int password)
    {  //call the constructor
        if(this.password == password) 
        {
			return true;
        }
        else
			return false;
	}
}
