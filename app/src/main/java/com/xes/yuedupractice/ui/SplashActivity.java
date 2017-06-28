package com.xes.yuedupractice.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.xes.yuedupractice.R;
import com.xes.yuedupractice.databinding.ActivitySplashBinding;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.xes.yuedupractice.utils.ConstantsUtils.TRANSITION_URLS;

public class SplashActivity extends AppCompatActivity {

    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mCompositeSubscription = new CompositeSubscription();
        final ActivitySplashBinding splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        splashBinding.ivDefultPic.setImageResource(R.mipmap.img_transition_default);
        int position = new Random().nextInt(TRANSITION_URLS.length);
        Glide.with(this)
                .load(TRANSITION_URLS[position])
                .placeholder(R.mipmap.img_transition_default)
                .error(R.mipmap.img_transition_default)
                .into(splashBinding.ivPic);
        RxView.clicks(splashBinding.tvJump)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        toMainActivity();
                        removeSubscription();
                    }
                });
        Subscription subscribe = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        splashBinding.ivDefultPic.setVisibility(View.GONE);
                    }
                });
        mCompositeSubscription.add(subscribe);
        Subscription subscription = Observable
                .just(3, 2, 1)
                .concatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return Observable.just(integer).delay(1000, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        splashBinding.tvJump.setText("跳过" + String.valueOf(integer));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        toMainActivity();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    private void removeSubscription() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    private void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
