package com.xes.yuedupractice.ui.fragment;


import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.adapter.GankFragmentAdapter;
import com.xes.yuedupractice.base.BaseFragment;
import com.xes.yuedupractice.databinding.FragmentGankBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment<FragmentGankBinding> {

    private List<String> mTitles;
    private List<Fragment> mFragments;

    @Override
    public int setContent() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView() {
        initFragments();
    }

    @Override
    protected void loadData() {

    }

    private void initFragments() {
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitles.add("每日推荐");
        mTitles.add("福利");
        mTitles.add("干货定制");
        mTitles.add("大安卓");
        mFragments.add(new EveryDayFragment());
        mFragments.add(new WelfareFragment());
        mFragments.add(new CustomFragment());
        mFragments.add(AndroidFragment.getInstance("android"));
        mDataBinding.vpGankContent.setOffscreenPageLimit(3);
        GankFragmentAdapter adapter = new GankFragmentAdapter(getChildFragmentManager(), mFragments, mTitles);

    }
}
