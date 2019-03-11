package com.jh.zhihuui.fragment.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.jh.zhihuui.R;
import com.jh.zhihuui.activity.PersonActivity;
import com.jh.zhihuui.adapter.ContactAdapter;
import com.jh.zhihuui.model.ContactList;

import java.io.Serializable;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class DetailFragment extends SupportFragment implements View.OnTouchListener {

    private RecyclerView recyclerView;
    private FrameLayout fl;

    private ContactAdapter contactAdapter;
    private List<ContactList> child;

    public static DetailFragment newInstance(List<ContactList> child) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) child);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        child = (List<ContactList>) bundle.getSerializable("data");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact1, null);
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        if (child == null){
//            getActivity().finish();
//            return;
//        }
        recyclerView = view.findViewById(R.id.recyclerView);
        fl = view.findViewById(R.id.fl);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactAdapter = new ContactAdapter(child, getActivity());
        recyclerView.setAdapter(contactAdapter);


        contactAdapter.setOnIteClickListener(new ContactAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(List<ContactList> data, int position) {
                if (data.get(position).getParentId() == 0){
                    startActivity(new Intent(getActivity(), PersonActivity.class));
                }else {
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


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
