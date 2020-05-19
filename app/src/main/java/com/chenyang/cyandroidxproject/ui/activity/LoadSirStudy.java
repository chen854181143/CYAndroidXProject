package com.chenyang.cyandroidxproject.ui.activity;

import android.os.Handler;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.chenyang.cyandroidxproject.R;
import com.chenyang.cyandroidxproject.bean.HttpResult;
import com.chenyang.cyandroidxproject.callback.ErrorCallback;
import com.chenyang.cyandroidxproject.callback.LoadingCallback;
import com.chenyang.cyandroidxproject.common.MyActivity;
import com.chenyang.cyandroidxproject.listener.OnNetReloadListener;
import com.chenyang.cyandroidxproject.utils.LoadSirUtils;
import com.hjq.toast.ToastUtils;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import butterknife.BindView;

public class LoadSirStudy extends MyActivity implements OnNetReloadListener {

    @BindView(R.id.loadsir)
    LinearLayoutCompat mLoadsir;

    private LoadService loadService;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_load_sir_study;
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
