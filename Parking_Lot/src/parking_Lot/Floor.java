package parking_Lot;

//class for describing the components of floor.
class Floor
{
    public int types=5;      //types == types of vehicles
    private static int n_cars=0, n_bikes=0, n_trucks=0,n_handicap_cars=0,n_handicap_bikes=0;
    public int max_cars, max_bikes, max_trucks, max_handicap_cars, max_handicap_bikes;
    int i,j,k;

//different array of objects for different types of vehicles   

    Parking_spot bike[];
    Parking_spot car[];
    Parking_spot truck[];
    Parking_spot handicap_car[];
    Parking_spot handicap_bike[];

//constructor for setting the values for the number of vehicles and for initializing the array of objects for different vehicles 
    Floor(int carnum, int bikenum, int trucknum, int bike_handicap_num, int car_handicap_num){
        max_cars = carnum;
        max_trucks = bikenum;
        max_bikes = trucknum;
        max_handicap_bikes = bike_handicap_num;
        max_handicap_cars = car_handicap_num;
        
        bike = new Parking_spot[max_bikes];
        car = new Parking_spot[max_cars];
        truck = new Parking_spot[max_trucks];
        handicap_car = new Parking_spot[max_handicap_cars];
        handicap_bike = new Parking_spot[max_handicap_bikes];
        
        for(j=0; j<=max_bikes-1; j++){
        	bike[j] = new Parking_spot();
        	bike[j].set_id(j);
        }
    	for(j=0; j<=max_cars-1; j++){
    		car[j] = new Parking_spot();
    		car[j].set_id(j);
    	}
    	for(j=0; j<=max_trucks-1; j++){
    		truck[j] = new Parking_spot();
    		truck[j].set_id(j);
   		} 		
    	for(j=0; j<=max_handicap_cars-1; j++){
    		handicap_car[j] = new Parking_spot();
   			handicap_car[j].set_id(j);
   		}  		
    	for(j=0; j<=max_handicap_bikes-1; j++){
    		handicap_bike[j] = new Parking_spot();
   			handicap_bike[j].set_id(j);
        }
    	
    }

//function to display the vacancies and number of vacancies for different types of vehicles in a floor

    void display() {
        System.out.println("The vacant spots for bikes are : ");
        j=0;
        for (i = 0; i <= max_bikes - 1; i++) {
            if (bike[i].get_vacancy_status()) {
                System.out.print(bike[i].get_id() + " ");
                 j++;
            }
        }
        System.out.println();
        System.out.println("Number : " + j );
        System.out.println();
        
        
        System.out.println("The vacant spots for cars are : ");
        j=0;
        for (i = 0; i <= max_cars - 1; i++) {
            if (car[i].get_vacancy_status()) {
                System.out.print(car[i].get_id() + " ");
                 j++;
            }
        }
        System.out.println();
        System.out.println("Number : " + j );
        System.out.println();
        
        
        System.out.println("The vacant spots for trucks are : ");
        j=0;
        for (i = 0; i <= max_trucks - 1; i++) {
            if (truck[i].get_vacancy_status())
                System.out.print(truck[i].get_id() + " ");
                j++;
        }
        System.out.println();
        System.out.println("Number : " + j );
        System.out.println();

        System.out.println("The vacant spots for handicapped cars are : ");
        j=0;
        for (i = 0; i <= max_handicap_cars - 1; i++) {
            if (handicap_car[i].get_vacancy_status())
                System.out.print(handicap_car[i].get_id() + " ");
                j++;
        }
        System.out.println();
        System.out.println("Number : " + j );
        System.out.println();

        System.out.println("The vacant spots for handicapped bikes are : ");
        j=0;
        for (i = 0; i <= max_handicap_bikes - 1; i++) {
            if (handicap_bike[i].get_vacancy_status())
                System.out.print(handicap_bike[i].get_id() + " ");
                j++;
        }
        System.out.println();
        System.out.println("Number : " + j );
        System.out.println();
    }
}

