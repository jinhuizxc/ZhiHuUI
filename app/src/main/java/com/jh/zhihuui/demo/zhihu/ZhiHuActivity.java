package com.jh.zhihuui.demo.zhihu;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.jh.zhihuui.R;
import com.jh.zhihuui.demo.zhihu.ui.fragment.ContactFragmentNew;
import com.jh.zhihuui.demo.zhihu.ui.fragment.WorkspaceFragmentNew;
import com.jh.zhihuui.demo.zhihu.ui.view.BottomBar;
import com.jh.zhihuui.demo.zhihu.ui.view.BottomBarTab;
import com.jh.zhihuui.event.TabSelectedEvent;
import com.jh.zhihuui.fragment.contact.ContactFragment;
import com.jh.zhihuui.fragment.WorkspaceFragment;
import com.jh.zhihuui.fragment.base.BaseMainFragment;
import com.jh.zhihuui.fragment.first.FirstHomeFragment;
import com.jh.zhihuui.fragment.first.ZhihuFirstFragment;
import com.jh.zhihuui.fragment.four.MeFragment;
import com.jh.zhihuui.fragment.four.ZhihuFourthFragment;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 类知乎 复杂嵌套Demo tip: 多使用右上角的"查看栈视图"
 */
public class ZhiHuActivity extends SupportActivity implements BaseMainFragment.OnBackToFirstListener{

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_main);

        SupportFragment firstFragment = findFragment(ZhihuFirstFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = ZhihuFirstFragment.newInstance();
            mFragments[SECOND] = ContactFragment.newInstance();
            mFragments[THIRD] = WorkspaceFragment.newInstance();
            mFragments[FOURTH] = ZhihuFourthFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(ContactFragment.class);
            mFragments[THIRD] = findFragment(WorkspaceFragment.class);
            mFragments[FOURTH] = findFragment(ZhihuFourthFragment.class);
        }

        initView();

    }

    private void initView() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_home_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_discover_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_message_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_account_circle_white_24dp));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                final SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof ZhihuFirstFragment) {
                        currentFragment.popToChild(FirstHomeFragment.class, false);
                    } else if (currentFragment instanceof ContactFragment) {
                        currentFragment.popToChild(ContactFragmentNew.class, false);
                    } else if (currentFragment instanceof WorkspaceFragment) {
                        currentFragment.popToChild(WorkspaceFragmentNew.class, false);
                    } else if (currentFragment instanceof ZhihuFourthFragment) {
                        currentFragment.popToChild(MeFragment.class, false);
                    }
                    return;
                }


                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    EventBusActivityScope.getDefault(ZhiHuActivity.this).post(new TabSelectedEvent(position));
                }
            }
        });

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
//            Toast.makeText(this, "back 1", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.finishAfterTransition(this);
//            Toast.makeText(this, "back 2", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }
}
