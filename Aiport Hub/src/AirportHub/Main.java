package AirportHub;


import java.util.ArrayList;



	public class Main {
		
		public static void main(String[] args) {
			
			  // Lists to hold airport and flight information
			  ArrayList<Airport> airports = new ArrayList<>();
		      ArrayList<Flight> flights = new ArrayList<>();
		        
		      // Add airports to the list
		      // Each airport is created with its name, code, city, and country
		      airports.add(new Airport("Orly", "ORY", "Paris", "France"));
		      airports.add(new Airport("Fiumicino", "FCO", "Rome", "Italy"));
		      airports.add(new Airport("Venizelos", "ATH", "Athens", "Greece"));
		      airports.add(new Airport("Macedonia", "SKG", "Thessaloniki", "Greece"));
		      airports.add(new Airport("MunichAirport", "MUC", "Munich", "Germany"));
		      airports.add(new Airport("Charleroi", "CRL", "Brussels", "Belgium"));
		      airports.add(new Airport("Heathrow", "LHR", "London", "United Kingdom"));
		      airports.add(new Airport("John F Kennedy", "JFK", "New York", "USA"));
		      airports.add(new Airport("Changi", "SIN", "Singapore", "Singapore"));
		      airports.add(new Airport("Narita", "NRT", "Tokyo", "Japan"));
		      airports.add(new Airport("Charles de Gaulle", "CDG", "Paris", "France"));
		      airports.add(new Airport("Los Angeles International", "LAX", "Los Angeles", "USA"));
		      airports.add(new Airport("Dubai International", "DXB", "Dubai", "UAE"));
		      airports.add(new Airport("Frankfurt", "FRA", "Frankfurt", "Germany"));
		      airports.add(new Airport("Sydney Kingsford Smith", "SYD", "Sydney", "Australia"));
		      airports.add(new Airport("O'Hare", "ORD", "Chicago", "USA"));
		      airports.add(new Airport("Sheremetyevo", "SVO", "Moscow", "Russia"));
		      airports.add(new Airport("Hong Kong International", "HKG", "Hong Kong", "China"));
		      airports.add(new Airport("Suvarnabhumi", "BKK", "Bangkok", "Thailand"));
		      airports.add(new Airport("Sao Paulo-Guarulhos", "GRU", "Sao Paulo", "Brazil"));
		      airports.add(new Airport("Toronto Pearson", "YYZ", "Toronto", "Canada"));
		      airports.add(new Airport("Amsterdam Schiphol", "AMS", "Amsterdam", "Netherlands"));
		      airports.add(new Airport("Ataturk", "IST", "Istanbul", "Turkey"));
		      airports.add(new Airport("Indira Gandhi", "DEL", "New Delhi", "India"));
		      airports.add(new Airport("Beijing Capital", "PEK", "Beijing", "China"));
		      airports.add(new Airport("Madrid Barajas", "MAD", "Madrid", "Spain"));

			
		      // Add flights to the list
		      // Each flight is created with its departure and arrival airports, duration, and airline
		      flights.add(new Flight(airports.get(0), airports.get(3), 120, "Air France"));
		      flights.add(new Flight(airports.get(3), airports.get(4), 90, "Lufthansa"));
		      flights.add(new Flight(airports.get(0), airports.get(5), 40, "Air France"));
		      flights.add(new Flight(airports.get(3), airports.get(4), 130, "EasyJet"));
		      flights.add(new Flight(airports.get(2), airports.get(3), 150, "Olympic"));
		      flights.add(new Flight(airports.get(3), airports.get(8), 120, "Aegean"));
		      flights.add(new Flight(airports.get(1), airports.get(10), 110, "Alitalia"));
		      flights.add(new Flight(airports.get(2), airports.get(3), 30, "Aegean"));
		      flights.add(new Flight(airports.get(6), airports.get(7), 300, "British Airways"));
		      flights.add(new Flight(airports.get(8), airports.get(11), 330, "Singapore Airlines"));
		      flights.add(new Flight(airports.get(10), airports.get(15), 360, "Air France"));
		      flights.add(new Flight(airports.get(12), airports.get(14), 390, "Emirates"));
		      flights.add(new Flight(airports.get(13), airports.get(19), 420, "Qantas"));
		      flights.add(new Flight(airports.get(8), airports.get(18), 450, "Aeroflot"));
		      flights.add(new Flight(airports.get(18), airports.get(20), 480, "Thai Airways"));
		      flights.add(new Flight(airports.get(7), airports.get(21), 510, "Air Canada"));
		      flights.add(new Flight(airports.get(9), airports.get(23), 540, "Turkish Airlines"));
		      flights.add(new Flight(airports.get(21), airports.get(14), 570, "Air China"));
			
		      // Create a CentralRegistry object to manage airports and flights
		      CentralRegistry CentralRegistry = new CentralRegistry();
			
		      // Add all airports to the CentralRegistry
			  for (Airport airport : airports) {
		            CentralRegistry.addAirport(airport);
		      }

		        // Add flights to CentralRegistry using enhanced for loop
		        for (Flight flight : flights) {
		            CentralRegistry.addFlight(flight);
		      }
		
		        
		        IntroGui gui = new IntroGui(CentralRegistry);

		}
}


		


	


