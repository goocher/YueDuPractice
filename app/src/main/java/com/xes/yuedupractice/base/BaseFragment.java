package com.xes.yuedupractice.base;


import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.xes.yuedupractice.R;
import com.xes.yuedupractice.databinding.FragmentBaseBinding;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action1;
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
    private boolean mIsVisible;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLlProgressView = getView(R.id.ll_progress_bar);
        ImageView imageView = getView(R.id.img_progress);
        mAnimationDrawable = (AnimationDrawable) imageView.getDrawable();
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        mRefreshView = getView(R.id.ll_error_refresh);
        RxView.clicks(mRefreshView).throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showLoading();
                        onRefresh();
                    }
                });
        mDataBinding.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onInVisible();
        } else {
            mIsVisible = false;
            onVisible();
        }
    }

    public void onVisible() {
        loadData();
    }

    public void loadData() {

    }

    protected void onInVisible() {

    }

    public abstract int setContent();

    protected <T extends View> T getView(int layoutId) {
        return (T) getView().findViewById(layoutId);
    }

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

    public void addSubscriptions(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
