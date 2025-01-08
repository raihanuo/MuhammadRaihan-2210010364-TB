package util;

import dao.FlightDAO;
import dao.TicketDAO;
import dao.TransactionDAO;
import model.Flight;
import model.Transaction;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    private final FlightDAO flightDAO;
    private final TicketDAO ticketDAO;
    private final TransactionDAO transactionDAO;

    public ReportGenerator() {
        try {
            Connection connection = Database.getConnection();
            this.flightDAO = new FlightDAO(connection);
            this.ticketDAO = new TicketDAO(connection);
            this.transactionDAO = new TransactionDAO(connection);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize DAOs: " + e.getMessage());
        }
    }

    public void generateFlightReport(String filePath) throws Exception {
        List<Flight> flights = flightDAO.getAllFlights();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Kode Penerbangan,Nama Penerbangan,Keberangkatan,Tujuan");

            for (Flight flight : flights) {
                writer.printf("%s,%s,%s,%s%n",
                        flight.getFlightCode(),
                        flight.getFlightName(),
                        flight.getDeparture(),
                        flight.getArrival());
            }
        }
    }

    public void generateTicketReport(String filePath) {
        try {
            List<Map<String, Object>> tickets = ticketDAO.getAllTickets();

            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.println("ID Tiket,Nama Penumpang,Kode Penerbangan,Jumlah Tiket,Harga");

                for (Map<String, Object> ticket : tickets) {
                    writer.printf("%d,%s,%s,%d,%d%n",
                            ticket.get("ticket_id"),
                            ticket.get("customer_name"),
                            ticket.get("flight_code"),
                            ticket.get("ticket_count"),
                            ticket.get("price"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void generateTransactionReport(String filePath) throws Exception {
        List<Transaction> transactions = transactionDAO.getAllTransactions();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ID Transaksi,ID Tiket,Kode Penerbangan,Tanggal Transaksi");

            for (Transaction transaction : transactions) {
                writer.printf("%d,%d,%s,%s%n",
                        transaction.getTransactionId(),
                        transaction.getTicketId(),
                        transaction.getFlightCode(),
                        transaction.getTransactionDate());
            }
        }
    }
}
