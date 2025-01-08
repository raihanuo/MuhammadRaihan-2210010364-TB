package model;

public class Ticket {
    private String customerName;
    private String flightCode;
    private int ticketCount;
    private double price;

    public Ticket(String customerName, String flightCode, int ticketCount, double price) {
        this.customerName = customerName;
        this.flightCode = flightCode;
        this.ticketCount = ticketCount;
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
