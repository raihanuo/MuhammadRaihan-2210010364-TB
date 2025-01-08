package dao;

import model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private final Connection connection;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTransaction(Transaction transaction) throws Exception {
        String query = "INSERT INTO transactions (ticket_id, flight_code, transaction_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transaction.getTicketId());
            statement.setString(2, transaction.getFlightCode());
            statement.setString(3, transaction.getTransactionDate());
            statement.executeUpdate();
        }
    }

    public List<Transaction> getAllTransactions() throws Exception {
        String query = "SELECT * FROM transactions";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            List<Transaction> transactions = new ArrayList<>();
            while (resultSet.next()) {
                transactions.add(new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getInt("ticket_id"),
                        resultSet.getString("flight_code"),
                        resultSet.getString("transaction_date")
                ));
            }
            return transactions;
        }
    }

    public void updateTransaction(Transaction transaction) throws Exception {
        String query = "UPDATE transactions SET ticket_id = ?, flight_code = ?, transaction_date = ? WHERE transaction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transaction.getTicketId());
            statement.setString(2, transaction.getFlightCode());
            statement.setString(3, transaction.getTransactionDate());
            statement.setInt(4, transaction.getTransactionId());
            statement.executeUpdate();
        }
    }

    public void deleteTransaction(int transactionId) throws Exception {
        String query = "DELETE FROM transactions WHERE transaction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, transactionId);
            statement.executeUpdate();
        }
    }
}
