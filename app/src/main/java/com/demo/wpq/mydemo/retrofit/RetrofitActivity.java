package com.demo.wpq.mydemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    public static final String TAG = "RetrofitActivity";


    String[] hostUrls = {"https://dev.healskare.com"};

    @Bind(R.id.result)
    TextView result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        getVersionInfo();
//        gankGirl();
    }

    private void getVersionInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.miaoyiapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClientManager.getClient())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<VersionBean> call = service.getVersionInfo();
        call.enqueue(new Callback<VersionBean>() {
            @Override
            public void onResponse(Call<VersionBean> call, retrofit2.Response<VersionBean> response) {
                Log.e(TAG, response.code() + ", " + response.isSuccessful() + ", " + response.message());
                if (response.isSuccessful()) {
                    VersionBean version = response.body();
                    result.setText(version.toString());
                }
            }

            @Override
            public void onFailure(Call<VersionBean> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void gankGirl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<GanHuo> call = service.getGanHuo("福利", 10, 1);
        call.enqueue(new Callback<GanHuo>() {
            @Override
            public void onResponse(Call<GanHuo> call, retrofit2.Response<GanHuo> response) {
                Log.e(TAG, response.code() + ", " + response.isSuccessful() + ", " + response.message());
                if (response.isSuccessful()) {
                    GanHuo ganhuo = response.body();
                    Log.e(TAG, ganhuo.getResults().get(0).getUrl());
                }
            }

            @Override
            public void onFailure(Call<GanHuo> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }


}
