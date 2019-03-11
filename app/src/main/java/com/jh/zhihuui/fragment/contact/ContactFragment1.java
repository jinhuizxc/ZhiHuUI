package com.jh.zhihuui.fragment.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jh.zhihuui.R;
import com.jh.zhihuui.activity.PersonActivity;
import com.jh.zhihuui.adapter.ContactAdapter;
import com.jh.zhihuui.model.ContactList;
import com.jh.zhihuui.model.DataModel;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 主要加载数据显示
 */
public class ContactFragment1 extends SupportFragment {


    private static final String TAG = "ContactFragment";

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private FrameLayout fl;

    public static ContactFragment1 newInstance() {
        Bundle args = new Bundle();
        ContactFragment1 fragment = new ContactFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact1, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        fl = view.findViewById(R.id.fl);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactAdapter = new ContactAdapter(DataModel.getData(), getContext());
        recyclerView.setAdapter(contactAdapter);
        Log.d(TAG, "getData: " + DataModel.getData().size());

        contactAdapter.setOnIteClickListener(new ContactAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(List<ContactList> data, int position) {
                if (data.get(position).getParentId() == 0) {
                    startActivity(new Intent(getContext(), PersonActivity.class));
                } else {
                    // 组织页面
                    final List<ContactList> child = data.get(position).child;
                    if (child != null) {
                        DetailFragment fragment = DetailFragment.newInstance(child);
                        start(fragment);
                        // 开始跳转fragment
                        Toast.makeText(getContext(), "组织页面", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "组织到底了!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}
