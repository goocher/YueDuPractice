package com.xes.yuedupractice.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.xes.yuedupractice.R;
import com.xes.yuedupractice.databinding.ActivityBaseBinding;
import com.xes.yuedupractice.utils.CommonUtils;
import com.xes.yuedupractice.utils.StatusBarUtils;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/06/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BaseActivity<SV extends ViewDataBinding> extends AppCompatActivity {
    private SV mDataBinding;
    private CompositeSubscription mCompositeSubscription;
    private LinearLayout llProgressView;
    private View mRefreshView;
    private ActivityBaseBinding mBaseBinding;
    private AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
        //contentView
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mDataBinding.getRoot().setLayoutParams(layoutParams);
        RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(mDataBinding.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

        // 设置透明状态栏
        StatusBarUtils.setColor(this, CommonUtils.getColor(R.color.colorTheme), 0);
        llProgressView = getView(R.id.ll_progress_bar);
        mRefreshView = getView(R.id.ll_error_refresh);
        ImageView img = getView(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        setToolBar();
        // 点击加载失败布局
        RxView.clicks(mRefreshView).throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        onRefresh();
                        showLoading();
                    }
                });
        mDataBinding.getRoot().setVisibility(View.GONE);
    }

    protected <T> T getView(int layoutId) {
        return (T) findViewById(layoutId);
    }

    protected void showLoading() {
        if (llProgressView.getVisibility() != View.VISIBLE) {
            llProgressView.setVisibility(View.VISIBLE);
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
        if (llProgressView.getVisibility() != View.GONE) {
            llProgressView.setVisibility(View.GONE);
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
        if (llProgressView.getVisibility() != View.GONE) {
            llProgressView.setVisibility(View.GONE);
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

    protected void onRefresh() {

    }

    public void setTitle(CharSequence title) {
        mBaseBinding.toolBar.setTitle(title);
    }

    protected void setToolBar() {
        setSupportActionBar(mBaseBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }
    }

    public void addSubscribetion(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
