package org.example.model;

public class SimulationResponse {
    private int finalX;
    private int finalY;
    private String message;

    public SimulationResponse(int finalX, int finalY, String message) {
        this.finalX = finalX;
        this.finalY = finalY;
        this.message = message;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public String getMessage() {
        return message;
    }
}
