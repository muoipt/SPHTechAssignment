package io.muoipt.sphtechassignment.data.model;

public class Quarter {
    private Integer id;
    private Float sent;
    private Integer year;
    private String quarterName;
    private Float sentGrowth;

    public Quarter() {
    }

    public Quarter(Integer id, Float sent, Integer year, String quarterName) {
        this.id = id;
        this.sent = sent;
        this.year = year;
        this.quarterName = quarterName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSent() {
        return sent;
    }

    public void setSent(Float sent) {
        this.sent = sent;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getQuarterName() {
        return quarterName;
    }

    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    public Float getSentGrowth() {
        return sentGrowth;
    }

    public void setSentGrowth(Float sentGrowth) {
        this.sentGrowth = sentGrowth;
    }
}
