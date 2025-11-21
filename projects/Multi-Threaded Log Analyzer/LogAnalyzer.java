import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class LogAnalyzer {

    private static final String[] KEYWORDS = {"ERROR", "WARN", "INFO"};

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter folder path: ");
        String folderPath = sc.nextLine();

        // Retrieve all .txt log files
        List<Path> logFiles = Files.list(Path.of(folderPath))
                .filter(p -> p.toString().endsWith(".txt"))
                .collect(Collectors.toList());

        int threadCount = 4;  // customize as required
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        List<Future<Map<String, Integer>>> futures = new ArrayList<>();
        ConcurrentHashMap<String, Integer> globalCount = new ConcurrentHashMap<>();

        long start = System.currentTimeMillis();

        // Submit worker tasks
        for (Path file : logFiles) {
            futures.add(executor.submit(new LogWorker(file, KEYWORDS)));
        }

        // Merge worker results into global map
        for (Future<Map<String, Integer>> future : futures) {
            Map<String, Integer> localMap = future.get();
            localMap.forEach((k, v) -> globalCount.merge(k, v, Integer::sum));
        }

        long end = System.currentTimeMillis();

        executor.shutdown();

        // Output summary
        System.out.println("\n===== Concurrent Log Analyzer Summary =====");
        globalCount.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("Execution Time: " + (end - start) + " ms");

        // Write to results file
        try (FileWriter fw = new FileWriter("concurrent_results.txt")) {
            fw.write("Concurrent Log Analyzer Summary\n");
            globalCount.forEach((k, v) -> {
                try {
                    fw.write(k + ": " + v + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            fw.write("Execution Time: " + (end - start) + " ms\n");
        }
    }
}
