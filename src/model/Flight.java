package model;

public class Flight {
    private String flightCode;
    private String flightName;
    private String departure;
    private String arrival;

    public Flight(String flightCode, String flightName, String departure, String arrival) {
        this.flightCode = flightCode;
        this.flightName = flightName;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
