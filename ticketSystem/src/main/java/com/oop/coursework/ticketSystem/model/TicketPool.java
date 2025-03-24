package com.oop.coursework.ticketSystem.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.ticketQueue = new LinkedList<>();
    }

    //Add Ticket for vendors to add tickets (Called by Vendor)
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace(); //In CLI, this is used to handle interruptedException
                throw new RuntimeException(e.getMessage());
            } //finaly() - to close a db connection or close file as it is guaranteed execution.
        }

        //Vendor adds ticket
        this.ticketQueue.add(ticket); //Adding the ticket into the Queue
        notifyAll(); //Notify all the waiting threads
        //printing a message to let a log to appear into the thread.
        System.out.print(Thread.currentThread().getName() + " added ticket to the pool. Current capacity: " + ticketQueue.size());
    }

    //Buy ticket for customers to buy and remove tickets (Called by Customer)
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e){
                throw new RuntimeException(e.getMessage());
            }
        }

        Ticket ticket = ticketQueue.poll(); //Remove ticket from Queue
        notifyAll(); //Notify all waiting threads

        System.out.println(Thread.currentThread().getName() + " bought a ticket from the pool. Current capacity: " + ticketQueue.size());
        return ticket;
    }
}

