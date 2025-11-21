package com.loganalyzer;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class ConcurrentAnalyzer {

    public static Map<String, Long> analyze(List<Path> files, int poolSize)
            throws InterruptedException {

        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);

        List<Future<Map<String, Long>>> futures = new ArrayList<>();

        for (Path file : files) {
            futures.add(executor.submit(new LogFileAnalyzer(file)));
        }

        ConcurrentHashMap<String, LongAdder> agg = new ConcurrentHashMap<>();

        for (Future<Map<String, Long>> future : futures) {
            try {
                Map<String, Long> temp = future.get();
                temp.forEach((k, v) ->
                        agg.computeIfAbsent(k, x -> new LongAdder()).add(v));
            } catch (Exception e) {
                System.out.println("Thread error: " + e.getMessage());
            }
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.MINUTES);

        return agg.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().longValue()));
    }
}
