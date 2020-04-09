package io.muoipt.sphtechassignment.data.source.remote.response;

public class DataRepoResponse {
    private String help;
    private Boolean success;
    private ResultResponse result;

    public DataRepoResponse() {
    }

    public DataRepoResponse(String help, Boolean success, ResultResponse result) {
        this.help = help;
        this.success = success;
        this.result = result;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ResultResponse getResult() {
        return result;
    }

    public void setResult(ResultResponse result) {
        this.result = result;
    }
}
