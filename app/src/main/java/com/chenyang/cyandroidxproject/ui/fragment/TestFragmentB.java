package com.chenyang.cyandroidxproject.ui.fragment;

import android.os.Handler;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.chenyang.cyandroidxproject.R;
import com.chenyang.cyandroidxproject.callback.ErrorCallback;
import com.chenyang.cyandroidxproject.callback.LoadingCallback;
import com.chenyang.cyandroidxproject.common.MyFragment;
import com.chenyang.cyandroidxproject.listener.OnNetReloadListener;
import com.chenyang.cyandroidxproject.ui.activity.HomeActivity;
import com.chenyang.cyandroidxproject.utils.LoadSirUtils;
import com.hjq.toast.ToastUtils;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadService;

import butterknife.BindView;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 项目自定义控件展示
 */
public final class TestFragmentB extends MyFragment<HomeActivity> implements OnNetReloadListener {

    @BindView(R.id.loadsir)
    LinearLayoutCompat mLoadsir;

    private LoadService loadService;

    public static TestFragmentB newInstance() {
        return new TestFragmentB();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_b;
    }

    @Override
    protected void initView() {
        loadService = LoadSirUtils.getInstance().showLoadSir(mLoadsir);
        LoadSirUtils.getInstance().setOnNetReloadListener(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showCallback(ErrorCallback.class);
            }
        }, 500);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public void onNetReload() {
        ToastUtils.show("点击我了");
        loadService.showCallback(LoadingCallback.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showCallback(SuccessCallback.class);
            }
        }, 500);
    }
}