package com.jh.zhihuui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.jh.zhihuui.R;
import com.jh.zhihuui.adapter.ContactAdapter;
import com.jh.zhihuui.model.ContactList;

import java.io.Serializable;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    private List<ContactList> child;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        child = (List<ContactList>) getIntent().getSerializableExtra("data");

        if (child == null){
            finish();
            return;
        }
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(child, this);
        recyclerView.setAdapter(contactAdapter);

        contactAdapter.setOnIteClickListener(new ContactAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(List<ContactList> data, int position) {
                if (data.get(position).getParentId() == 0){
                    startActivity(new Intent(DetailActivity.this, PersonActivity.class));
                }else {
                    // 组织页面
                    List<ContactList> child = data.get(position).child;
                    Intent intent = new Intent();
                    intent.putExtra("data", (Serializable) child);
                    startActivity(new Intent(DetailActivity.this, DetailActivity.class));
                }
            }
        });

    }
}
