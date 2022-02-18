package com.statistics.categories;

/**
 * Generates statistics about data you are interested in.
 *
 * @author Serhii_Movenko
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
     * @param data - the data to add to statistics
     */
    void addData(String data);

}
