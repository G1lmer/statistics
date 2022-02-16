package com.statistics.categories;

import org.apache.commons.lang3.StringUtils;

/**
 * {@inheritDoc}
 */
public abstract class AbstractCategoryStatistics implements CategoryStatistics {

    private static final String CATEGORY_NAME_DELIMITER = ":" + System.lineSeparator();

    private String categoryName;

    public AbstractCategoryStatistics(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategoryStatistics() {
        if (dataIsEmpty()) {
            return StringUtils.EMPTY;
        }
        return categoryName.toUpperCase() + CATEGORY_NAME_DELIMITER + convertDataToString();
    }

    /**
     * Converts category data to the result statistic String
     */
    protected abstract String convertDataToString();

    /**
     * Return true if there no any data for this category.
     * Returns false otherwise.
     */
    protected abstract boolean dataIsEmpty();

}
