package com.loganalyzer;

import java.nio.file.Path;
import java.util.*;

public class SequentialAnalyzer {

    public static Map<String, Long> analyze(List<Path> files) {
        Map<String, Long> result = new HashMap<>();

        for (Path file : files) {
            try {
                Map<String, Long> temp = AnalyzerUtils.analyzeFile(file);
                temp.forEach((k, v) -> result.merge(k, v, Long::sum));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return result;
    }
}
