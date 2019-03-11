package com.jh.zhihuui.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.jh.zhihuui.R;
import com.jh.zhihuui.adapter.MyAdapter;
import com.jh.zhihuui.fragment.MessageFragment;
import com.jh.zhihuui.fragment.MyFragment;
import com.jh.zhihuui.fragment.WorkspaceFragment;
import com.jh.zhihuui.fragment.contact.ContactFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private String title[] = new String[]{"消息", "通讯录", "工作台", "我"};
    private List<Fragment> fragments = new ArrayList<>();

    private Fragment frags[] = new Fragment[4];


    private MessageFragment messageFragment;
    private ContactFragment contactFragment;
    private WorkspaceFragment workspaceFragment;
    private MyFragment myFragment;

    private Fragment currentFragment;

    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (messageFragment == null) {
            messageFragment = new MessageFragment();
        }

        if (contactFragment == null) {
            contactFragment = new ContactFragment();
        }

        if (workspaceFragment == null) {
            workspaceFragment = new WorkspaceFragment();
        }

        if (myFragment == null) {
            myFragment = new MyFragment();
        }


        tabLayout = findViewById(R.id.tabLayout);

        for (int i = 0; i < title.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title[i]));
            fragments.add(frags[i]);
        }

        adapter = new MyAdapter(getSupportFragmentManager(), fragments, title);
        
        showMainFragment(messageFragment);


//        tabLayout.setupWithViewPager(viewPager);
        // 监听
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        showAndHideFragment(messageFragment);
                        break;
                    case 1:
                        showAndHideFragment(contactFragment);
                        break;
                    case 2:
                        showAndHideFragment(workspaceFragment);
                        break;
                    case 3:
                        showAndHideFragment(myFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // tab未被选中时回调
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // tab重新选择时回调
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }




    private void showMainFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        currentFragment = fragment;
        if (!fragment.isAdded()){
            transaction.add(R.id.fl_content, fragment);
        }else {
            transaction.show(fragment);
        }
        transaction.commit();
    }

    /**
     * 关于fragment栈管理得去熟悉了！！！
     * @param
     * @param fragment
     */
    private void showAndHideFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(currentFragment);
        currentFragment = fragment;
        if (!fragment.isAdded()){
            transaction.add(R.id.fl_content, fragment);
        }else {
            transaction.show(fragment);
        }
        transaction.commit();
    }




}
