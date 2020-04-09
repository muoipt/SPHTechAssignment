package io.muoipt.sphtechassignment.data.source.remote.network.action;

import io.muoipt.sphtechassignment.data.source.remote.response.DataRepoResponse;

public interface OnDataRepoResponse extends OnErrorResponse {
    void onSuccessDatastoreResponse(DataRepoResponse dataRepoResponse);
}
