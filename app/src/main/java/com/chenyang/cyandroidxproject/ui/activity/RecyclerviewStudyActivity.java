package com.chenyang.cyandroidxproject.ui.activity;

import android.view.View;

import com.chenyang.cyandroidxproject.R;
import com.chenyang.cyandroidxproject.aop.SingleClick;
import com.chenyang.cyandroidxproject.common.MyActivity;
import com.hjq.toast.ToastUtils;

public class RecyclerviewStudyActivity extends MyActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview_study;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.btn_recyclerview_study,R.id.btn_recyclerview_study1);
    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_recyclerview_study:
                startActivity(LinearLayoutManagerStudyActivity.class);
                break;
            case R.id.btn_recyclerview_study1:
                startActivity(GridLayoutManagerStudyActivity.class);
                break;
        }
    }
}
