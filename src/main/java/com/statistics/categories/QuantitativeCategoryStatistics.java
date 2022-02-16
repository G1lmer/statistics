package com.statistics.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Generates statistics about data you are interested in.
 * Statistics records are unique values with the number of occurrences.
 */
public class QuantitativeCategoryStatistics extends AbstractCategoryStatistics {

    private static final String DATA_FORMAT = "%s:%s";

    private List<String> categoryData = new ArrayList<>();

    public QuantitativeCategoryStatistics(String categoryName) {
        super(categoryName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String convertDataToString() {
        return categoryData.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(entry -> String.format(DATA_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean dataIsEmpty() {
        return categoryData.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addData(String data) {
        categoryData.add(data);
    }

}
