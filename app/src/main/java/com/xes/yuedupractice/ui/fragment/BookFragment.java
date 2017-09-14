package com.xes.yuedupractice.ui.fragment;


import android.support.v4.app.Fragment;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.base.BaseLazyFragment;
import com.xes.yuedupractice.contract.BookFragmentContract;
import com.xes.yuedupractice.databinding.FragmentBookBinding;
import com.xes.yuedupractice.presenter.BookFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseLazyFragment<FragmentBookBinding, BookFragmentPresenter> implements BookFragmentContract.View {

    @Override
    public BookFragmentPresenter setPresenter() {
        return new BookFragmentPresenter();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void startLoad() {
        mPresenter.getData("12345");
    }


    @Override
    public int setContent() {
        return R.layout.fragment_book;
    }
}
