package com.xes.yuedupractice.base;


import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.databinding.FragmentBaseBinding;

import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<SV extends ViewDataBinding> extends Fragment {
    private SV mDataBinding;
    private LinearLayout mLlProgressView;
    private RelativeLayout mContainer;
    private View mRefreshView;
    private CompositeSubscription mCompositeSubscription;
    private Activity mActivity;
    private FragmentBaseBinding mFragmentBaseBinding;
    private AnimationDrawable mAnimationDrawable;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        mDataBinding = DataBindingUtil.inflate(mActivity.getLayoutInflater(), setContent(), null, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mDataBinding.getRoot().setLayoutParams(layoutParams);
        mContainer = (RelativeLayout) mFragmentBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(mDataBinding.getRoot());
        return view;
    }

    public abstract int setContent();

    protected void onRefresh() {

    }

    protected void showLoading() {
        if (mLlProgressView.getVisibility() != View.VISIBLE) {
            mLlProgressView.setVisibility(View.VISIBLE);
        }
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (mDataBinding.getRoot().getVisibility() != View.GONE) {
            mDataBinding.getRoot().setVisibility(View.GONE);
        }
        if (mRefreshView.getVisibility() != View.GONE) {
            mRefreshView.setVisibility(View.GONE);
        }
    }

    protected void showError() {
        if (mLlProgressView.getVisibility() != View.GONE) {
            mLlProgressView.setVisibility(View.GONE);
        }
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefreshView.getVisibility() != View.VISIBLE) {
            mRefreshView.setVisibility(View.VISIBLE);
        }
        if (mDataBinding.getRoot().getVisibility() != View.GONE) {
            mDataBinding.getRoot().setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (mLlProgressView.getVisibility() != View.GONE) {
            mLlProgressView.setVisibility(View.GONE);
        }
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefreshView.getVisibility() != View.GONE) {
            mRefreshView.setVisibility(View.GONE);
        }
        if (mDataBinding.getRoot().getVisibility() != View.VISIBLE) {
            mDataBinding.getRoot().setVisibility(View.VISIBLE);
        }
    }
}
