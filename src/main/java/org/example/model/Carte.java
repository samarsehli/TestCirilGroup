package org.example.model;

public class Carte {

    private char[][] grid;
    private int width;
    private int hight;

    public Carte(char[][] grid, int width, int hight) {
        this.grid = grid;
        this.width = width;
        this.hight = hight;
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHight() {
        return hight;
    }

    public char getCase(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < hight) {
            return grid[y][x];
        }
        return '#';
    }

}
