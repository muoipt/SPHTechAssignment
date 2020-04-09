package io.muoipt.sphtechassignment.data.source.remote.response;

import java.util.List;

public class ResultResponse {
    private String resource_id;
    private List<QuarterResponse> records;

    public ResultResponse() {
    }

    public ResultResponse(String resource_id, List<QuarterResponse> records) {
        this.resource_id = resource_id;
        this.records = records;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public List<QuarterResponse> getRecords() {
        return records;
    }

    public void setRecords(List<QuarterResponse> records) {
        this.records = records;
    }
}
