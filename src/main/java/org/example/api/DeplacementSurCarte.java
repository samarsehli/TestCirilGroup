package org.example.api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class DeplacementSurCarte {

    public DeplacementSurCarte() {
    }

    private static char[][] lireCarte(String pathFile) throws IOException {
        List<String> readLine;
        try (BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile), StandardCharsets.UTF_8))) {
            readLine = read.lines().toList();
        }

        if (readLine.isEmpty()) {
            throw new IOException("Le fichier de la carte est vide.");
        }

        int axeY = readLine.size();
        int axeX = readLine.get(0).length();
        char[][] carte = new char[axeY][axeX];
        for (int i = 0; i < axeY; i++) {
            if (readLine.get(i).length() != axeX) {
                throw new IOException("Les lignes de la carte n'ont pas toutes la même longueur.");
            }
            carte[i] = readLine.get(i).toCharArray();
        }
        return carte;
    }

    public static void simulation(String pathFileCarte) {
        try {
            char[][] carte = lireCarte(pathFileCarte);
            int axeX = carte[0].length;
            int axeY = carte.length;

            Scanner scanner = new Scanner(System.in);

            System.out.println("Veuillez entrer la position initiale du personnage (x,y) : ");
            String positionInitialeStr = scanner.nextLine();

            int[] position = parseCoordinates(positionInitialeStr, axeX, axeY, scanner);
            if (position == null) {
                return;
            }
            int currentX = position[0];
            int currentY = position[1];

            System.out.println("Veuillez entrer la séquence des déplacements (N, S, E, O) : ");
            String deplacements = scanner.nextLine().toUpperCase();

            System.out.println("Position initiale du personnage : (" + currentX + "," + currentY + ")");
            System.out.println("Déplacements à effectuer : " + deplacements);

            for (char direction : deplacements.toCharArray()) {
                int newX = currentX;
                int newY = currentY;

                switch (direction) {
                    case 'N' -> newY--;
                    case 'S' -> newY++;
                    case 'E' -> newX++;
                    case 'O' -> newX--;
                    default -> System.out.println("Direction invalide : " + direction);
                }

                if (newX >= 0 && newX < axeX && newY >= 0 && newY < axeY) {
                    if (carte[newY][newX] != '#') {
                        currentX = newX;
                        currentY = newY;
                        System.out.println("Déplacement vers " + direction + ". Nouvelle position : (" + currentX + "," + currentY + ")");
                    } else {
                        System.out.println("Impossible d'aller sur les bois impénétrables à la direction : " + direction + ". Le personnage reste en (" + currentX + "," + currentY + ").");
                    }
                } else {
                    System.out.println("Tentative de sortie de la carte à la direction : " + direction + ". Le personnage reste en (" + currentX + "," + currentY + ").");
                }
            }

            System.out.println("\nPosition finale du personnage : (" + currentX + "," + currentY + ")");
            scanner.close();

        } catch (IOException e) {
            System.err.println("Erreur lors de la simulation : " + e.getMessage());
        }
    }

    private static int[] parseCoordinates(String positionStr, int axeX, int axeY, Scanner scanner) {
        String[] coords = positionStr.split(",");
        if (coords.length == 2) {
            try {
                int x = Integer.parseInt(coords[0].trim());
                int y = Integer.parseInt(coords[1].trim());
                if (x < 0 || x >= axeX || y < 0 || y >= axeY) {
                    System.err.println("Erreur: Les coordonnées initiales sont en dehors des limites de la carte.");
                    scanner.close();
                    return null;
                }
                return new int[]{x, y};
            } catch (NumberFormatException e) {
                System.err.println("Erreur: Les coordonnées initiales doivent être des nombres entiers.");
                scanner.close();
                return null;
            }
        } else {
            System.err.println("Erreur: La position initiale doit être au format 'x,y'.");
            scanner.close();
            return null;
        }
    }

}