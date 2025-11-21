package com.loganalyzer;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    private static final int DEFAULT_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Usage: java com.loganalyzer.Main <logs-folder> [pool-size]");
            return;
        }

        Path logsDir = Paths.get(args[0]);
        int poolSize = (args.length >= 2) ? Integer.parseInt(args[1]) : DEFAULT_POOL_SIZE;

        if (!Files.isDirectory(logsDir)) {
            System.out.println("Not a directory!");
            return;
        }

        List<Path> logFiles = Files.walk(logsDir)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        long seqStart = System.nanoTime();
        Map<String, Long> seq = SequentialAnalyzer.analyze(logFiles);
        long seqEnd = System.nanoTime();

        long concStart = System.nanoTime();
        Map<String, Long> conc = ConcurrentAnalyzer.analyze(logFiles, poolSize);
        long concEnd = System.nanoTime();

        System.out.println("Sequential: " +
                (seqEnd - seqStart) / 1_000_000 + " ms");
        System.out.println("Concurrent: " +
                (concEnd - concStart) / 1_000_000 + " ms");
    }
}
