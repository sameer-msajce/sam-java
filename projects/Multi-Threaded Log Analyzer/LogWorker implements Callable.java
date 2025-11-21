import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class LogWorker implements Callable<Map<String, Integer>> {

    private final Path filePath;
    private final String[] keywords;

    public LogWorker(Path filePath, String[] keywords) {
        this.filePath = filePath;
        this.keywords = keywords;
    }

    @Override
    public Map<String, Integer> call() throws Exception {
        System.out.println("Processing file in thread: " + Thread.currentThread().getName());

        Map<String, Integer> localCount = new java.util.HashMap<>();

        try (Stream<String> lines = Files.lines(filePath)) {

            lines.forEach(line -> {
                for (String keyword : keywords) {
                    if (line.contains(keyword)) {
                        localCount.merge(keyword, 1, Integer::sum);
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return localCount;
    }
}
