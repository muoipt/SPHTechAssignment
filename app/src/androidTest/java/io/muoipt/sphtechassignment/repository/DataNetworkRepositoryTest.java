package io.muoipt.sphtechassignment.repository;


import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.ConnectException;

import io.muoipt.sphtechassignment.data.source.remote.network.APIClient;
import io.muoipt.sphtechassignment.data.source.remote.network.action.OnDataRepoResponse;
import io.muoipt.sphtechassignment.data.source.remote.response.DataRepoResponse;
import io.muoipt.sphtechassignment.view.MainActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class DataNetworkRepositoryTest {

    Context context;

    @Before
    public void setUp() {
        ActivityScenario.launch(MainActivity.class);
        context = ApplicationProvider.getApplicationContext();
        assertNotNull(context);
    }

    @Test
    public void getYearlyMobileDataUsage() {
        APIClient apiClient = new APIClient(context);
        apiClient.getDataNetworkSent(new OnDataRepoResponse() {
            @Override
            public void onErrorResponse(String error) {
                assertNotNull(error);
            }

            @Override
            public void onSuccessDataRepoResponse(DataRepoResponse dataRepoResponse) {
                assertEquals(dataRepoResponse.getSuccess(), true);
            }
        });
    }

    @Test
    public void testNetworkErrors_connectionException() {
        ConnectException connectException = new ConnectException("Error");
        APIClient apiClient = new APIClient(context);
        assertEquals(apiClient.handleErrors(connectException), "Connection error");
    }

    @Test
    public void testNetworkErrors_otherException() {
        APIClient apiClient = new APIClient(context);
        assertEquals(apiClient.handleErrors(new Throwable()), "Error occurred");
    }


}
