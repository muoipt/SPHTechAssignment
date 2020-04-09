package io.muoipt.sphtechassignment.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.muoipt.sphtechassignment.R;

import javax.inject.Inject;

import butterknife.BindView;
import io.muoipt.sphtechassignment.DataNetworkApplication;
import io.muoipt.sphtechassignment.adapter.DataNetworkAdapter;
import io.muoipt.sphtechassignment.utils.Utils;
import io.muoipt.sphtechassignment.viewmodel.DataNetworkViewModel;


public class MainActivity extends BaseView {
    private static final String TAG = "MainActivity";

    @BindView(R.id.rvDataNetwork)
    RecyclerView recyclerViewDataNetworkSent;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    DataNetworkViewModel dataNetworkViewModel;
    private DataNetworkAdapter dataNetworkAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DataNetworkApplication) getApplication()).getApplicationComponent().inject(this);

        initViews();
    }

    @Override
    protected void initViews() {
        super.initViews();
        initDataNetworkSentRecyclerView(this);
        getDataNetworkSent();

        if (!Utils.checkInternetConnection(this)) {
            showErrorActionSnackBar("No Internet Connection");
        }
    }

    private void initDataNetworkSentRecyclerView(Context context) {
        recyclerViewDataNetworkSent.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewDataNetworkSent.setLayoutManager(layoutManager);
        dataNetworkAdapter = new DataNetworkAdapter(context);
        recyclerViewDataNetworkSent.setAdapter(dataNetworkAdapter);

        dataNetworkViewModel = ViewModelProviders.of(this, viewModelFactory).get(DataNetworkViewModel.class);
    }

    private void getDataNetworkSent() {
        dataNetworkViewModel.getYearlyDataNetworkSent().observe(this, yearListWrapper -> {
            if (null != yearListWrapper.getYearList()) {
                dataNetworkAdapter.setYearList(yearListWrapper.getYearList());
                dataNetworkAdapter.notifyDataSetChanged();
            } else {
                showErrorActionSnackBar(yearListWrapper.getError());
                Log.e(TAG, "getDataNetworkSent: " + yearListWrapper.getError());
            }
        });
    }
}
