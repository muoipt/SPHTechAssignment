package io.muoipt.sphtechassignment.view;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import io.muoipt.sphtechassignment.R;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseView extends AppCompatActivity {

    @BindView(R.id.parentLayout)
    View parentLayout;

    protected void initViews() {
        ButterKnife.bind(this);
    }

    protected void showErrorActionSnackBar(String message) {
        final Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();
    }
}
