package com.xes.yuedupractice.ui.fragment;


import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.base.BaseLazyFragment;
import com.xes.yuedupractice.contract.OneFragmentContract;
import com.xes.yuedupractice.databinding.FragmentOneBinding;
import com.xes.yuedupractice.presenter.OneFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends BaseLazyFragment<FragmentOneBinding,OneFragmentPresenter> implements OneFragmentContract.View {



    @Override
    public OneFragmentPresenter setPresenter() {
        return new OneFragmentPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void startLoad() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_one;
    }
}
