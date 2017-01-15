package com.itheima.leon.funhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.itheima.leon.funhttplib.NetworkListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MVRequest.getRequest(new NetworkListener<List<HomeListItemBean>>() {
            @Override
            public void onFailed(String s) {

            }

            @Override
            public void onSuccess(List<HomeListItemBean> result) {
                Toast.makeText(MainActivity.this, "onSuccess " + result.size(), Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }
}
