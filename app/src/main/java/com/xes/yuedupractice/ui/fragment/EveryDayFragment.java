package com.xes.yuedupractice.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.base.BaseLazyFragment;
import com.xes.yuedupractice.contract.EveryDayContract;
import com.xes.yuedupractice.data.response.TestBean;
import com.xes.yuedupractice.databinding.FragmentEveryDayBinding;
import com.xes.yuedupractice.presenter.EveryDayPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EveryDayFragment extends BaseLazyFragment<FragmentEveryDayBinding, EveryDayPresenter> implements EveryDayContract.View {

    @Override
    public EveryDayPresenter setPresenter() {
        return new EveryDayPresenter();
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void startLoad() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            TestBean test = (TestBean) bundle.getSerializable("test");
            mDataBinding.tvTest.setText(test.toString());
            showContent();
        } else {
            showError();
        }
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();

    }

    @Override
    public int setContent() {
        return R.layout.fragment_every_day;
    }
}
