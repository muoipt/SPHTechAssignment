package io.muoipt.sphtechassignment.utils;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

import io.muoipt.sphtechassignment.view.MainActivity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    Context context;

    @Before
    public void setUp() {
        ActivityScenario.launch(MainActivity.class);
        context = ApplicationProvider.getApplicationContext();
        assertNotNull(context);
    }

    @Test
    public void checkInternetConnection() {
        assertTrue(Utils.checkInternetConnection(context));
    }
}
