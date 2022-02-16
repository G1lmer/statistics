package com.statistics.categories;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnifiedBrandCategoryStatisticsTest {

    @Test
    public void shouldReturnUniqueValues() {
        UnifiedBrandCategoryStatistics categoryStatistics =
                generateCategoryStatistics("CARS", "audi", "audi");

        String statistics = categoryStatistics.convertDataToString();

        assertEquals("§ audi (4d9fa555e7c23996e99f1fb0e286aea8)", statistics);
    }

    @Test
    public void shouldReturnSortedValues() {
        UnifiedBrandCategoryStatistics categoryStatistics =
                generateCategoryStatistics("CARS", "audi", "vw", "opel");
        String expectedResult = "§ vw (7336a2c49b0045fa1340bf899f785e70)"
                + System.lineSeparator()
                + "§ opel (f65b7d39472c52142ea2f4ea5e115d59)"
                + System.lineSeparator()
                + "§ audi (4d9fa555e7c23996e99f1fb0e286aea8)";

        String statistics = categoryStatistics.convertDataToString();

        assertEquals(expectedResult, statistics);
    }

    @Test
    public void categoryNameShouldBeUpperCase() {
        UnifiedBrandCategoryStatistics categoryStatistics =
                generateCategoryStatistics("cars", "audi");
        String expectedResult = "CARS:" + System.lineSeparator() + "§ audi (4d9fa555e7c23996e99f1fb0e286aea8)";

        String statistics = categoryStatistics.getCategoryStatistics();

        assertEquals(expectedResult, statistics);
    }

    @Test
    public void shouldReturnEmptyStringIfNoData() {
        UnifiedBrandCategoryStatistics categoryStatistics =
                generateCategoryStatistics("CARS");
        String expectedResult = "";

        String statistics = categoryStatistics.getCategoryStatistics();
        boolean isEmpty = categoryStatistics.dataIsEmpty();

        assertEquals(expectedResult, statistics);
        assertTrue(isEmpty);
    }

    private UnifiedBrandCategoryStatistics generateCategoryStatistics(String categoryName, String... data) {
        UnifiedBrandCategoryStatistics categoryStatistics = new UnifiedBrandCategoryStatistics(categoryName);
        Arrays.stream(data).forEach(categoryStatistics::addData);
        return categoryStatistics;
    }

}
