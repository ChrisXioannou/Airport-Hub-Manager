package AirportHub;

public class Flight {
    private Airport airporta;
    private Airport airportb;
    private int duration;
    private String companyname;

    // Constructor: Initializes a new flight with specified details
    Flight(Airport text1, Airport text2, int int1, String text3) {
        airporta = text1;
        airportb = text2;
        duration = int1;
        companyname = text3;    
    }

    // Converts flight details to a string format
    @Override
    public String toString() {
        // Assuming that Airport class has a getCity method to retrieve the city name
        return "Flight operated by " + companyname + ", duration: " + duration + " minutes. From " +
                airporta.getCity() + " to " + airportb.getCity() + ".";
    }

    // Getters for accessing private fields
    public Airport getAirportA() {
        return airporta;
    }

    public Airport getAirportB() {
        return airportb;
    }

    public int getDuration() {
        return duration;
    }

    public String getCompanyName() {
        return companyname;
    }
}
