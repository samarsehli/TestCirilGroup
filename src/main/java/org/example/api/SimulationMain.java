package org.example.api;

public class SimulationMain {

    private static DeplacementSurCarte deplacementSurCarte;

    public SimulationMain(DeplacementSurCarte deplacementSurCarte) {
        this.deplacementSurCarte = deplacementSurCarte;
    }

    public static void main(String[] args) {
        String fichierCarteTest1 = "carte v2.txt";
        System.out.println("*************************************************");
        System.out.println("*************************************************");
        deplacementSurCarte.simulation(fichierCarteTest1);
    }
}
