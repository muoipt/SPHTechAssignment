package io.muoipt.sphtechassignment.data.model;

import java.util.List;

public class YearListWrapper {

    private List<Year> yearList;
    private String error;

    public YearListWrapper(String error) {
        this.error = error;
    }

    public YearListWrapper(List<Year> yearList) {
        this.yearList = yearList;
    }

    public List<Year> getYearList() {
        return yearList;
    }

    public void setYearList(List<Year> yearList) {
        this.yearList = yearList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
