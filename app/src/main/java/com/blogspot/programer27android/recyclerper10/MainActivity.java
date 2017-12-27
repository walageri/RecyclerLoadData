package com.blogspot.programer27android.recyclerper10;

import android.content.ClipData;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.UiThreadTest;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> list;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecAdapter adapter;

    View v;
    boolean isloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //list.addAll(Arrays.asList(getResources().getStringArray(R.array.namecountry)));
        adapter=new RecAdapter(list);
        recyclerView.setAdapter(adapter);
        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        adapter.setLoadMore(new ILoad(){
            @Override
            public void onLoad() {
                if (list.size()<=10){
                    list.add(null);
                    adapter.notifyItemInserted(list.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            list.remove(list.size()-1);
                            adapter.notifyItemRemoved(list.size());
                            int index = list.size();
                            int end = index+10;
                            for (int i = index; i<end; i++ ){
                                list.addAll(Arrays.asList(getResources().getStringArray(R.array.namecountry)));
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setIsLoaded();
                        }
                    },1000);
                }else {
                    Toast.makeText(MainActivity.this, "brhasil", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void loadData(){
        int i;
        for ( i=0; i<10; i++ ) {
            list.addAll(Arrays.asList(getResources().getStringArray(R.array.namecountry)));
        }
    }
//    public class MyHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 0:
//                    //load message
//                    RecyclerView.
//                    break;
//                case 1:
//                    adapter.addListItem((ArrayList<String>)msg.obj);
//                    recyclerView.
//            }
//        }
//    }
}
