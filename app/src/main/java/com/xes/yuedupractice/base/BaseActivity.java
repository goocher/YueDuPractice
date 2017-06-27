package com.xes.yuedupractice.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public <T> T getView(int layoutId) {
        return (T) findViewById(layoutId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
