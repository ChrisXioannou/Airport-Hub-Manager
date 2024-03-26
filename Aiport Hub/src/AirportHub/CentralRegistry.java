package AirportHub;

import java.util.ArrayList;

public class CentralRegistry {

    private ArrayList<Flight> all_flights = new ArrayList<>();
    private ArrayList<Airport> all_airports = new ArrayList<>();

    // Returns an Airport object matching the provided city name, or null if not found
    Airport getAirport(String cityName) {
        for (Airport anAirport : all_airports) {
            if (anAirport.getCity().equals(cityName)) {
                return anAirport;
            }
        }
        return null;
    }

    // Adds an airport to the list of all airports
    public void addAirport(Airport anAirport) {
        all_airports.add(anAirport);
    }

    // Returns a string listing all direct flights between two specified airports
    public String getDirectFlightsDetailsAsString(Airport departure, Airport arrival) {
        StringBuilder details = new StringBuilder();
        for (Flight flight : all_flights) {
            if ((flight.getAirportA().equals(departure) && flight.getAirportB().equals(arrival)) ||
                (flight.getAirportB().equals(departure) && flight.getAirportA().equals(arrival))) {
                details.append(flight.toString()).append("\n");
            }
        }
        return details.toString();
    }

    // Returns a string listing all indirect flights between two specified airports
    public String getInDirectFlightsDetailsAsString(Airport departure, Airport arrival) {
        StringBuilder details = new StringBuilder();
        for (Airport intermediate : all_airports) {
            if (departure.isDirectlyConnectedTo(intermediate) && intermediate.isDirectlyConnectedTo(arrival)) {
                details.append(departure.getCity()).append(" -> ");
                details.append(intermediate.getCity()).append(" -> ");
                details.append(arrival.getCity()).append("\n");
            }
        }
        return details.toString();
    }

    // Adds a flight to the list of all flights and updates connections and companies for the involved airports
    public void addFlight(Flight aFlight) {
        all_flights.add(aFlight);

    	aFlight.getAirportA().addconnection(aFlight.getAirportB());
		aFlight.getAirportB().addconnection(aFlight.getAirportA());
		
        aFlight.getAirportA().addcompany(aFlight.getCompanyName());
        aFlight.getAirportB().addcompany(aFlight.getCompanyName());  
    }

    // Checks if an airport exists within the registry by city name
    public boolean airportFinder(String name) {
        for (Airport anAirport : all_airports) {
            if (anAirport.getCity().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Finds and returns the airport with the most connections
    public Airport getLargestHub() {
        int max = 0;
        Airport largestHub = null;

        for (Airport anAirport : all_airports) {
            int pl = 0;
            for (Flight aFlight : all_flights) {
                if (aFlight.getAirportA() == anAirport || aFlight.getAirportB() == anAirport) {
                    pl++;
                }
            }
            if (pl > max) {
                max = pl;
                largestHub = anAirport;
            }
        }
        return largestHub;
    }

    // Finds and returns the longest flight in terms of duration
    public Flight getLongestFlight() {
        Flight longestFlight = null;
        int maxtime = 140;

        for (Flight aFlight : all_flights) {
            if (aFlight.getDuration() > maxtime) {
                maxtime = aFlight.getDuration();
                longestFlight = aFlight;
            }
        }
        return longestFlight;
    }

    // Getter for the list of all airports
    public ArrayList<Airport> getAllAirports() {
        return all_airports;
    }

    // Getter for the list of all flights
    public ArrayList<Flight> getAllFlights() {
        return all_flights;
    }
}
