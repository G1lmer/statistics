package com.statistics.categories;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Generates statistics about data you are interested in.
 * Statistics records are unique values with the number of occurrences.
 *
 * @author Serhii_Movenko
 */
public class QuantitativeCategoryStatistics extends AbstractCategoryStatistics {

    private static final String DATA_FORMAT = "%s:%s";

    public QuantitativeCategoryStatistics(String categoryName) {
        super(categoryName);
    }

    /**
     * Converts category data to the result statistic String.
     * Returns unique data values with the number of occurrences.
     */
    @Override
    protected String convertDataToString() {
        return getCategoryDataStream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(entry -> String.format(DATA_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
