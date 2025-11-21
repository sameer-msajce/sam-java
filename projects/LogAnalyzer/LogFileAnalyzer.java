package com.loganalyzer;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.Callable;

public class LogFileAnalyzer implements Callable<Map<String, Long>> {

    private Path file;

    public LogFileAnalyzer(Path file) {
        this.file = file;
    }

    @Override
    public Map<String, Long> call() throws Exception {
        System.out.println(Thread.currentThread().getName() +
                " processing " + file.getFileName());
        return AnalyzerUtils.analyzeFile(file);
    }
}
