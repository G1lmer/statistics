package com.statistics.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates statistics about data you are interested in.
 * Statistics records are unique and sorted values.
 */
public class UniqueValueCategoryStatistics extends AbstractCategoryStatistics {

    private List<String> categoryData = new ArrayList<>();

    public UniqueValueCategoryStatistics(String categoryName) {
        super(categoryName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String convertDataToString() {
        return categoryData.stream()
                .distinct()
                .sorted()
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
