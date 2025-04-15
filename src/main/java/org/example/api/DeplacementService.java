package org.example.api;

import org.example.model.Carte;
import org.example.model.SimulationResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class DeplacementService {

    private Carte lireCarte(String pathFile) throws IOException {
        List<String> lignes = Files.readAllLines(Path.of(pathFile), StandardCharsets.UTF_8);
        if (lignes.isEmpty()) {
            throw new IOException("Le fichier de la carte est vide.");
        }
        int hauteur = lignes.size();
        int largeur = lignes.get(0).length();
        char[][] grille = new char[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            grille[i] = lignes.get(i).toCharArray();
        }
        return new Carte(grille, largeur, hauteur);
    }

    public SimulationResponse simulerDeplacement(String cartePath, int initialX, int initialY, String deplacements) throws IOException {
        SimulationResponse simulationResponse = null;
        char[][] carte = lireCarte(cartePath).getGrid();
        int currentX = initialX;
        int currentY = initialY;

        for (char deplacement : deplacements.toCharArray()) {
            int nextX = currentX;
            int nextY = currentY;

            switch (deplacement) {
                case 'N':
                    nextY--;
                    break;
                case 'S':
                    nextY++;
                    break;
                case 'E':
                    nextX++;
                    break;
                case 'O':
                    nextX--;
                    break;
            }

            if (nextX < 0 || nextX >= carte[0].length || nextY < 0 || nextY >= carte.length) {
                simulationResponse = new SimulationResponse(currentX, currentY, "Le personnage ne peut pas sortir de la carte.");
            }

            if (carte[nextY][nextX] == '#') {
                simulationResponse = new SimulationResponse(currentX, currentY, "Le personnage ne peut pas aller sur les cases occupées par les bois impénétrables.");
            } else {
                currentX = nextX;
                currentY = nextY;
                simulationResponse = new SimulationResponse(currentX, currentY, "Simulation terminée.");
            }

        }

        return simulationResponse;
    }
}