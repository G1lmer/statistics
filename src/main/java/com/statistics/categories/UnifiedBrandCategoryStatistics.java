package com.statistics.categories;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Generates statistics about data you are interested in.
 * Statistics records are unique values win lower case,
 * backwards sorted and contains cache in it.
 *
 * @author Serhii_Movenko
 */
public class UnifiedBrandCategoryStatistics extends AbstractCategoryStatistics {

    public UnifiedBrandCategoryStatistics(String categoryName) {
        super(categoryName);
    }

    /**
     * Converts category data to the result statistic String.
     * Returns unique data values in lowercase,
     * sorted in reverse order,
     * with the cache prefix of these values in the brackets and with suffix §.
     */
    @Override
    protected String convertDataToString() {
        return getCategoryDataStream()
                .map(String::toLowerCase)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .map(brand -> "§ " + brand + " (" + DigestUtils.md5Hex(brand) + ")")
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
