import java.io.Serializable;

public class Configuration implements Serializable {

    // Serialization version UID for compatibility
    private static final long serialVersionUID = 1L;

    // Default values
    private static final int defaultTicketPoolCapacity = 10;
    private static final int defaultVendorCount = 5;
    private static final int defaultCustomerCount = 5;
    private static final int defaultVendorReleaseRate = 5;
    private static final int defaultCustomerRetrievalRate = 5;

    // Configuration parameters
    private int ticketPoolCapacity;
    private int vendorCount;
    private int customerCount;
    private int vendorReleaseRate;
    private int customerRetrievalRate;

    // Default Constructor
    public Configuration() {
        this.ticketPoolCapacity = defaultTicketPoolCapacity;
        this.vendorCount = defaultVendorCount;
        this.customerCount = defaultCustomerCount;
        this.vendorReleaseRate = defaultVendorReleaseRate;
        this.customerRetrievalRate = defaultCustomerRetrievalRate;
    }

    // Getters and Setters
    public int getTicketPoolCapacity() {
        return ticketPoolCapacity;
    }

    public void setTicketPoolCapacity(int ticketPoolCapacity) {
        this.ticketPoolCapacity = ticketPoolCapacity;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public int getVendorReleaseRate() {
        return vendorReleaseRate;
    }

    public void setVendorReleaseRate(int vendorReleaseRate) {
        this.vendorReleaseRate = vendorReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // ToString method for easy printing of configuration
    @Override
    public String toString() {
        return "Configuration{" + "Ticket Pool Capacity=" + ticketPoolCapacity + ", Vendor Count=" + vendorCount + ", Customer Count=" + customerCount + ", Vendor Release Rate=" + vendorReleaseRate + " seconds" + ", Customer Retrieval Rate=" + customerRetrievalRate + " seconds" + '}';
    }
}
