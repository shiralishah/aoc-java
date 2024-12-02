package adventofcode.aoc2024;

import java.util.*;

import adventofcode.InputReader;

public class Day01 {
    public static void main(String[] args) {
        Day01 solution = new Day01();
        String fileSource = "input.txt";
        System.out.println(solution.part1(fileSource));
        System.out.println(solution.part2(fileSource));

    }

    public long part1(String fileSource) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        InputReader.readLines(fileSource, this.getClass()).forEach(s -> {
           String[] sections = s.split("\s+");
           left.add(Integer.parseInt(sections[0]));
           right.add(Integer.parseInt(sections[1]));
        });

        int n = left.size();

        PriorityQueue<Integer> leftPQ = new PriorityQueue<>(left);
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>(right);
        long totalDistance = 0;
        for(int i = 0; i < n; i++) {
            totalDistance += (Math.abs(leftPQ.poll() - rightPQ.poll()));
        }

        return totalDistance;
    }

    public long part2(String fileSource) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        InputReader.readLines(fileSource, this.getClass()).forEach(s -> {
            String[] sections = s.split("\s+");
            // left
            int num = Integer.parseInt(sections[0]);
            int count = left.getOrDefault(num, 0);
            left.put(num, count+1);
            //right
            num = Integer.parseInt(sections[1]);
            count = right.getOrDefault(num, 0);
            right.put(num, count+1);
        });

        long similarityScore = 0;
        for(Map.Entry<Integer, Integer> entry: left.entrySet()) {
            int num = entry.getKey();
            long count = right.getOrDefault(num, 0);
            similarityScore += (num * count * entry.getValue());
        }

        return similarityScore;
    }
}
