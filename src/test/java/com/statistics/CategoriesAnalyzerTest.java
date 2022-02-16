package com.statistics;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.statistics.categories.CategoryStatistics;
import com.statistics.exceptions.StatisticsFileNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesAnalyzerTest {

    @Spy
    private CategoryStatistics categoryStatistics;

    @Test
    public void shouldReadFileSuccessfully() throws StatisticsFileNotFoundException, IOException {
        CategoriesAnalyzer categoriesAnalyzer = CategoriesAnalyzer.builder()
                .category("ANIMALS", categoryStatistics)
                .build();

        categoriesAnalyzer.getCategoriesStatistic("src/test/resources/input.txt");

        verify(categoryStatistics, times(4)).addData(anyString());
    }

    @Test(expected = StatisticsFileNotFoundException.class)
    public void shouldThrowStatisticsFileNotFoundException() throws StatisticsFileNotFoundException {
        CategoriesAnalyzer categoriesAnalyzer = CategoriesAnalyzer.builder().build();

        categoriesAnalyzer.getCategoriesStatistic("src/test/resources/nonExists.txt");
    }

}
