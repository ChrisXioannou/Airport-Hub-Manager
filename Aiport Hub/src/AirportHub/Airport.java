package AirportHub;

import java.util.ArrayList;

public class Airport {
	private String name;
	private String codedname;
	private String city;
	private String country;
	private ArrayList<Airport> connections=new ArrayList<>();
	private ArrayList<String> companies=new ArrayList<>();
	
	public Airport(String text1,String text2,String text3,String text4) {
		name = text1;
		codedname = text2;
		city = text3;
		country = text4;
		connections = new ArrayList<>();
		companies=new ArrayList<>();
	}
	
	@Override
	public String toString() {
	    return "Aiport City: " + city+ " ,Airport Code: " + codedname+" ,Airport Country: "+ country ;
		}
	
	public void addcompany(String acompany){
		
	
		 if (!companies.contains(acompany)) {
		        companies.add(acompany);
		 		}	
	}

	public void addconnection(Airport anAirport){
		
		connections.add(anAirport);		
	}
	
	public ArrayList<Airport> getConnections() {
		return connections;
	}

	public String getName() {
		return name;
	}
	
	public String getCodedname() {
		return codedname;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	
	public Boolean isDirectlyConnectedTo(Airport anAirport){
		
		 for (Airport connection : connections) {
			 
		        if (connection.equals(anAirport)) {
		        	
		            return true;
		        	}
		
		 		}
		 return false;
		}
	
	
	public Boolean isInDirectlyConnectedTo(Airport anAirport) {
		
		Airport current=null;
			
			 for (Airport connection : connections) {
				 
				 
				 current=connection;
				 
				 	if(current.isDirectlyConnectedTo(anAirport)==true);
				 	return true;
			 	}
			 
			 return false;
		}
	
	public ArrayList<Airport> InDirectlyConnectedToAirport(Airport anAirport) {
		
		Airport current=null;
		
			 ArrayList<Airport>Airports = new ArrayList<>();
			 
			 for (Airport connection : connections) {
				 
				 current=connection;
				 
				 	if(current.isDirectlyConnectedTo(anAirport)==true) {
				 	Airports.add(current);
				 		}
				 	
			 	}
			 
			 return Airports;
	 }
			
	
	public ArrayList<Airport> getCommonConnections(Airport anAirport){
		
		ArrayList<Airport> commons=new ArrayList<>();
		ArrayList<Airport> array2=new ArrayList<>();
		
		
			array2=anAirport.getConnections();
	
			for (Airport connection : connections) {
		
				for(Airport connection2:array2)
					if(connection.equals(connection2)) {
						commons.add(connection);
			 			}
	
	 			}
			return commons;
		}


	public void printCompanies() {
		
		for (String company: companies)
			System.out.println(company);
 		}
	
	
	public ArrayList<String> companiesretuner(){
		
		ArrayList<String> comapaniesArray=new ArrayList<>();
		
		for (String company: companies) {
			comapaniesArray.add(company);
 		}
		
		return comapaniesArray;
	}

}



