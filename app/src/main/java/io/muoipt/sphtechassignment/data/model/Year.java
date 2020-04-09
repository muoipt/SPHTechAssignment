package io.muoipt.sphtechassignment.data.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Year {
    private Integer year;
    private List<Quarter> quarters;

    public Year(Integer year, List<Quarter> quarters) {
        this.year = year;
        initQuarters(quarters);
    }

    public boolean isYearCompleted() {
        return quarters.size() == 4;
    }

    private void initQuarters(List<Quarter> quarterList) {
        Collections.sort(quarterList, new Comparator<Quarter>() {
            public int compare(Quarter q1, Quarter q2) {
                return q1.getQuarterName().compareTo(q2.getQuarterName());
            }
        });

        for (int i = 0; i < quarterList.size(); i++) {
            if (i == 0) {
                quarterList.get(i).setSentGrowth(0f);
            } else {
                float growth = quarterList.get(i).getSent() - quarterList.get(i - 1).getSent();
                quarterList.get(i).setSentGrowth(growth);
            }
        }

        this.quarters = quarterList;
    }

    public float getTotalSent() {
        float sent = 0;
        for (Quarter quarter : quarters) {
            sent = sent + quarter.getSent();
        }
        return sent;
    }

    public Integer getYear() {
        return year;
    }

    public List<Quarter> getQuarters() {
        return quarters;
    }

    public boolean isDecreasedGrowth() {
        boolean isDecreaseGrowth = false;

        for (Quarter quarter : quarters) {
            if (quarter.getSentGrowth() < 0) {
                isDecreaseGrowth = true;
            }
        }
        return isDecreaseGrowth;
    }

}
