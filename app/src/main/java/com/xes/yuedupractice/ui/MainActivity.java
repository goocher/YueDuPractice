package com.xes.yuedupractice.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.adapter.MainFragmentAdapter;
import com.xes.yuedupractice.databinding.ActivityMainBinding;
import com.xes.yuedupractice.ui.fragment.BookFragment;
import com.xes.yuedupractice.ui.fragment.GankFragment;
import com.xes.yuedupractice.ui.fragment.OneFragment;
import com.xes.yuedupractice.utils.CommonUtils;
import com.xes.yuedupractice.utils.RxBus;
import com.xes.yuedupractice.utils.RxBusBaseMessage;
import com.xes.yuedupractice.utils.RxCodeConstants;
import com.xes.yuedupractice.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ActivityMainBinding mMainBinding;
    private FloatingActionButton mFloatingActionButton;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;
    private Toolbar mToolbar;
    private ViewPager mVpContent;
    private ImageView mIvTitleDou;
    private ImageView mIvTitleGank;
    private ImageView mIvTitleMenu;
    private ImageView mIvTitleOne;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();

    }

    private void init() {
        initStatusBar();
        initViews();
        initRxBus();
        StatusBarUtils.setColorNoTranslucentForDrawerLayout(this, mDrawerLayout, CommonUtils.getColor(R.color.colorTheme));
        initContentFragment();
        initDrawerLayout();
        initListener();
    }

    private void initDrawerLayout() {

    }

    private void initListener() {

    }

    private void initContentFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new GankFragment());
        mFragments.add(new OneFragment());
        mFragments.add(new BookFragment());
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager(), mFragments);
        mVpContent.setAdapter(adapter);
        mVpContent.setOffscreenPageLimit(2);
        mVpContent.addOnPageChangeListener(this);
        mVpContent.setCurrentItem(0);
        mIvTitleGank.setSelected(true);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initRxBus() {
        RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE_TO_ONE, RxBusBaseMessage.class)
                .subscribe(new Action1<RxBusBaseMessage>() {
                    @Override
                    public void call(RxBusBaseMessage integer) {
                        mVpContent.setCurrentItem(1);
                    }
                });
    }

    private void initViews() {
        mFloatingActionButton = mMainBinding.include.fab;
        mDrawerLayout = mMainBinding.drawerLayout;
        mNavView = mMainBinding.navView;
        mToolbar = mMainBinding.include.toolbar;
        mVpContent = mMainBinding.include.vpContent;
        mIvTitleDou = mMainBinding.include.ivTitleDou;
        mIvTitleGank = mMainBinding.include.ivTitleGank;
        mIvTitleMenu = mMainBinding.include.ivTitleMenu;
        mIvTitleOne = mMainBinding.include.ivTitleOne;
    }

    private void initStatusBar() {
        ViewGroup.LayoutParams layoutParams = mMainBinding.include.viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtils.getStatusBarHeight(this);
        mMainBinding.include.viewStatus.setLayoutParams(layoutParams);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
