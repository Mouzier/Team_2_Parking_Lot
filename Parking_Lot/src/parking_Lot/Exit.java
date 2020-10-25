package parking_Lot;

import java.time.LocalDateTime;
import java.time.format.*;
import java.util.Scanner;

class Exit
{
    
    //  typeOfPayment is for getting to know the type of payment
    //      1 . cash
    //      2 . credit
    //      3 . UPI
    
    //  hoursParked is for getting to know total number of hours parked
    
    //  firstHCost is for getting the cost of a particular under certain circumstances during 1st hour
    //  secondHCost and other is as firstHCost
    
    private int typeOfPayment;
	private float hoursParked;
	private float firstHCost;
	private float secondHCost;
	private float thirdHCost;
	private int typeOfVehicle;
	private boolean charging_point;
	private float AmountToBePaid=0;
	private String [] PAY = new String[4];
	private String UPIid;
	public static float electric_cost;
	public static float HelpPayment[][]= new float[3][3];
    
    Exit(){}
    

    //  this constructor is for taking the customer details to know the type of vehicle and its attributes
    //  we are passing customer here to do the above mentioned

    Exit(Customer c, boolean charging_point)
	{
		typeOfVehicle=c.get_vehicle_type()-1;
		this.firstHCost = HelpPayment[typeOfVehicle][0];
		this.secondHCost = HelpPayment[typeOfVehicle][1];
		this.thirdHCost = HelpPayment[typeOfVehicle][2];
		this.charging_point = charging_point;
	}
    
    //  this function gets the payment type preferred by the customer  
    
    public void getPaymentType(int n)  {typeOfPayment = n;}
    
    //   if the customer selects credit payment we take the following details for transcation purpose

    public void getDetailsCredit(String ccn,String cvv,String date,String name)
	{
		PAY[0]=ccn;
		PAY[1]=cvv;
		PAY[2]=date;
		PAY[3]=name;
	}
    
    //      this function gets details regarding UPI payment

    public void getDetailsUPI(String UPIid){    this.UPIid = UPIid; }
    
    //      we will know the intime of the customer as we are passing the customer in constructor
    //      now we will get the outtime as the vehicle is exiting
    //      We further proceed to calculate the hours parked

    public void calculate_time(String in_time) 
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String outime_time = now.format(dtf);

        int in_h, o_h, in_min, o_min;

        String h = in_time.substring(0, 2);
        in_h = Integer.parseInt(h);

        h = outime_time.substring(0,2);
        o_h = Integer.parseInt(h);

        h = in_time.substring(3, 5);
        in_min = Integer.parseInt(h);

        h = outime_time.substring(3, 5);
        o_min = Integer.parseInt(h);

        if(o_h >= in_h)
        {
            hoursParked = (o_h - in_h) + (float)((o_min - in_min)/60.0);
            int check = (int)hoursParked;
            if(hoursParked != check){	hoursParked = check+1;}
        }
        else
        {
            hoursParked = (24-(in_h - o_h)) + (float)((o_min - in_min)/60.0);
            int check = (int)hoursParked;
            if(hoursParked != check) {hoursParked = check+1;}
        }
    }
    
    //      This the important  function of this class
    //      depending upon the payment type ,we collect money through different means
    //      and return the amount collected
    //      Then we add this to the Treasury of the parking lot.
 
    public float pay()
	{
        //This function is used to calculate the amount depending upon vehicle type ,electric attribute and hours parked.
        
        calculateAmount();
        
        if(typeOfPayment==1)
		{
			cash Wallet = new cash(AmountToBePaid);
			System.out.println(" Money received :) ");
            Wallet.paymentDetails();
            System.out.println("THANK YOU ! VISIT US AGAIN !");
		}
		else if(typeOfPayment==2)
		{
			credit CARD = new credit(AmountToBePaid,PAY[0],PAY[1],PAY[2],PAY[3]);
            CARD.paymentDetails();
            System.out.println("THANK YOU ! VISIT US AGAIN !");
		}
		else 
		{
			UPI googlePay = new UPI(AmountToBePaid,UPIid);
            googlePay.paymentDetails();
            System.out.println("THANK YOU ! VISIT US AGAIN !");
		}
		return AmountToBePaid;
	}
    
    // thsi function claculates the amount to be paid by the customer

    public void calculateAmount()
	{
		float time = hoursParked;
		for(int i=0;i<3;i++)
		{
			if(hoursParked==0)
			{
				AmountToBePaid+=0;
			}
			else
			{
				if(i<2)
				{
				AmountToBePaid+=HelpPayment[typeOfVehicle][i];
				if(hoursParked!=0) {hoursParked-=1;}
				else {hoursParked=0;}
				}
				else
				{
					AmountToBePaid+=hoursParked*HelpPayment[typeOfVehicle][2];
				}
			}
		}
		if(charging_point) {
			AmountToBePaid += time*electric_cost;
		}
	}
	
}

// this is credit class which takes the various details regarding payment
// and gives us the payment details when called (function) 


class credit 
{
		private float creditAmount=0;
		private String ccn;
		private String cvv;
		private String date;
		private String name;
		credit(float paymentAmount,String ccn,String cvv,String date,String name)
		{
			creditAmount=paymentAmount;
			this.ccn=ccn;
			this.cvv=cvv;
			this.date=date;
			this.name=name;
		}
		public String getCcn(){return ccn;}
		public void setCcn(String ccn){this.ccn = ccn;}
		public String getCvv(){return cvv;}
		public void setCvv(String cvv){this.cvv = cvv;}
		public String getDate(){return date;}
		public void setDate(String date){this.date = date;}
		public String getName(){return name;}
		public void setName(String name){this.name = name;}
		public void paymentDetails()
		{
			System.out.println("Name on card : " + this.getName());
			System.out.println("Expiration date : " + this.getDate());
			System.out.println("Credit card number : " + this.getCcn());
			System.out.println("cvv number : " + this.getCvv());
			System.out.println("The amount credited from the card is : " + creditAmount);
		}
}


// This class is for processing UPI payment
// gives the details of the specific payment
// which is displayed to the customer

class UPI
{
	private float upAmount=0;
	private String UPIID;
	UPI(float paymentAmount,String UPIID)
	{
		upAmount=paymentAmount;
		this.UPIID=UPIID;
	}
	
	public void setUPIID(String id)
	{
		UPIID=id;
	}
	public String getUPIID() {return UPIID;}
	public void paymentDetails()
	{
		System.out.println(upAmount+" is being successfully transferred from UPI "+ this.getUPIID());
	}
}


// This class for cash payment

// which displays the payment details to the customer

class cash 
{
	private float cashAmount=0;
		cash(float paymentAmount)
		{
			cashAmount =paymentAmount;
			
		}
		public void paymentDetails()
		{
			System.out.println("The payment amount is : " + this.cashAmount);
		}
}