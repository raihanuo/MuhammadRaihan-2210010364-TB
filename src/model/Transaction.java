package model;

public class Transaction {
    private int transactionId;
    private int ticketId;
    private String flightCode;
    private String transactionDate;

    public Transaction(int transactionId, int ticketId, String flightCode, String transactionDate) {
        this.transactionId = transactionId;
        this.ticketId = ticketId;
        this.flightCode = flightCode;
        this.transactionDate = transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
