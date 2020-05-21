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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class GridLayoutManagerStudyActivity extends MyActivity {
    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gridlayoutmanager_study;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        addData();
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(getActivity(),1), Utils.dip2px(getActivity(),1), Color.BLUE));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
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
        public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            HomeAdapter.MyViewHolder holder = new HomeAdapter.MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position)
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
