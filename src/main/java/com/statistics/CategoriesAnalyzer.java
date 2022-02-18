package com.statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.statistics.categories.CategoryStatistics;
import com.statistics.exceptions.StatisticsFileNotFoundException;

/**
 * This object reads categories data from
 * the input file and provides categories statistics.
 *
 * @author Serhii_Movenko
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
        Optional.ofNullable(inputFilePath)
                .orElseThrow(() ->
                        new StatisticsFileNotFoundException(
                                "Path is null. Provide correct path to the input file, please"));

        readData(inputFilePath);

        return categories.values().stream()
                .map(CategoryStatistics::getCategoryStatistics)
                .collect(Collectors.joining(System.lineSeparator() + System.lineSeparator()));
    }

    private void readData(String inputFilePath) throws StatisticsFileNotFoundException {
        Path path = getPath(inputFilePath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(this::addData);
        } catch (IOException ex) {
            throw new StatisticsFileNotFoundException("Cannot read statistics from file " + inputFilePath, ex);
        }
    }

    private Path getPath(String inputFilePath) throws StatisticsFileNotFoundException {
        try {
            return Paths.get(inputFilePath);
        } catch (InvalidPathException exception) {
            throw new StatisticsFileNotFoundException("Incorrect path. Your path value may contain invalid characters");
        }
    }

    private void addData(String line) {
        String categoryName = line.toUpperCase();
        Optional.ofNullable(categories.get(categoryName))
                .ifPresentOrElse(categoryStatistics -> currentCategoryName = categoryName,
                        () -> addDataForCurrentCategory(line));
    }

    private void addDataForCurrentCategory(String line) {
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
