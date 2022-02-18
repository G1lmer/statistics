package com.statistics.categories;

import java.util.stream.Collectors;

/**
 * Generates statistics about data you are interested in.
 * Statistics records are unique and sorted values.
 *
 * @author Serhii_Movenko
 */
public class UniqueValueCategoryStatistics extends AbstractCategoryStatistics {

    public UniqueValueCategoryStatistics(String categoryName) {
        super(categoryName);
    }

    /**
     * Converts category data to the result statistic String.
     * Returns unique data values sorted alphabetically.
     */
    @Override
    protected String convertDataToString() {
        return getCategoryDataStream()
                .distinct()
                .sorted()
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
