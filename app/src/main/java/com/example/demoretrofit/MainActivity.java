package com.example.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.demoretrofit.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void demoRetrofit(View view) {
        startActivity(new Intent(getApplicationContext(), RetrofitActivity.class));
    }

//    public void demoRetrofit(View view) {
//        PolyRetrofit.getInstance().getPostOfCategory("18", "5", "1")
//                .enqueue(new Callback<List<Example>>() {
//                    @Override
//                    public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
//                        Log.d("size", response.body().size() + "");
//                        Log.d("code", response.code() + "");
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Example>> call, Throwable t) {
//                        Log.e("err", t.getMessage());
//                    }
//                });
//    }
}
