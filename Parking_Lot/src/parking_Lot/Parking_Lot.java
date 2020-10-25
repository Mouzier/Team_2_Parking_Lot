package parking_Lot;
import java.util.*;
import java.lang.Math;
public class Parking_Lot 
{

	static ArrayList<Customer> c1 = new ArrayList<Customer>();
	static ArrayList<Car> car1 = new ArrayList<Car>();
	static ArrayList<Bike> bike1 = new ArrayList<Bike>();
	static ArrayList<Truck> truck1 = new ArrayList<Truck>();
	static int issue_id;
	
	
    public static void main(String[] args) 
    {
		int hold = 10000;
		issue_id = (int)(Math.random()*hold);
		
		int total_amount = 0;
		Scanner sc = new Scanner(System.in);
		Exit ex1 = new Exit();
		
		System.out.println("Input number of floors");
		int no_of_floor = sc.nextInt();
		
		Floor[] floorarr = new Floor[no_of_floor];                          //Taking input for number of parking spot for various vehicle type
		System.out.print("Enter number of parking space for cars : ");
		int car_num = sc.nextInt();
		System.out.print("Enter number of parking space for bike : ");
		int bike_num = sc.nextInt();
		System.out.print("Enter number of parking space for trucks : ");
		int truck_num = sc.nextInt();
		System.out.print("Enter number of parking space for bike handicap : ");
		int bikehandicap_num = sc.nextInt();
		System.out.print("Enter number of parking space for car handicap : ");
		int carhandicap_num = sc.nextInt();
		
		System.out.println("\n\n");
		
		for(int i =0;i<no_of_floor;i++) {
			floorarr[i] = new Floor(car_num, bike_num, truck_num, bikehandicap_num, carhandicap_num);
		}
		
		System.out.println("What are the number of entrances in your building?");
		int no_of_entrances = sc.nextInt();
		System.out.println("What are the number of exits?");
		int no_of_exits = sc.nextInt();
		
		System.out.println("Vehicle code: \n1.Car\n2.Bike\n3.Truck");                       //Taking the amount per hour for every type of vehicle
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.print("Enter the amount to be collected for " +(i+1) +" type vehicle for " + (j+1) + "th hour");
				ex1.HelpPayment[i][j] = sc.nextFloat();
			}
		}


		System.out.println("Input cost per unit electricity");   	//Cost for electricity
		ex1.electric_cost = sc.nextFloat();
		
		System.out.println("Enter a password for admin access");  	//Admin Password
		int password = sc.nextInt();
		Admin a1 = new Admin(password);
		
		
		Entrance e1 = new Entrance();
		boolean loop = true;
		
		while(loop) {
			System.out.println("Please input via numbers as indicated");
			System.out.println("1.Are you a new customer\n2.Retrieve your vehicle\n3.Admin Access");
			int option = sc.nextInt();
			
			System.out.println("\n");
			
			boolean in_system = false;
			
			System.out.println("\n");
			
			switch(option) {
			case 1: 
					System.out.println("Which entrance do you want to enter?");
					for(int i=0; i < no_of_entrances;i++) {
						System.out.println(i+1);
					}
					int entrance = sc.nextInt();
					System.out.println("\n");
					if(entrance>0 && entrance<= no_of_entrances) {
						System.out.println("Welcome");
					}
					else {
						System.out.println("Invalid input. You have entered through the wrong path.");
						continue;
					}
					
					System.out.println("\n");
					System.out.println("What is your vehicle?");
					System.out.println("1.Car\n2.Bike\n3.Truck");
					e1.vehicle_type = sc.nextInt();
					
					System.out.println("\n");
					switch(e1.vehicle_type) {
						case 1:
								System.out.println("What is the size of your car?");
								System.out.println("1.Compact\n2.Large");
								e1.size = sc.nextInt();	
								System.out.println("\n");
								
								System.out.println("Is your car electric?");
								System.out.println("1.Yes\n 2.No");
								int n = sc.nextInt();
								
								System.out.println("\n");
								switch(n) {
									case 1: 
										e1.electric = true;
										break;
									case 2:
										e1.electric = false;
										break;
									default:
										System.out.println("Invalid input. Please try again");
										System.out.println("\n");
										break;
								}
								in_system = true;
								
								break;
						case 2: 
								in_system = true;
								break;
						case 3:
								in_system = true;
								break;
						default:
								System.out.println("Invalid input. Please try again");
								System.out.println("\n");
								break;
					}
					if(in_system) {
						System.out.println("Input vehicle license plate");
						e1.NumberPlate = sc.next();
						System.out.println("");
					}
					if(in_system) {
						System.out.println("Are you handicapped?");
						System.out.println("1.Yes\n2.No");
						int n = sc.nextInt();
						System.out.println("");
						
						switch(n) {
							case 1: 
									e1.handicap = true;
									break;
							case 2:
									e1.handicap = false;
									break;
							default:
									System.out.println("Invalid input. Please try again");
									System.out.println("\n");
									break;
				         	}
					}
					if(in_system) {
						e1.set_time();
						e1.assigned_id = issue_id++ ;	
						
						c1.add(new Customer(e1.handicap,e1.in_time,e1.assigned_id,e1.vehicle_type));
						switch(e1.vehicle_type) {
						case 1: 
								car1.add(new Car(e1.assigned_id,e1.NumberPlate,e1.electric,e1.size));
								break;
						case 2: 
								bike1.add(new Bike(e1.assigned_id,e1.NumberPlate));
								break;
						case 3:
								truck1.add(new Truck(e1.assigned_id,e1.NumberPlate));
								break;
						}
					}
					if(in_system) {
						for(int i =0;i < no_of_floor;i++) {
                            System.out.println("The vacancy spots in floor "+ (i+1) +" are :");
							floorarr[i].display();
						}
					int i,j=-1;
						switch(e1.vehicle_type) {
						case 1:
								if(e1.handicap) {             				//Searching and allotting parking space
									for(i=0; i< no_of_floor;i++) {
										for(j=0; j < carhandicap_num; j++) {
											if(floorarr[i].handicap_car[j].get_vacancy_status()) {
												floorarr[i].handicap_car[j].set_vacancy_status(false);
												floorarr[i].handicap_car[j].set_assigned_id(e1.assigned_id);
												c1.get(c1.size()-1).set_floor(i);
												break;
											}
										}
										if(j<carhandicap_num) {
											break;
										}
									}
									if(i == no_of_floor) {
										c1.remove(c1.size()-1);
										System.out.println("No vacancy");
										continue;
									}
									if(e1.electric) {
										System.out.println("Do you want to charge your vehicle");
										System.out.println("1.Yes\n2.No");
										int choice = sc.nextInt();
										if(choice == 1) {
											floorarr[i].handicap_car[j].set_charge_point_status(true);		
										}else {
											floorarr[i].handicap_car[j].set_charge_point_status(false);
										}
									}
								}
								else {
									for(i=0; i< no_of_floor;i++) {
										for(j=0; j<car_num;j++) {			//Searching and allotting parking space
											if(floorarr[i].car[j].get_vacancy_status()) {
												floorarr[i].car[j].set_vacancy_status(false);
												floorarr[i].car[j].set_assigned_id(e1.assigned_id);
												c1.get(c1.size()-1).set_floor(i);
												break;
											}
										}
										if(j<car_num) {
											break;
										}
									}
									if(i == no_of_floor) {
										c1.remove(c1.size()-1);
										System.out.println("No vacancy");
										continue;
									}
								if(e1.electric) {
									System.out.println("Do you want to charge your vehicle");
									System.out.println("1.Yes\n2.No");
									int choice = sc.nextInt();
									if(choice == 1) {
										floorarr[i].car[j].set_charge_point_status(true);
									}else {
										floorarr[i].car[j].set_charge_point_status(false);
									}
								}
								}
                                    break;
                            case 2:
								if(e1.handicap) {
									for(i=0; i< no_of_floor;i++) {
										for(j=0; j < bikehandicap_num; j++) {				//Searching and allotting parking space
											if(floorarr[i].handicap_bike[j].get_vacancy_status()) {
												floorarr[i].handicap_bike[j].set_vacancy_status(false);
												floorarr[i].handicap_bike[j].set_assigned_id(e1.assigned_id);
												c1.get(c1.size()-1).set_floor(i);
												break;
											}
					
										}
										if(j < bikehandicap_num) {
											break;
										}
									}
									if(i == no_of_floor) {
										c1.remove(c1.size()-1);
										System.out.println("No vacancy");
										continue;
									}
								}
								else {
									for(i=0; i< no_of_floor;i++) {
										for(j=0; j < bike_num;j++) {					//Searching and allotting parking space	
											if(floorarr[i].bike[j].get_vacancy_status()) {
												floorarr[i].bike[j].set_vacancy_status(false);
												floorarr[i].bike[j].set_assigned_id(e1.assigned_id);
												c1.get(c1.size()-1).set_floor(i);
												break;
											}
					
										}
										if(j < bike_num) {
                                            break;
										}
									}
									if(i == no_of_floor) {
										c1.remove(c1.size()-1);
										System.out.println("No vacancy");
										continue;
									}
								}
							 break;
						case 3:
						       for(i=0; i< no_of_floor;i++) {
										for(j=0; j<truck_num;j++) {					//Searching and allotting parking space
											if(floorarr[i].truck[j].get_vacancy_status()) {
												floorarr[i].truck[j].set_vacancy_status(false);
												floorarr[i].truck[j].set_assigned_id(e1.assigned_id);
												c1.get(c1.size()-1).set_floor(i);
												break;
											}
					
										}
										if(j<truck_num) {
											break;
										}
									}
									if(i == no_of_floor) {
										c1.remove(c1.size()-1);
										System.out.println("No vacancy");
										continue;
									}
							 break;
						   }
					}
					e1.print_ticket();
				 	break;
			case 2: 
					System.out.println("Which exit do you want to go through");
					for(int i=0;i<no_of_exits;i++) {
						System.out.println(i+1);
					}
					int exit = sc.nextInt();
					if(exit >0 && exit<= no_of_exits) {
						System.out.println("You can proceed");
					}
					else {
						System.out.println("Invalid input");
						continue;
					}
					
					System.out.println("Enter your assigned id");
					int id = sc.nextInt();
					Customer current_customer = HelpWithCostumers(id);
					Car current_car;
					Bike current_bike;
					Truck current_truck;
					if(current_customer == null) {
						System.out.println("Your vehicle is not in the building");
						continue;
					}
					switch(current_customer.get_vehicle_type()) {
					case 1:
							current_car = HelpWithCars(id);
							break;
					case 2: 
							current_bike = HelpWithBikes(id);
							break;
					case 3:
							current_truck = HelpWithTrucks(id);
							break;
					}
					
					
					for(int i=0; i<c1.size();i++) {
						if(c1.get(i).get_assigned_id() == current_customer.get_assigned_id())
							c1.remove(i);
					}
					boolean electric_charging = false;
					switch(current_customer.get_vehicle_type()) {
					case 1: 
							if(current_customer.get_handicap()) {
								for(int i=0;i<carhandicap_num;i++) {				//Searching and deassigning parking spot
									if(floorarr[current_customer.get_floor()].handicap_car[i].get_assigned_id() == current_customer.get_assigned_id()) {
										floorarr[current_customer.get_floor()].handicap_car[i].set_vacancy_status(true);
										electric_charging = floorarr[current_customer.get_floor()].handicap_car[i].get_charge_point_status();
										System.out.println(electric_charging);
									}
								}
							}
							else {
								for(int i=0; i < car_num; i++) {				//Searching and deassigning parking spot	
									if(floorarr[current_customer.get_floor()].car[i].get_assigned_id() == current_customer.get_assigned_id()) {
										floorarr[current_customer.get_floor()].car[i].set_vacancy_status(true);
										electric_charging = floorarr[current_customer.get_floor()].car[i].get_charge_point_status();
										System.out.println(electric_charging);
						           	}
			              		}
					     	}
					     	for(int i=0; i < car1.size();i++) {
					     		if(car1.get(i).get_assigned_id() == current_customer.get_assigned_id())
					     			car1.remove(i);
				         	}
					     	break;
					 case 2:
					         if(current_customer.get_handicap()) {
								for(int i=0;i < bikehandicap_num;i++) {				//Searching and deassigning parking spot
									if(floorarr[current_customer.get_floor()].handicap_bike[i].get_assigned_id() == current_customer.get_assigned_id()) {
										floorarr[current_customer.get_floor()].handicap_bike[i].set_vacancy_status(true);
									}
								}
							}
							else {
								for(int i=0;i<bike_num; i++) {					//Searching and deassigning parking spot
									if(floorarr[current_customer.get_floor()].bike[i].get_assigned_id() == current_customer.get_assigned_id()) {
										floorarr[current_customer.get_floor()].bike[i].set_vacancy_status(true);
						           	}
			              		}
					     	}
					    	for(int i=0; i<bike1.size();i++) {
					    		if(bike1.get(i).get_assigned_id() == current_customer.get_assigned_id())
					    			bike1.remove(i);
				         	}
					     	break;
					 case 3: 
					        for(int i=0;i<truck_num; i++) {							//Searching and deassigning parking spot
									if(floorarr[current_customer.get_floor()].truck[i].get_assigned_id() == current_customer.get_assigned_id()) {
										floorarr[current_customer.get_floor()].truck[i].set_vacancy_status(true);
						           	}
			              		}
			              	for(int i=0; i<truck1.size();i++) {
			              		if(truck1.get(i).get_assigned_id() == current_customer.get_assigned_id())
			              			truck1.remove(i);
				         	}
					     	break; 
							}
					
						Exit e2 = new Exit(current_customer, electric_charging);
						System.out.println("How do you want to pay");
						System.out.println("1.Cash\n2.Credit\n3.UPI");
						int n = sc.nextInt();
						
						e2.calculate_time(current_customer.get_time());
						
						e2.getPaymentType(n);
						boolean payment_loop = true;
						while(payment_loop) {
							switch(n) {
						
							case 1 :
									total_amount+=e2.pay();
									payment_loop = false;
									break;
							case 2:
									System.out.println("Input your details");
									System.out.println("Card number : ");
									String ccn = sc.next();
									System.out.println("CVV : ");
									String cvv = sc.next();
									System.out.println("Expiry Date : ");
									String date = sc.next();
									System.out.println("Enter name on card : ");
									String name = sc.next();
								
									e2.getDetailsCredit(ccn, cvv, date, name);
									
									total_amount += e2.pay();
								
									payment_loop = false;
									break;
							case 3: 
									System.out.println("Enter your UPI");
									String UPIid = sc.next();
									e2.getDetailsUPI(UPIid);
									total_amount += e2.pay();
									
									payment_loop = false;
									break;
							default:
									System.out.println("Invalid option");
									break;
							}
						}
						
						
				    	break;
			case 3 :
						System.out.print("Enter your admin password");
						password= sc.nextInt();
						boolean admin_loop = true;
						while(admin_loop) {
							if(a1.check_password(password)){
								System.out.println("Welcome");
								System.out.println("1.Customer Info\n2.Remove customer\n3.Goto main page\n4.System shutdown\n5.Display revenue");
								int choice = sc.nextInt();
								
								switch(choice) {
								case 1:
										for(int i =0; i<c1.size();i++) {                //Printing out customer information
											System.out.println("Customer assigned id : " + c1.get(i).get_assigned_id());
											System.out.println("Customer entry time : " + c1.get(i).get_time());
											if(c1.get(i).get_vehicle_type() == 1) {
												System.out.println("Customer vehicle type : Car");
												Car info_car = new Car();
												info_car = HelpWithCars(c1.get(i).get_assigned_id());
												System.out.println("Customer vehicle number plate : " + info_car.get_NumberPlate());
											}
											else if(c1.get(i).get_vehicle_type()==2){
												System.out.println("Customer vehicle type : Bike");
												Bike info_bike = new Bike();
												info_bike = HelpWithBikes(c1.get(i).get_assigned_id());
												System.out.println("Customer vehicle number plate : " + info_bike.get_NumberPlate());
											}
											else {
												System.out.println("Customer vehicle type : Truck");
												Truck info_truck = new Truck();
												info_truck = HelpWithTrucks(c1.get(i).get_assigned_id());
												System.out.println("Customer vehicle number plate : " + info_truck.get_NumberPlate());
											}
										}
										break;
										
								case 2 :				//Deleting or removing customer with vehicle from the parking lot
										System.out.println("Enter customer id");
										int delete_id = sc.nextInt();
										Customer delete_customer = HelpWithCostumers(delete_id);
										Car delete_car;
										Bike delete_bike;
										Truck delete_truck;
										if(delete_customer == null) {
											System.out.println("This vehicle is not in the building");
											continue;
										}
										switch(delete_customer.get_vehicle_type()) {
										case 1:
												delete_car = HelpWithCars(delete_id);
												break;
										case 2: 
												delete_bike = HelpWithBikes(delete_id);
												break;
										case 3:
												delete_truck = HelpWithTrucks(delete_id);
												break;
										}
										
										
										for(int i=0; i<c1.size();i++) {
											if(c1.get(i).get_assigned_id() == delete_customer.get_assigned_id())
												c1.remove(i);
										}
										
										switch(delete_customer.get_vehicle_type()) {
										case 1: 
												if(delete_customer.get_handicap()) {
													for(int i=0;i<carhandicap_num;i++) {
														if(floorarr[delete_customer.get_floor()].handicap_car[i].get_assigned_id() == delete_customer.get_assigned_id()) {
															floorarr[delete_customer.get_floor()].handicap_car[i].set_vacancy_status(true);
														}
													}
												}
												else {
													for(int i=0; i < car_num; i++) {
														if(floorarr[delete_customer.get_floor()].car[i].get_assigned_id() == delete_customer.get_assigned_id()) {
															floorarr[delete_customer.get_floor()].car[i].set_vacancy_status(true);
											           	}
								              		}
										     	}
										     	for(int i=0; i < car1.size();i++) {
										     		if(car1.get(i).get_assigned_id() == delete_customer.get_assigned_id())
										     			car1.remove(i);
									         	}
										     	break;
										 case 2:
										         if(delete_customer.get_handicap()) {
													for(int i=0;i < bikehandicap_num;i++) {
														if(floorarr[delete_customer.get_floor()].handicap_bike[i].get_assigned_id() == delete_customer.get_assigned_id()) {
															floorarr[delete_customer.get_floor()].handicap_bike[i].set_vacancy_status(true);
														}
													}
												}
												else {
													for(int i=0;i<bike_num; i++) {
														if(floorarr[delete_customer.get_floor()].bike[i].get_assigned_id() == delete_customer.get_assigned_id()) {
															floorarr[delete_customer.get_floor()].bike[i].set_vacancy_status(true);
											           	}
								              		}
										     	}
										    	for(int i=0; i<bike1.size();i++) {
										    		if(bike1.get(i).get_assigned_id() == delete_customer.get_assigned_id())
										    			bike1.remove(i);
									         	}
										     	break;
										 case 3: 
										        for(int i=0;i<truck_num; i++) {
														if(floorarr[delete_customer.get_floor()].truck[i].get_assigned_id() == delete_customer.get_assigned_id()) {
															floorarr[delete_customer.get_floor()].truck[i].set_vacancy_status(true);
											           	}
								              		}
								              	for(int i=0; i<truck1.size();i++) {
								              		if(truck1.get(i).get_assigned_id() == delete_customer.get_assigned_id())
								              			truck1.remove(i);
									         	}
										     	break; 
												}
												break;
												
								case 3:	admin_loop = false;
										break;
										
								case 4: System.exit(0);
										break;
								case 5 : 
										System.out.println("Total amount earned till now : ");
										System.out.println(total_amount);
								default: 
										System.out.println("Invalid input");
										break;
								}
							}else {
								System.out.println("Wrong password. Reset status");
								admin_loop = false;
							}
						}
						break;
			default : System.out.println("Invalid input");
					break;
					}
			}
		
	}

	public static Customer HelpWithCostumers(int cid)                  //Returns a customer object with matching id from arraylist customer
    {
        for(int i=0;i<c1.size();i++)
        {
            if(c1.get(i).get_assigned_id()==cid)
            {
                return c1.get(i);
            }
        }
        return null;
    }

    public static Car HelpWithCars(int cid)				//Returns a CAR object with matching id from arraylist car
    {
        for(int i=0;i<car1.size();i++)
        {
            if(car1.get(i).get_assigned_id()==cid)
            {
                return car1.get(i);
            }
        }
        return null;
    }

    public static Bike HelpWithBikes(int cid)				//Returns a bike object with matching id from arraylist bike
    {
        for(int i=0;i<bike1.size();i++)
        {
            if(bike1.get(i).get_assigned_id()==cid)
            {
                return bike1.get(i);
            }
        }
        return null;
    }

    public static Truck HelpWithTrucks(int cid)				//Returns a truck object with matching id from arraylist truck
    {
        for(int i=0;i<truck1.size();i++)
        {
            if(truck1.get(i).get_assigned_id()==cid)
            {
                return truck1.get(i);
            }
        }
        return null;
    }

}

