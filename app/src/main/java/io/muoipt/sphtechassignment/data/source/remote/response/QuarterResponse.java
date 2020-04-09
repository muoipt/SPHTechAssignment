package io.muoipt.sphtechassignment.data.source.remote.response;

public class QuarterResponse {
    private float volume_of_mobile_data;
    private String quarter;
    private int _id;

    public QuarterResponse() {
    }


    public QuarterResponse(float volume_of_mobile_data, String quarter, int _id) {
        this.volume_of_mobile_data = volume_of_mobile_data;
        this.quarter = quarter;
        this._id = _id;
    }

    public float getVolume_of_mobile_data() {
        return volume_of_mobile_data;
    }

    public void setVolume_of_mobile_data(float volume_of_mobile_data) {
        this.volume_of_mobile_data = volume_of_mobile_data;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
