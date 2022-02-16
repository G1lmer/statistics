package com.statistics.categories;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UniqueValueCategoryStatisticsTest {

    @Test
    public void shouldReturnUniqueValues() {
        UniqueValueCategoryStatistics categoryStatistics =
                generateCategoryStatistics("ANIMALS", "cow", "cow");

        String statistics = categoryStatistics.convertDataToString();

        assertEquals("cow", statistics);
    }

    @Test
    public void shouldReturnSortedValues() {
        UniqueValueCategoryStatistics categoryStatistics =
                generateCategoryStatistics("ANIMALS", "cow", "sheep", "horse");
        String expectedResult = "cow" + System.lineSeparator() + "horse" + System.lineSeparator() + "sheep";

        String statistics = categoryStatistics.convertDataToString();

        assertEquals(expectedResult, statistics);
    }

    @Test
    public void categoryNameShouldBeUpperCase() {
        UniqueValueCategoryStatistics categoryStatistics =
                generateCategoryStatistics("animals", "cow");
        String expectedResult = "ANIMALS:" + System.lineSeparator() + "cow";

        String statistics = categoryStatistics.getCategoryStatistics();

        assertEquals(expectedResult, statistics);
    }

    @Test
    public void shouldReturnEmptyStringIfNoData() {
        UniqueValueCategoryStatistics categoryStatistics =
                generateCategoryStatistics("ANIMALS");
        String expectedResult = "";

        String statistics = categoryStatistics.getCategoryStatistics();
        boolean isEmpty = categoryStatistics.dataIsEmpty();

        assertEquals(expectedResult, statistics);
        assertTrue(isEmpty);
    }

    private UniqueValueCategoryStatistics generateCategoryStatistics(String categoryName, String... data) {
        UniqueValueCategoryStatistics categoryStatistics = new UniqueValueCategoryStatistics(categoryName);
        Arrays.stream(data).forEach(categoryStatistics::addData);
        return categoryStatistics;
    }

}
