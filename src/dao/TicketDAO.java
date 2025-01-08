package dao;

import model.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class TicketDAO {
    private final Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<Integer> getAllTicketIds() throws Exception {
        String query = "SELECT ticket_id FROM tickets";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            List<Integer> ticketIds = new ArrayList<>();
            while (resultSet.next()) {
                ticketIds.add(resultSet.getInt("ticket_id"));
            }
            return ticketIds;
        }
    }
    
    public void addTicket(Ticket ticket) throws Exception {
        String ticketQuery = "INSERT INTO tickets (customer_name, flight_code, ticket_count, price) VALUES (?, ?, ?, ?)";
        String transactionQuery = "INSERT INTO transactions (ticket_id, flight_code, transaction_date) VALUES (?, ?, ?)";

        try (PreparedStatement ticketStmt = connection.prepareStatement(ticketQuery, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement transactionStmt = connection.prepareStatement(transactionQuery)) {

            ticketStmt.setString(1, ticket.getCustomerName());
            ticketStmt.setString(2, ticket.getFlightCode());
            ticketStmt.setInt(3, ticket.getTicketCount());
            ticketStmt.setDouble(4, ticket.getPrice());
            ticketStmt.executeUpdate();

            ResultSet generatedKeys = ticketStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedTicketId = generatedKeys.getInt(1);
                transactionStmt.setInt(1, generatedTicketId);
                transactionStmt.setString(2, ticket.getFlightCode());
                transactionStmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                transactionStmt.executeUpdate();
            }
        }
    }

    public List<Map<String, Object>> getAllTickets() throws Exception {
        String query = "SELECT * FROM tickets";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            List<Map<String, Object>> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> ticketData = new HashMap<>();
                ticketData.put("ticket_id", resultSet.getInt("ticket_id"));
                ticketData.put("customer_name", resultSet.getString("customer_name"));
                ticketData.put("flight_code", resultSet.getString("flight_code"));
                ticketData.put("ticket_count", resultSet.getInt("ticket_count"));
                ticketData.put("price", resultSet.getInt("price"));
                tickets.add(ticketData);
            }
            return tickets;
        }
    }

    public void updateTicket(int id, Ticket ticket) throws Exception {
        String query = "UPDATE tickets SET customer_name = ?, flight_code = ?, ticket_count = ?, price = ? WHERE ticket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticket.getCustomerName());
            statement.setString(2, ticket.getFlightCode());
            statement.setInt(3, ticket.getTicketCount());
            statement.setDouble(4, ticket.getPrice());
            statement.setInt(5, id);
            statement.executeUpdate();
        }
    }


    public void deleteTicket(int ticket_id) throws Exception {
        String query = "DELETE FROM tickets WHERE ticket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ticket_id);
            statement.executeUpdate();
        }
    }
}
