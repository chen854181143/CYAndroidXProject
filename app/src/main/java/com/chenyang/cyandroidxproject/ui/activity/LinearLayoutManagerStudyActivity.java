package com.chenyang.cyandroidxproject.ui.activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyang.cyandroidxproject.R;
import com.chenyang.cyandroidxproject.common.MyActivity;
import com.chenyang.cyandroidxproject.utils.Utils;
import com.chenyang.cyandroidxproject.utils.decoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class LinearLayoutManagerStudyActivity extends MyActivity {

    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_linearlayoutmanager_study;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        addData();
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(getActivity(),10), Utils.dip2px(getActivity(),10), Color.BLUE));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
    }

    protected void addData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }

}
