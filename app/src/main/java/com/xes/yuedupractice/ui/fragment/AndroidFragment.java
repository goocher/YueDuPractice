package com.xes.yuedupractice.ui.fragment;


import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.base.BaseFragment;
import com.xes.yuedupractice.databinding.FragmentAndroidBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends BaseFragment<FragmentAndroidBinding> {

    public static AndroidFragment getInstance(String tag) {
        AndroidFragment fragment = new AndroidFragment();
        return fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public int setContent() {
        return R.layout.fragment_android;
    }

}
