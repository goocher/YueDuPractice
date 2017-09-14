package com.xes.yuedupractice.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.adapter.GankFragmentAdapter;
import com.xes.yuedupractice.base.BaseLazyFragment;
import com.xes.yuedupractice.contract.GankFragmentContract;
import com.xes.yuedupractice.data.response.TestBean;
import com.xes.yuedupractice.databinding.FragmentGankBinding;
import com.xes.yuedupractice.presenter.GankFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseLazyFragment<FragmentGankBinding, GankFragmentPresenter> implements GankFragmentContract.View {

    private EveryDayFragment mEveryDayFragment;
    private WelfareFragment mWelfareFragment;
    private CustomFragment mCustomFragment;

    @Override
    public int setContent() {
        return R.layout.fragment_gank;
    }

    @Override
    public GankFragmentPresenter setPresenter() {
        return new GankFragmentPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void startLoad() {
        mPresenter.start();
    }

    private void initFragments(Bundle bundle) {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        titles.add(getString(R.string.everyday));
        titles.add(getString(R.string.welfare));
        titles.add(getString(R.string.custom));
        titles.add(getString(R.string.android));
        mEveryDayFragment = new EveryDayFragment();
        mEveryDayFragment.setArguments(bundle);
        fragments.add(mEveryDayFragment);
        mWelfareFragment = new WelfareFragment();
        fragments.add(mWelfareFragment);
        mCustomFragment = new CustomFragment();
        fragments.add(mCustomFragment);
        fragments.add(AndroidFragment.getInstance("android"));
        GankFragmentAdapter adapter = new GankFragmentAdapter(getChildFragmentManager(), fragments, titles);
        mDataBinding.vpGankContent.setAdapter(adapter);
        mDataBinding.tlGankTitle.setupWithViewPager(mDataBinding.vpGankContent);
    }


    @Override
    public void setTestData(TestBean testData) {
        showContent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("test", testData);
        initFragments(bundle);
    }
}
