package com.oop.coursework.ticketSystem.service;

import com.oop.coursework.ticketSystem.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class TicketVendorService {
    private TicketPool ticketPool;
    private Configuration currentConfiguration;
    private List<Ticket> soldTickets = new ArrayList<>();

    public Configuration getCurrentConfiguration() {
        return currentConfiguration != null ? currentConfiguration : new Configuration();
    }

    public void ConfigureSystem(Configuration config){
        this.currentConfiguration = config;
        this.ticketPool = new TicketPool(config.getTicketPoolCapacity());
    }

    public void runTicketSystem(){
        if (currentConfiguration == null){
            currentConfiguration = new Configuration();
        }

        ticketPool = new TicketPool(currentConfiguration.getTicketPoolCapacity());
        ExecutorService executorService = Executors.newFixedThreadPool(
                currentConfiguration.getVendorCount() + currentConfiguration.getCustomerCount()
        );

        //Create and Starting vendor Threads
        for(int i = 0; i < currentConfiguration.getVendorCount(); i++){
            Vendor vendor = new Vendor(ticketPool, 20, currentConfiguration.getVendorReleaseRate());
            executorService.submit(vendor);
        }

        //Create and Starting customer Threads
        List<Customer> customers = new ArrayList<>();
        for(int i = 0; i < currentConfiguration.getVendorCount(); i++){
            Customer customer = new Customer(ticketPool, currentConfiguration.getCustomerRetrievalRate(), 5){
                @Override
                public void run() {
                    for (int j = 0; j < currentConfiguration.getCustomerCount(); j++) {
                        Ticket ticket = ticketPool.buyTicket();
                        soldTickets.add(ticket);
                        System.out.println("Ticket bought by " + Thread.currentThread().getName() + ". Ticket is " + ticket);

                        try {
                            Thread.sleep(currentConfiguration.getCustomerRetrievalRate() * 1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            //logger.error("Customer Thread was Interrupted.", e);
                            break;
                        }
                    }
                }
            };
            customers.add(customer);
            executorService.submit(customer);
        }

        //Shutdown and wait for completion
        executorService.shutdown();
        try{
            executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public List<Ticket> getSoldTickets() {
        return new ArrayList<>(soldTickets);
    }



}
