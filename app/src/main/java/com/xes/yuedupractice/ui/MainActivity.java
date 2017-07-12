package com.xes.yuedupractice.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xes.yuedupractice.R;
import com.xes.yuedupractice.adapter.MainFragmentAdapter;
import com.xes.yuedupractice.databinding.ActivityMainBinding;
import com.xes.yuedupractice.databinding.NavHeaderMainBinding;
import com.xes.yuedupractice.ui.fragment.BookFragment;
import com.xes.yuedupractice.ui.fragment.GankFragment;
import com.xes.yuedupractice.ui.fragment.OneFragment;
import com.xes.yuedupractice.utils.CommonUtils;
import com.xes.yuedupractice.utils.ConstantsImageUrl;
import com.xes.yuedupractice.utils.ImgLoadUtils;
import com.xes.yuedupractice.utils.RxBus;
import com.xes.yuedupractice.utils.RxBusBaseMessage;
import com.xes.yuedupractice.utils.RxCodeConstants;
import com.xes.yuedupractice.utils.SPUtils;
import com.xes.yuedupractice.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

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
    private NavHeaderMainBinding mBind;

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
        mNavView.inflateHeaderView(R.layout.nav_header_main);
        mBind = DataBindingUtil.bind(mNavView.getHeaderView(0));
        mBind.setListener(this);
        ImgLoadUtils.displayCircle(mBind.ivAvatar, ConstantsImageUrl.IC_AVATAR);
        mBind.dayNightSwitch.setChecked(SPUtils.getNightMode());
        mBind.llNavAbout.setOnClickListener(listener);
        mBind.llNavDeedback.setOnClickListener(listener);
        mBind.llNavHomepage.setOnClickListener(listener);
        mBind.llNavLogin.setOnClickListener(listener);
        mBind.llNavScanDownload.setOnClickListener(listener);
        mBind.llNavExit.setOnClickListener(this);
        mBind.ivAvatar.setOnClickListener(this);
    }

    private void initListener() {
        mIvTitleDou.setOnClickListener(this);
        mIvTitleOne.setOnClickListener(this);
        mIvTitleGank.setOnClickListener(this);
        mIvTitleMenu.setOnClickListener(this);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            mMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
            mMainBinding.drawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (v.getId()) {

                    }
                }
            },500);
        }
    };

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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                setIvTitleSelect(mIvTitleGank);
                break;
            case 1:
                setIvTitleSelect(mIvTitleOne);
                break;
            case 2:
                setIvTitleSelect(mIvTitleDou);
                break;
        }
    }

    private void setIvTitleSelect(View select) {
        mIvTitleGank.setSelected(false);
        mIvTitleOne.setSelected(false);
        mIvTitleDou.setSelected(false);
        select.setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_gank:
                mVpContent.setCurrentItem(0);
                break;
            case R.id.iv_title_one:
                mVpContent.setCurrentItem(1);
                break;
            case R.id.iv_title_dou:
                mVpContent.setCurrentItem(2);
                break;
            case R.id.iv_title_menu:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    /**
     * 夜间模式待完善
     */
    public boolean getNightMode() {
        return SPUtils.getNightMode();
    }

    public void onNightModeClick(View view) {
        if (!SPUtils.getNightMode()) {
//            SkinCompatManager.getInstance().loadSkin(Constants.NIGHT_SKIN);
        } else {
            // 恢复应用默认皮肤
//            SkinCompatManager.getInstance().restoreDefaultTheme();
        }
        SPUtils.setNightMode(!SPUtils.getNightMode());
        mBind.dayNightSwitch.setChecked(SPUtils.getNightMode());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                mMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
