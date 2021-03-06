package com.chenyang.cyandroidxproject.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.chenyang.cyandroidxproject.R;
import com.chenyang.cyandroidxproject.common.MyActivity;
import com.chenyang.cyandroidxproject.common.MyFragment;
import com.chenyang.cyandroidxproject.helper.ActivityStackManager;
import com.chenyang.cyandroidxproject.helper.DoubleClickHelper;
import com.chenyang.cyandroidxproject.ui.fragment.TestFragmentA;
import com.chenyang.cyandroidxproject.ui.fragment.TestFragmentB;
import com.chenyang.cyandroidxproject.ui.fragment.TestFragmentC;
import com.chenyang.cyandroidxproject.ui.fragment.TestFragmentD;
import com.chenyang.cyandroidxproject.utils.KeyboardWatcher;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hjq.base.BaseFragmentAdapter;

import butterknife.BindView;

public class HomeActivity extends MyActivity
        implements KeyboardWatcher.SoftKeyboardStateListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.vp_home_pager)
    ViewPager mViewPager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        KeyboardWatcher.with(this)
                .setListener(this);
    }

    @Override
    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(TestFragmentA.newInstance());
        mPagerAdapter.addFragment(TestFragmentB.newInstance());
        mPagerAdapter.addFragment(TestFragmentC.newInstance());
        mPagerAdapter.addFragment(TestFragmentD.newInstance());

        mViewPager.setAdapter(mPagerAdapter);

        // 限制页面数量
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
    }

    /**
     * {@link BottomNavigationView.OnNavigationItemSelectedListener}
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mPagerAdapter.setCurrentItem(TestFragmentA.class);
                return true;
            case R.id.home_found:
                mPagerAdapter.setCurrentItem(TestFragmentB.class);
                return true;
            case R.id.home_message:
                mPagerAdapter.setCurrentItem(TestFragmentC.class);
                return true;
            case R.id.home_me:
                mPagerAdapter.setCurrentItem(TestFragmentD.class);
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * {@link KeyboardWatcher.SoftKeyboardStateListener}
     */
    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        mBottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onSoftKeyboardClosed() {
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 回调当前 Fragment 的 onKeyDown 方法
        if (mPagerAdapter.getCurrentFragment().onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            // 移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            postDelayed(() -> {

                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities();
                // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                // System.exit(0);

            }, 300);
        } else {
            toast(R.string.home_exit_hint);
        }
    }

    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }
}
