package adventofcode.aoc2024;

import adventofcode.InputReader;

public class Day02 {

    public static void main(String[] args) {
        Day02 solution = new Day02();
        String fileSource = "input.txt";
        System.out.println(solution.part1(fileSource));
        System.out.println(solution.part2(fileSource));

    }

    public int part1(String fileSource) {
        return InputReader.readLines(fileSource, this.getClass()).map(s -> {
            String[] levels = s.split("\s+");
            if (checkLevels(levels, 1, false)) return 1;
            else if (checkLevels(levels, -1, false)) return 1;
            else return 0;
        }).reduce(0, Integer::sum);
    }

    public int part2(String fileSource) {
        return InputReader.readLines(fileSource, this.getClass()).map(s -> {
            String[] levels = s.split("\s+");
            if (checkLevels(levels, 1, true)) return 1;
            else if (checkLevels(levels, -1, true)) return 1;
            else return 0;
        }).reduce(0, Integer::sum);
    }

    private boolean checkLevels(String[] levels, int sign, boolean useDampener) {
        if (levels.length <= 1) return levels.length != 0;
        if (levels.length == 2 && useDampener) return true;
        for (int i = 1; i < levels.length; i++) {
            int curr = Integer.parseInt(levels[i]);
            int prev = Integer.parseInt(levels[i-1]);
            int diff = curr - prev;
            int diffAbs = Math.abs(diff);
            if (diff == 0 || !(diff/diffAbs == sign && diffAbs <= 3)) {
                if (!useDampener) return false;
                else {
                    return checkLevelsInd(levels, sign, i-1) || checkLevelsInd(levels, sign, i);
                }
            }
        }
        return true;
    }




    private boolean checkLevelsInd(String[] levels, int sign, int ind) {
        if (levels.length <= 1) return levels.length != 0;
        int i = ind == 0 ? 1 : 0;
        int prev = Integer.parseInt(levels[i]);
        i++;
        while(i < levels.length) {
            if (i == ind) {
                i++;
                continue;
            }
            int curr = Integer.parseInt(levels[i]);
            int diff = curr - prev;
            int diffAbs = Math.abs(diff);
            if (diff == 0 || !(diff/diffAbs == sign && diffAbs <= 3)) {
                return false;
            }
            prev = curr;
            i++;
        }
        return true;
    }
}
