package com.jh.zhihuui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jh.zhihuui.demo.zhihu.ZhiHuActivity;

/**
 * 单activity、 多fragment
 *
 * A powerful library that manage Fragment for Android!
 *
 * 为"单Activity ＋ 多Fragment","多模块Activity + 多Fragment"架构而生，简化开发，轻松解决动画、嵌套、事务相关等问题。
 *
 * https://github.com/YoKeyword/Fragmentation
 */
public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter);

        initView();
    }

    private void initView() {

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        findViewById(R.id.btn_flow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(EnterActivity.this, ZhiHuActivity.class));
                Toast.makeText(EnterActivity.this, "btn_flow", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(EnterActivity.this,ZhiHuActivity.class));
                Toast.makeText(EnterActivity.this, "btn_wechat", Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.btn_zhihu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterActivity.this, ZhiHuActivity.class));
            }
        });

    }
}
