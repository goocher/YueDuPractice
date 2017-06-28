package com.xes.yuedupractice.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/06/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<?> mList;
    private List<String> mTitle;


    public MainFragmentAdapter(FragmentManager fm, List<?> list) {
        super(fm);
        mList = list;
    }

    public void setTitle(List<String> title) {
        mTitle = title;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mList.get(position);
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitle != null) {
            return mTitle.get(position);
        } else {
            return "";
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
