package com.chenyang.cyandroidxproject.ui.fragment;

import com.chenyang.cyandroidxproject.R;
import com.chenyang.cyandroidxproject.common.MyFragment;
import com.chenyang.cyandroidxproject.ui.activity.HomeActivity;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 项目框架使用示例
 */
public final class TestFragmentC extends MyFragment<HomeActivity> {

    public static TestFragmentC newInstance() {
        return new TestFragmentC();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_c;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

}