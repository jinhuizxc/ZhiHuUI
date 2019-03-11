package com.jh.zhihuui.fragment.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jh.zhihuui.R;
import com.jh.zhihuui.fragment.base.BaseMainFragment;
import com.jh.zhihuui.fragment.four.ZhihuFourthFragment;


import me.yokeyword.fragmentation.SupportFragment;

public class ContactFragment extends BaseMainFragment {


    private static final String TAG = "ContactFragment";

    public static ContactFragment newInstance() {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, null);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        if (findChildFragment(ContactFragment1.class) == null) {
            loadRootFragment(R.id.fl, ContactFragment1.newInstance());
        }
    }

}
