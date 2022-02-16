package com.statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.statistics.categories.CategoryStatistics;
import com.statistics.exceptions.StatisticsFileNotFoundException;

/**
 * This object reads categories data from
 * the input file and provides categories statistics.
 */
public class CategoriesAnalyzer {

    private Map<String, CategoryStatistics> categories;
    private String currentCategoryName;

    public CategoriesAnalyzer(Map<String, CategoryStatistics> categories) {
        this.categories = categories;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Reads data from the input file and returns
     * categories statistics as String.
     *
     * @param inputFilePath - path to the input file with categories data
     * @return categories statistics
     * @throws StatisticsFileNotFoundException when the input file does not exist.
     */
    public String getCategoriesStatistic(String inputFilePath) throws StatisticsFileNotFoundException {
        if (inputFilePath == null) {
            throw new StatisticsFileNotFoundException("Path is null. Provide correct path to the input file, please");
        }
        try {
            readData(inputFilePath);
        } catch (IOException ex) {
            throw new StatisticsFileNotFoundException("Cannot read statistics from file " + inputFilePath, ex);
        }

        return categories.values().stream()
                .map(CategoryStatistics::getCategoryStatistics)
                .collect(Collectors.joining(System.lineSeparator() + System.lineSeparator()));
    }

    private void readData(String inputFilePath) throws IOException {
        Path path = Paths.get(inputFilePath);
        Files.lines(path).forEach(this::addData);
    }

    private boolean isCategory(String line) {
        return categories.containsKey(line.toUpperCase());
    }

    private void addData(String line) {
        if (isCategory(line)) {
            currentCategoryName = line;
            return;
        }
        categories.get(currentCategoryName).addData(line);
    }

    /**
     * The builder for CategoriesAnalyzer
     */
    static class Builder {

        private final Map<String, CategoryStatistics> categories = new HashMap<>();

        public Builder category(String categoryName, CategoryStatistics categoryStatistics) {
            categories.put(categoryName, categoryStatistics);
            return this;
        }

        public CategoriesAnalyzer build() {
            return new CategoriesAnalyzer(categories);
        }

    }

}
