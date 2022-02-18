package com.statistics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

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

    @Test
    public void shouldThrowStatisticsFileNotFoundException() {
        CategoriesAnalyzer categoriesAnalyzer = CategoriesAnalyzer.builder().build();

        StatisticsFileNotFoundException thrown = assertThrows(StatisticsFileNotFoundException.class, () ->
                categoriesAnalyzer.getCategoriesStatistic("src/test/resources/nonExists.txt"));

        assertEquals("Cannot read statistics from file src/test/resources/nonExists.txt", thrown.getMessage());
        assertEquals(NoSuchFileException.class, thrown.getCause().getClass());
    }

    @Test
    public void shouldThrowStatisticsFileNotFoundExceptionIfPathIsNull() {
        CategoriesAnalyzer categoriesAnalyzer = CategoriesAnalyzer.builder().build();

        StatisticsFileNotFoundException thrown = assertThrows(StatisticsFileNotFoundException.class, () ->
                categoriesAnalyzer.getCategoriesStatistic(null));

        assertEquals("Path is null. Provide correct path to the input file, please", thrown.getMessage());
    }

    @Test
    public void shouldThrowStatisticsFileNotFoundExceptionIfPathIsIncorrect() {
        CategoriesAnalyzer categoriesAnalyzer = CategoriesAnalyzer.builder().build();

        StatisticsFileNotFoundException thrown = assertThrows(StatisticsFileNotFoundException.class, () ->
                categoriesAnalyzer.getCategoriesStatistic(":incorrectPath.txt"));

        assertEquals("Incorrect path. Your path value may contain invalid characters", thrown.getMessage());
    }

}
