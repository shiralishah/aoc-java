package adventofcode.aoc2024;

import adventofcode.InputReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    private Pattern MulPattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");

    public static void main(String[] args) {
        Day03 solution = new Day03();
        String fileSource = "input.txt";
        System.out.println(solution.part1(fileSource));
        System.out.println(solution.part2(fileSource));

    }

    public long part1(String fileSource) {
        String input = InputReader.readString(fileSource, this.getClass());
        return getProducts(input);
    }

    public long part2(String fileSource) {
        String input = InputReader.readString(fileSource, this.getClass());
        Matcher doMatcher = Pattern.compile("do\\(\\)").matcher(input);
        Matcher dontMatcher = Pattern.compile("don't\\(\\)").matcher(input);
        int start = 0;
        long total = 0;
        int end;
        while (true) {
            if(!dontMatcher.find(start)) {
                total += getProducts(input.substring(start));
                break;
            } else {
                end = dontMatcher.start();
                total += getProducts(input.substring(start, end));
                if (doMatcher.find(dontMatcher.end())) {
                    start = doMatcher.end();
                } else {
                    break;
                }
            }

        }
        return total;
    }

    private long getProducts(String input) {
        Matcher matcher = MulPattern.matcher(input);
        long total = 0;
        while(matcher.find()) {
            String group = matcher.group();
            String[] nums = group.substring(4, group.length()-1).split(",");
            total += (long) Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
        }
        return total;
    }

}