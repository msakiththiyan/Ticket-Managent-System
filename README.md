#  Real-Time Event Ticketing System with Producer-Consumer Pattern (CLI Version)



A *Java-based Ticket Booking System* designed to simulate a real-time ticket management system. The program models concurrent ticket releasing and purchasing processes using multithreading, thread synchronization, and logging.

## Features
- *Thread-safe Ticket Management:* Uses synchronized blocks and ReentrantLocks to ensure thread safety.
- *Dynamic Configuration:* Accepts user-defined or pre-configured settings for simulation parameters.
- *Concurrency Simulation:* Supports multiple vendor and customer threads.

## System Design

### Code Structure



| File/Directory        | Description                             |
|-----------------------|-----------------------------------------|
| *Configuration.java* | Handles the configuration settings.    |
| *Ticket.java*        | Represents a ticket object.            |
| *TicketPool.java*    | Shared resource for managing tickets.  |
| *Vendor.java*        | Implements the vendor thread.          |
| *Customer.java*      | Implements the customer thread.        |
| *CLI.java*          | Main driver program to execute.        |


### Classes
1. *Configuration*  
   Holds system configuration settings such as:
   - Total tickets
   - Ticket release rate
   - Ticket purchase rate
   - Maximum ticket pool capacity
   - Number of vendor and customer threads

 | Parameter              | Description                                  |
 |------------------------|----------------------------------------------|
 | Ticket Release Rate    | Tickets per second released by vendors      |
 | Customer Retrieval Rate| Tickets per second purchased by customers   |
 | Maximum Pool Capacity  | Maximum tickets held in the pool            | |
 | Number of Vendors      | Number of vendor threads in the simulation  |
 | Number of Customers    | Number of customer threads in the simulation|


2. *Ticket*  
   Represents a ticket with the following properties:
   - Unique Ticket ID
   - Event name - (Single event as StageCraft)
   - Price (will be 1000 for StageCraft)

3. *TicketPool*  
   Acts as a shared pool for tickets:
   - Stores tickets in a thread-safe Vector
   - Limits ticket capacity and tracks remaining tickets to add/remove
   - Provides addTicket and removeTicket methods for vendors and customers

4. *Vendor*  
   Vendor threads release tickets to the pool based on the specified release rate.

5. *Customer*  
   Customer threads purchase tickets from the pool based on the specified retrieval rate.

6. *CLI*  
   Handles user interaction, configuration loading, and thread creation.


## How to Use
## Prerequisites
1. *Java Development Kit (JDK)*
   - Ensure JDK 8 or above is installed on your system.
   - Add JAVA_HOME to your system's environment variables if not already set.

2. *Integrated Development Environment (IDE)*
   - Use an IDE like *IntelliJ IDEA* or any other Java I

### Running the Program

1. *Open the Main Class*
   - Locate and open the CLI.java file in your IDE.

2. *Run the Program*
   - Click the *Run* button in your IDE or use the shortcut (e.g., Shift+F10 in IntelliJ IDEA).

3. *Program Execution*
   - Choose to run a default configuration or create a new configuration by following the on-screen instructions.
   - Provide the necessary parameters if creating a new configuration.
   - Observe vendors releasing tickets and customers purchasing them in real-time, with logs showing interactions and transactions.
   - Exit if done.

## Example Workflow

1. Scenario:
   - Vendors release tickets every 5 seconds.
   - Customers purchase tickets every 4 seconds.

2. Steps:
   - Create configuration: Ticket Pool Capacity = 100, Number of Vendors = 20, Number of Customers = 21,  Vendor Ticket Release Rate = 5, Customer Ticket Retrieval Rate = 4.
   - Observe vendors adding tickets and customers purchasing them in real-time.
   - Logs display thread interactions and ticket transactions.

## Troubleshooting
- *Errors on Run:*  
  Verify that the CLI.java file is set as the entry point for the application.
