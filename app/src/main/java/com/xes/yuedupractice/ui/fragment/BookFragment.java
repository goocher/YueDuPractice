package com.xes.yuedupractice.ui.fragment;


import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.base.BaseFragment;
import com.xes.yuedupractice.databinding.FragmentBookBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment<FragmentBookBinding> {


    public BookFragment() {
        // Required empty public constructor
    }

    @Override
    protected void loadData() {

    }


    @Override
    public int setContent() {
        return R.layout.fragment_book;
    }
}
