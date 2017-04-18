package com.example.ggs.recyclerviewdivider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengjian on 2017/4/1.
 */

public class MainActivity extends AppCompatActivity {
    List<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("item : " + i);
        }
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CustomAdapter adapter = new CustomAdapter();
        adapter.setData(data);
//        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
//        decor.setWidth(4);
        HorzItemDecoration decor = new HorzItemDecoration(this, R.drawable.divider, 40, LinearLayoutManager.HORIZONTAL);
        recyclerView.addItemDecoration(decor);
        recyclerView.setAdapter(adapter);
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

        private List<String> data;

        private void setData(List<String> data) {
            this.data = data;
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new CustomViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.name.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public CustomViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
