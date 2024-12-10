import java.io.*;
import java.util.Scanner;

public class CLI {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Configuration config = loadConfiguration();

        // Main menu loop
        while (true) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    config = configureSystem();
                    saveConfiguration(config);
                    break;
                case 2:
                    runTicketSystem(config);
                    break;
                case 0:
                    System.out.println("Exiting the Ticket Vendor System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n-*-*-*- Ticket Vendor System -*-*-*-");
        System.out.println("1. Configure System");
        System.out.println("2. Run Ticket System");
        System.out.println("0. Exit");
        System.out.print("\nEnter your choice: ");
    }

    private static int getUserChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static Configuration configureSystem() {
        Configuration config = new Configuration();

        // Ticket Pool Capacity
        while (true) {
            System.out.print("Enter Ticket Pool Capacity: ");
            try {
                int capacity = Integer.parseInt(scanner.nextLine());
                if (capacity > 0) {
                    config.setTicketPoolCapacity(capacity);
                    break;
                }
                System.out.println("Capacity must be a Positive Value");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Number of Vendors
        while (true) {
            System.out.print("Enter Number of Vendors: ");
            try {
                int vendorCount = Integer.parseInt(scanner.nextLine());
                if (vendorCount > 0) {
                    config.setVendorCount(vendorCount);
                    break;
                }
                System.out.println("Vendor count must be a Positive Value");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Number of Customers
        while (true) {
            System.out.print("Enter Number of Customers: ");
            try {
                int customerCount = Integer.parseInt(scanner.nextLine());
                if (customerCount > 0) {
                    config.setCustomerCount(customerCount);
                    break;
                }
                System.out.println("Customer count must be a Positive Value");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Vendor Ticket Release Rate
        while (true) {
            System.out.print("Enter Vendor Ticket Release Rate (seconds, 1-10): ");
            try {
                int releaseRate = Integer.parseInt(scanner.nextLine());
                if (releaseRate > 0 && releaseRate <= 10) {
                    config.setVendorReleaseRate(releaseRate);
                    break;
                }
                System.out.println("Release rate must be between 1 and 10 seconds.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Customer Retrieval Rate
        while (true) {
            System.out.print("Enter Customer Retrieval Rate (seconds, 1-10): ");
            try {
                int retrievalRate = Integer.parseInt(scanner.nextLine());
                if (retrievalRate > 0 && retrievalRate <= 10) {
                    config.setCustomerRetrievalRate(retrievalRate);
                    break;
                }
                System.out.println("Retrieval rate must be between 1 and 10 seconds.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        return config;
    }

    private static void runTicketSystem(Configuration config) {
        System.out.println("\nStarting Ticket Vendor System with the following configuration:");
        System.out.println(config);

        TicketPool ticketPool = new TicketPool(config.getTicketPoolCapacity());

        // Create and start Vendor threads
        Vendor[] vendors = new Vendor[config.getVendorCount()];
        Thread[] vendorThreads = new Thread[config.getVendorCount()];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool, 20, config.getVendorReleaseRate());
            vendorThreads[i] = new Thread(vendors[i], "Vendor : " + i);
            vendorThreads[i].start();
        }

        // Create and start Customer threads
        Customer[] customers = new Customer[config.getCustomerCount()];
        Thread[] customerThreads = new Thread[config.getCustomerCount()];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, config.getCustomerRetrievalRate(), 5);
            customerThreads[i] = new Thread(customers[i], "Customer : " + i);
            customerThreads[i].start();
        }

        // Wait for all threads to complete
        try {
            for (Thread vendorThread : vendorThreads) {
                vendorThread.join();
            }
            for (Thread customerThread : customerThreads) {
                customerThread.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Threads were interrupted: " + e.getMessage());
        }

        System.out.println("\nTicket Vendor System has completed.");
    }

    private static Configuration loadConfiguration() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ticket_system_config.ser"))) {
            return (Configuration) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous configuration found. Using default settings.");
            return new Configuration();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return new Configuration();
        }
    }

    private static void saveConfiguration(Configuration config) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ticket_system_config.ser"))) {
            oos.writeObject(config);
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }
}