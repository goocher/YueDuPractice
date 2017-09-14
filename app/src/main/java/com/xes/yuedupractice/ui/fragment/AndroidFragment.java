package com.xes.yuedupractice.ui.fragment;


import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.base.BaseLazyFragment;
import com.xes.yuedupractice.contract.AndroidContract;
import com.xes.yuedupractice.databinding.FragmentAndroidBinding;
import com.xes.yuedupractice.presenter.AndroidPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends BaseLazyFragment<FragmentAndroidBinding, AndroidPresenter> implements AndroidContract.View {

    public static AndroidFragment getInstance(String tag) {
        AndroidFragment fragment = new AndroidFragment();
        return fragment;
    }

    @Override
    public AndroidPresenter setPresenter() {
        return new AndroidPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void startLoad() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_android;
    }

}
