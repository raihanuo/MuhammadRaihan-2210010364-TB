package dao;

import model.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    private final Connection connection;

    public FlightDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<Flight> getAllFlightCodes() throws Exception {
        String query = "SELECT flight_code FROM flights";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()) {
                flights.add(new Flight(
                        resultSet.getString("flight_code"),
                        null,                              
                        null,                              
                        null                               
                ));
            }
            return flights;
        }
    }


    public void addFlight(Flight flight) throws Exception {
        String query = "INSERT INTO flights (flight_code, flight_name, departure, arrival) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flight.getFlightCode());
            statement.setString(2, flight.getFlightName());
            statement.setString(3, flight.getDeparture());
            statement.setString(4, flight.getArrival());
            statement.executeUpdate();
        }
    }

    public List<Flight> getAllFlights() throws Exception {
        String query = "SELECT * FROM flights";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()) {
                flights.add(new Flight(
                        resultSet.getString("flight_code"),
                        resultSet.getString("flight_name"),
                        resultSet.getString("departure"),
                        resultSet.getString("arrival")
                ));
            }
            return flights;
        }
    }

    public void updateFlight(Flight flight) throws Exception {
        String query = "UPDATE flights SET flight_name = ?, departure = ?, arrival = ? WHERE flight_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flight.getFlightName());
            statement.setString(2, flight.getDeparture());
            statement.setString(3, flight.getArrival());
            statement.setString(4, flight.getFlightCode());
            statement.executeUpdate();
        }
    }

    public void deleteFlight(String flightCode) throws Exception {
        String query = "DELETE FROM flights WHERE flight_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, flightCode);
            statement.executeUpdate();
        }
    }
}
