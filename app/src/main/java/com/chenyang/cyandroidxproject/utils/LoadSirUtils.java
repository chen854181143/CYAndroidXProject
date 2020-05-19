package com.chenyang.cyandroidxproject.utils;

import android.view.View;

import com.chenyang.cyandroidxproject.bean.HttpResult;
import com.chenyang.cyandroidxproject.callback.EmptyCallback;
import com.chenyang.cyandroidxproject.callback.ErrorCallback;
import com.chenyang.cyandroidxproject.callback.LoadingCallback;
import com.chenyang.cyandroidxproject.listener.OnNetReloadListener;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.Convertor;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

public class LoadSirUtils {

    /**LoadSir参数*/
    private final int SUCCESS_CODE = 0x00;
    private final int ERROR_CODE = 0x01;
    private final int EMPTY_CODE = 0x02;

    private LoadService loadService;
    private OnNetReloadListener mOnNetReloadListener;
    private static LoadSirUtils mLoadSirUtils = null;
    private LoadSirUtils () {

    }

    public static LoadSirUtils getInstance() {
        if (mLoadSirUtils == null) {
            synchronized (LoadSirUtils.class) {
                if (mLoadSirUtils == null) {
                    mLoadSirUtils = new LoadSirUtils();
                }
            }
        }
        return mLoadSirUtils;
    }

    public LoadService showLoadSir(View mView) {
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();
        loadService = loadSir.register(mView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mOnNetReloadListener.onNetReload();
            }
        }, new Convertor<HttpResult>() {
            @Override
            public Class<? extends Callback> map(HttpResult httpResult) {
                Class<? extends Callback> resultCode = SuccessCallback.class;
                switch (httpResult.getResultCode()) {
                    case SUCCESS_CODE:
                        resultCode = SuccessCallback.class;
                        break;
                    case EMPTY_CODE:
                        resultCode = EmptyCallback.class;
                        break;
                    case ERROR_CODE:
                        resultCode = ErrorCallback.class;
                        break;
                    default:
                        resultCode = ErrorCallback.class;
                }
                return resultCode;
            }
        });
        return loadService;
    }

    public void setOnNetReloadListener(OnNetReloadListener onNetReloadListener) {
        mOnNetReloadListener = onNetReloadListener;
    }

    public int getSUCCESS_CODE() {
        return SUCCESS_CODE;
    }

    public int getERROR_CODE() {
        return ERROR_CODE;
    }

    public int getEMPTY_CODE() {
        return EMPTY_CODE;
    }
}
