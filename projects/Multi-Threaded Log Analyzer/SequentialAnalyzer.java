import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class SequentialAnalyzer {

    private static final String[] KEYWORDS = {"ERROR", "WARN", "INFO"};

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter folder path: ");
        String folderPath = sc.nextLine();

        List<Path> logFiles = Files.list(Path.of(folderPath))
                .filter(p -> p.toString().endsWith(".txt"))
                .toList();

        Map<String, Integer> counts = new HashMap<>();
        long start = System.currentTimeMillis();

        for (Path file : logFiles) {
            try (Stream<String> lines = Files.lines(file)) {
                lines.forEach(line -> {
                    for (String keyword : KEYWORDS) {
                        if (line.contains(keyword)) {
                            counts.merge(keyword, 1, Integer::sum);
                        }
                    }
                });
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("\n===== Sequential Log Analyzer Summary =====");
        counts.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("Execution Time: " + (end - start) + " ms");
    }
}
