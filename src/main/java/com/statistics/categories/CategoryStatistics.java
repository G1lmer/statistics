package com.statistics.categories;

/**
 * Generates statistics about data you are interested in.
 */
public interface CategoryStatistics {

    /**
     * Returns data statistic
     *
     * @return statistics for this category
     */
    String getCategoryStatistics();

    /**
     * Adds new data for this category.
     * This data will be used to generate statistics.
     *
     * @param data
     */
    void addData(String data);

}
