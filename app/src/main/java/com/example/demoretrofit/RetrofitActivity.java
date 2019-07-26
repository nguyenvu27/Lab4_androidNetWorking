package com.example.demoretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.demoretrofit.adapter.Adapter;
import com.example.demoretrofit.model.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {
    List<Example> exampleList = new ArrayList<>();
    private RecyclerView rcvExample;
    private Adapter adapterRCV;
    int curentPage;
    int lastVisibleItem, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


        curentPage = 1;
        adapterRCV = new Adapter(exampleList);
        rcvExample = (RecyclerView) findViewById(R.id.rcv_example);
        rcvExample.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcvExample.setAdapter(adapterRCV);
        demoRetrofit(curentPage);

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rcvExample.getLayoutManager();
        rcvExample.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (linearLayoutManager != null) {
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }
                totalItemCount = Integer.parseInt(String.valueOf(rcvExample.getAdapter().getItemCount()));
                if (!rcvExample.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    curentPage++;
                    demoRetrofit(curentPage);

                }
            }
        });

    }

    public void demoRetrofit(int curentPage) {
        PolyRetrofit.getInstance().getPostOfCategory("18", "10", String.valueOf(curentPage))
                .enqueue(new Callback<List<Example>>() {
                    @Override
                    public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                        if (response.code() == 200 && response.body() != null) {
                            adapterRCV.updateData(response.body());
                        } else {
                            Toast.makeText(RetrofitActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Example>> call, Throwable t) {
                        Log.e("err", t.getMessage());
                    }
                });
    }
}
