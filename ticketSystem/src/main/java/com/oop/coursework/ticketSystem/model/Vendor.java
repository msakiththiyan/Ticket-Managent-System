package com.oop.coursework.ticketSystem.model;

public class Vendor implements Runnable{
    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;
    //Implement the thread by Implementing runnable interface


    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket(1000, "StageCraft", i);
            ticketPool.addTicket(ticket); //Method in TicketPool to add tickets

            //The ticket release frequency = delay between the tickets.
            try{
                Thread.sleep(ticketReleaseRate * 1000);
            }catch (InterruptedException e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}

