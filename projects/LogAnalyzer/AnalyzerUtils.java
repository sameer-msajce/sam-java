package com.loganalyzer;

import java.nio.file.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class AnalyzerUtils {

    public static Map<String, Long> analyzeFile(Path file) throws Exception {

        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .flatMap(l -> Arrays.stream(l.split("\\W+")))
                    .map(String::toLowerCase)
                    .filter(s -> !s.isBlank())
                    .collect(Collectors.groupingBy(Function.identity(),
                            Collectors.counting()));
        }
    }
}
