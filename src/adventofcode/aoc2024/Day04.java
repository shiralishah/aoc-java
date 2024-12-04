package adventofcode.aoc2024;

import adventofcode.InputReader;

import java.util.List;

public class Day04 {

    public static void main(String[] args) {
        Day04 solution = new Day04();
        String fileSource = "input.txt";
        System.out.println(solution.part1(fileSource));
        System.out.println(solution.part2(fileSource));

    }

    public int part1(String fileSource) {
        List<String> lines = InputReader.readLines(fileSource, this.getClass()).toList();
        int[][] directions = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};
        int n = lines.size();
        int m = lines.get(0).length();
        char[][] grid = new char[n][m];
        for(int i = 0; i < n; i++) {
            grid[i] = lines.get(i).toCharArray();
        }
        int count = 0;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'X') {
                    for (int[] d: directions) {
                        if (isWord(grid, d, j, i, n, m)) count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean isWord(char[][] grid, int[] dir, int x, int y, int n, int m) {
        String XMAS = "XMAS";
        for(int i = 0; i < XMAS.length(); i++) {
            int checkX = x+i*dir[0];
            int checkY = y+i*dir[1];
            if (checkX < 0 || checkY < 0 || checkY >= n || checkX >= m) return false;
            if (grid[checkY][checkX] != XMAS.charAt(i)) return false;
        }
        return true;
    }

    public int part2(String fileSource) {
        List<String> lines = InputReader.readLines(fileSource, this.getClass()).toList();
        int n = lines.size();
        int m = lines.get(0).length();
        char[][] grid = new char[n][m];
        for(int i = 0; i < n; i++) {
            grid[i] = lines.get(i).toCharArray();
        }
        int count = 0;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'A') {
                    if(checkDiagonal(grid, j, i, n, m, new int[]{1, 1}) &&
                            checkDiagonal(grid, j, i, n, m, new int[]{1, -1})) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean checkDiagonal(char[][] grid, int x, int y, int n, int m, int[] dir) {
        if (x - dir[0] < 0 || y - dir[1] < 0 || x + dir[0] >= m || y + dir[1] >= n) return false;
        else if (grid[y-dir[1]][x-dir[0]] == 'M' && grid[y+dir[1]][x+dir[0]] == 'S') return true;
        else return grid[y - dir[1]][x - dir[0]] == 'S' && grid[y + dir[1]][x + dir[0]] == 'M';
    }

}