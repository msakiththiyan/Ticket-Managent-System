public class Ticket {
    private int ticketID;
    private String eventName;
    private double ticketPrice;

    public Ticket(double ticketPrice, String eventName, int ticketID) {
        this.ticketPrice = ticketPrice;
        this.eventName = eventName;
        this.ticketID = ticketID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" + "Ticket ID= " + ticketID + ", Event Name= '" + eventName + '\'' + ", Ticket Price= Rs." + ticketPrice + '}';
    }
}
