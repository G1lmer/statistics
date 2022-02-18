package com.statistics.categories;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuantitativeCategoryStatisticsTest {

    @Test
    public void shouldReturnCountOfValues() {
        QuantitativeCategoryStatistics categoryStatistics =
                generateCategoryStatistics("NUMBERS", "one", "two", "one");
        String expectedResult = "one:2" + System.lineSeparator() + "two:1";

        String statistics = categoryStatistics.convertDataToString();

        assertEquals(expectedResult, statistics);
    }

    @Test
    public void categoryNameShouldBeUpperCase() {
        QuantitativeCategoryStatistics categoryStatistics =
                generateCategoryStatistics("numbers", "one");
        String expectedResult = "NUMBERS:" + System.lineSeparator() + "one:1";

        String statistics = categoryStatistics.getCategoryStatistics();

        assertEquals(expectedResult, statistics);
    }

    @Test
    public void shouldReturnEmptyStringIfNoData() {
        QuantitativeCategoryStatistics categoryStatistics =
                generateCategoryStatistics("NUMBERS");
        String expectedResult = "";

        String statistics = categoryStatistics.getCategoryStatistics();

        assertEquals(expectedResult, statistics);
    }

    private QuantitativeCategoryStatistics generateCategoryStatistics(String categoryName, String... data) {
        QuantitativeCategoryStatistics categoryStatistics = new QuantitativeCategoryStatistics(categoryName);
        Arrays.stream(data).forEach(categoryStatistics::addData);
        return categoryStatistics;
    }

}
