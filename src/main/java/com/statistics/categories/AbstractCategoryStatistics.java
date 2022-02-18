package com.statistics.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

/**
 * {@inheritDoc}
 *
 * @author Serhii_Movenko
 */
public abstract class AbstractCategoryStatistics implements CategoryStatistics {

    private static final String CATEGORY_NAME_DELIMITER = ":" + System.lineSeparator();

    private String categoryName;
    private List<String> categoryData = new ArrayList<>();

    protected AbstractCategoryStatistics(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategoryStatistics() {
        return Optional.of(convertDataToString())
                .filter(StringUtils::isNotEmpty)
                .map(data -> categoryName.toUpperCase() + CATEGORY_NAME_DELIMITER + data)
                .orElse(StringUtils.EMPTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addData(String data) {
        categoryData.add(data);
    }

    /**
     * Provides Stream of category data for inheritors
     */
    protected Stream<String> getCategoryDataStream() {
        return categoryData.stream();
    }

    /**
     * Converts category data to the result statistic String.
     */
    protected abstract String convertDataToString();

}
