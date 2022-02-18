package com.statistics;

import com.statistics.categories.QuantitativeCategoryStatistics;
import com.statistics.categories.UnifiedBrandCategoryStatistics;
import com.statistics.categories.UniqueValueCategoryStatistics;
import com.statistics.exceptions.StatisticsFileNotFoundException;

/**
 * The main app to demonstrate functionality
 *
 * @author Serhii_Movenko
 */
public class App {

    public static void main(String[] args) {
        CategoriesAnalyzer categoriesAnalyzer = CategoriesAnalyzer.builder()
                .category("ANIMALS", new UniqueValueCategoryStatistics("ANIMALS"))
                .category("CARS", new UnifiedBrandCategoryStatistics("CARS"))
                .category("NUMBERS", new QuantitativeCategoryStatistics("NUMBERS"))
                .build();

        try {
            String categoriesStatistic = categoriesAnalyzer.getCategoriesStatistic("input.txt");
            System.out.println(categoriesStatistic);
        } catch (StatisticsFileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Check that the file exists, please.");
        }
    }


}
