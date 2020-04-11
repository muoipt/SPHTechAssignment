package io.muoipt.sphtechassignment;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.muoipt.sphtechassignment.data.model.Quarter;
import io.muoipt.sphtechassignment.data.model.Year;

public class YearTest {
    public static final int ITEM_YEAR = 2008;

    Year year;

    public void setUpCompleteIncrementalQuarters() {
        List<Quarter> quarterList = new ArrayList<>();
        quarterList.add(new Quarter(1, 1f, ITEM_YEAR, "Q1"));
        quarterList.add(new Quarter(2, 2f, ITEM_YEAR, "Q2"));
        quarterList.add(new Quarter(3, 3f, ITEM_YEAR, "Q3"));
        quarterList.add(new Quarter(4, 4f, ITEM_YEAR, "Q4"));
        year = new Year(2008, quarterList);
    }

    public void setUpCompleteDecrementalQuarters() {
        List<Quarter> quarterList = new ArrayList<>();
        quarterList.add(new Quarter(1, 4f, ITEM_YEAR, "Q1"));
        quarterList.add(new Quarter(2, 3f, ITEM_YEAR, "Q2"));
        quarterList.add(new Quarter(3, 2f, ITEM_YEAR, "Q3"));
        quarterList.add(new Quarter(4, 1f, ITEM_YEAR, "Q4"));
        year = new Year(2008, quarterList);
    }

    public void setUpIncompleteQuarters() {
        List<Quarter> quarterList = new ArrayList<>();
        quarterList.add(new Quarter(1, 4f, ITEM_YEAR, "Q1"));
        quarterList.add(new Quarter(2, 3f, ITEM_YEAR, "Q2"));
        year = new Year(2008, quarterList);
    }

    @Test
    public void isYearCompleted_completeQuarters() {
        setUpCompleteIncrementalQuarters();
        Assert.assertTrue(year.isYearCompleted());
    }

    @Test
    public void isYearCompleted_incompleteQuarters() {
        setUpIncompleteQuarters();
        Assert.assertFalse(year.isYearCompleted());
    }

    @Test
    public void getTotalUsage() {
        setUpCompleteIncrementalQuarters();
        Assert.assertEquals(year.getTotalSent(), 10f, 0f);
    }

    @Test
    public void getYear() {
        setUpCompleteIncrementalQuarters();
        Assert.assertEquals(year.getYear(), ITEM_YEAR, 0);
    }

    @Test
    public void getQuarters() {
        setUpCompleteIncrementalQuarters();
        Assert.assertEquals(year.getQuarters().size(), 4);
    }

    @Test
    public void isDecreasedGrowth_increaseGrowthQuarters() {
        setUpCompleteIncrementalQuarters();
        Assert.assertFalse(year.isDecreasedGrowth());
    }

    @Test
    public void isDecreasedGrowth_decreaseGrowthQuarters() {
        setUpCompleteDecrementalQuarters();
        Assert.assertTrue(year.isDecreasedGrowth());
    }
}
