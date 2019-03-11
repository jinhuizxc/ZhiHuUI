package com.jh.zhihuui.demo.zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jh.zhihuui.R;

import me.yokeyword.fragmentation.SupportFragment;

public class WorkspaceFragmentNew extends SupportFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workspace_child, null);
        return view;
    }
}
