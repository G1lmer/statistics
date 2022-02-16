package com.statistics.categories;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates statistics about data you are interested in.
 * Statistics records are unique values win lower case,
 * backwards sorted and contains cache in it.
 */
public class UnifiedBrandCategoryStatistics extends AbstractCategoryStatistics {

    private List<String> categoryData = new ArrayList<>();

    public UnifiedBrandCategoryStatistics(String categoryName) {
        super(categoryName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String convertDataToString() {
        return categoryData.stream()
                .map(String::toLowerCase)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .map(brand -> "§ " + brand + " (" + DigestUtils.md5Hex(brand) + ")")
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
