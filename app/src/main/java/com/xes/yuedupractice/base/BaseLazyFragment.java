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
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.xes.yuedupractice.R;
import com.xes.yuedupractice.databinding.FragmentBaseBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseLazyFragment<SV extends ViewDataBinding, P extends BasePresenter> extends Fragment implements IBaseView {
    protected SV mDataBinding;
    private LinearLayout mLlProgressView;
    protected RelativeLayout mContainer;
    private View mRefreshView;
    private FragmentBaseBinding mFragmentBaseBinding;
    private CompositeSubscription mCompositeSubscription;
    private Activity mActivity;
    private AnimationDrawable mAnimationDrawable;
    protected boolean prepared;
    protected boolean dataLoaded;
    protected P mPresenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, null, false);
        mDataBinding = DataBindingUtil.inflate(mActivity.getLayoutInflater(), setContent(), null, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mDataBinding.getRoot().setLayoutParams(layoutParams);
        mContainer = (RelativeLayout) mFragmentBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(mDataBinding.getRoot());
        mLlProgressView = (LinearLayout) mFragmentBaseBinding.getRoot().findViewById(R.id.ll_progress_bar);
        ImageView imageView = (ImageView) mFragmentBaseBinding.getRoot().findViewById(R.id.img_progress);
        mAnimationDrawable = (AnimationDrawable) imageView.getDrawable();
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        mRefreshView = mFragmentBaseBinding.getRoot().findViewById(R.id.ll_error_refresh);
        RxView.clicks(mRefreshView).throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showLoadingDialog();
                        onRefresh();
                    }
                });
        mDataBinding.getRoot().setVisibility(View.GONE);
        mPresenter = setPresenter();
        mPresenter.attachView(this);
        EventBus.getDefault().register(this);
        initView();
        return mFragmentBaseBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepared = true;
        lazyLoad();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        prepared = false;
        dataLoaded = false;
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public BaseLazyFragment() {
        // Required empty public constructor
    }

    public abstract P setPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    protected abstract void initView();


    private void lazyLoad() {
        if (!prepared) {
            return;
        }
        if (getUserVisibleHint() && !dataLoaded) {
            startLoad();
            dataLoaded = true;
        } else if (!getUserVisibleHint() && dataLoaded) {
            stopLoad();
        }
    }

    protected void stopLoad() {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //当可见的时候
        lazyLoad();
    }

    protected abstract void startLoad();

    public abstract int setContent();

    protected <T extends View> T getView(int layoutId) {
        return (T) getView().findViewById(layoutId);
    }

    protected void onRefresh() {

    }

    @Override
    public void showLoadingDialog() {
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

    @Override
    public void toast(String msg) {
        Toast.makeText(YueDuApplication.sYueDuApplication, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissDialog() {

    }

    public void showError() {
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

    @Override
    public void showContent() {
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void empty(Object objects) {

    }
}

