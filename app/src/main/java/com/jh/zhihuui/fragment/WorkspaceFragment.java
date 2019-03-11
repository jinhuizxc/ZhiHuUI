package com.jh.zhihuui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jh.zhihuui.R;

import me.yokeyword.fragmentation.SupportFragment;

public class WorkspaceFragment extends SupportFragment {

    public static WorkspaceFragment newInstance() {

        Bundle args = new Bundle();
        WorkspaceFragment fragment = new WorkspaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workspace, null);
    }
}
