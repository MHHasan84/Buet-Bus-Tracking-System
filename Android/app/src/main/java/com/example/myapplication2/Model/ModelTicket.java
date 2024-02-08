package com.example.myapplication2.Model;

public class ModelTicket {
    private String ticketId;
    private int numberOfTickets;
    private boolean validity;

    public ModelTicket(){

    }

    public ModelTicket(String ticketId, int numberOfTickets, boolean validity) {
        this.ticketId = ticketId;
        this.numberOfTickets = numberOfTickets;
        this.validity = validity;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }
}
