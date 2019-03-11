package com.jh.zhihuui.fragment.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jh.zhihuui.R;

import me.yokeyword.fragmentation.SupportFragment;
/**
 * Created by YoKeyword on 16/2/7.
 */
public class BaseBackFragment extends SupportFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
