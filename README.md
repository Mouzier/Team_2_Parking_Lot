# Team_2_Parking_Lot
This is a program for handling, allotment and payment at a parking lot


TEAM MEMBERS
CS19B002 - AMASA YASWANTH
CS19B015 - GAGAN HEGDE
CS19B018 - GUDIPATI PAVAN SRIRAM
CS19B043 - YAYI SAI SRUJAN
CS19B045 - EMANI NAGA SAI VENKATA SOWMYA


The program is based on a menu-driven system

The input from the user is taken in the form of numerical system.

The beginning of the program is the creation or abstraction of the parking lot.
The instructions are clear at the start. Instructions are given for the various input in the following order:
1. No of floors
2. Parking alotted for each parking type (car, bike, truck,bike handicap, car handicap)
3. Number of entrances
4. Number of exits
5. The cost for various type of vehicles (for 1st hour, 2nd hour, 3rd hour(remaining hours))
6. Password for admin access.

The first menu is for interaction with the system:
1. For a new customer
2. For customer already having a car
3. Admin access

	Clear instructions are mentioned under each option, for input values required for the various 		elements of the customer (Like handicap, vehicle license plate)

Under option 1. (We understand a parking attendant can also do this job, as like an automated system without any difference)
	1.Once the customer have input all the necessay information, the customer is assigned a parking a spot.
	2.Once the parking spot is assigned, the customer is given a 5 digit unique code that they need to input when they return to retrieve their vehicle

Under option 2.
	1.The customer must input the UNIQUE ID given to them
	2.Once input, it is understood that they want to leave and they are taken to the payment gateway
	3.They are given an option to pay in cash, by credit card or upi.
	4.The amount to be paid is displayed

Under admin access;
You are only allowed access if you know the password for entry
Once the right password is entered, you are allowed into another menu

1. Customer information - (To see the customer information)
2. Remove customer - (Manually remove a customer and his vehicle from the meaning)
3. Goto main page - (Goes to the previous menu)
4. Shutdown system - (Ends program)
5. Total revenue - (Outputs the total amount collected)

We have used ArrayList for certain dynamic aspects of the program i.e.
	1.Customer who enter and leave the parking lot
	2.The vehicle of the customer in the system at the point of time
