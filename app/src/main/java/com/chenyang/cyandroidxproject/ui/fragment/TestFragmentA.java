package com.chenyang.cyandroidxproject.ui.fragment;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.chenyang.cyandroidxproject.R;
import com.chenyang.cyandroidxproject.aop.SingleClick;
import com.chenyang.cyandroidxproject.common.MyFragment;
import com.chenyang.cyandroidxproject.ui.activity.HomeActivity;
import com.chenyang.cyandroidxproject.ui.activity.LoadSirStudy;
import com.hjq.toast.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 项目炫酷效果示例
 */
public final class TestFragmentA extends MyFragment<HomeActivity> {

    public static TestFragmentA newInstance() {
        return new TestFragmentA();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_a;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.btn_one_click,R.id.btn_loadsir_study);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one_click:
                ToastUtils.show("单击事件");
                break;
            case R.id.btn_loadsir_study:
                startActivity(LoadSirStudy.class);
                break;
        }
    }

}