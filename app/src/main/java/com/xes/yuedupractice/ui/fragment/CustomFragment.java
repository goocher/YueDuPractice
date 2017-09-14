package com.xes.yuedupractice.ui.fragment;


import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.base.BaseLazyFragment;
import com.xes.yuedupractice.contract.CustormFragmentContract;
import com.xes.yuedupractice.databinding.FragmentCustomBinding;
import com.xes.yuedupractice.presenter.CustormFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomFragment extends BaseLazyFragment<FragmentCustomBinding, CustormFragmentPresenter> implements CustormFragmentContract.View {


    @Override
    public CustormFragmentPresenter setPresenter() {
        return new CustormFragmentPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void startLoad() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_custom;
    }

}
