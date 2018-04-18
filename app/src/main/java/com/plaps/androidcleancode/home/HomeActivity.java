package com.plaps.androidcleancode.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.plaps.androidcleancode.MyApplication;
import com.plaps.androidcleancode.R;
import com.plaps.androidcleancode.models.CityListData;
import com.plaps.androidcleancode.models.CityListResponse;
import com.plaps.androidcleancode.models.Name;
import com.plaps.androidcleancode.networking.Service;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private static final String TAG = HomeActivity.class.getName();
    private RecyclerView list;

    @Inject
    public Service service;

    @Inject
    public Name name;

    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplicationContext()).getNetworkComponent().inject(this);

        renderView();
        init();

        Log.d(TAG, name.toString());

        HomePresenter presenter = new HomePresenter(service, this);
        presenter.getCityList();
    }

    public void renderView() {
        setContentView(R.layout.activity_home);
        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
    }

    public void init() {
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCityListSuccess(CityListResponse cityListResponse) {

        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), cityListResponse.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CityListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);

    }
}
