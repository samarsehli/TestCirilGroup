package org.example.model;

public class SimulationRequest {
    private String cartePath;
    private int initialX;
    private int initialY;
    private String deplacements;


    public String getCartePath() {
        return cartePath;
    }

    public void setCartePath(String cartePath) {
        this.cartePath = cartePath;
    }

    public int getInitialX() {
        return initialX;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    public String getDeplacements() {
        return deplacements;
    }

    public void setDeplacements(String deplacements) {
        this.deplacements = deplacements;
    }
}