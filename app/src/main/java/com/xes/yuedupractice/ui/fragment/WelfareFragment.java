package com.xes.yuedupractice.ui.fragment;


import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.base.BaseLazyFragment;
import com.xes.yuedupractice.contract.WelfareFragmentContract;
import com.xes.yuedupractice.databinding.FragmentWelfareBinding;
import com.xes.yuedupractice.presenter.WelfareFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelfareFragment extends BaseLazyFragment<FragmentWelfareBinding,WelfareFragmentPresenter> implements WelfareFragmentContract.View {


    @Override
    public WelfareFragmentPresenter setPresenter() {
        return new WelfareFragmentPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void startLoad() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_welfare;
    }

}
