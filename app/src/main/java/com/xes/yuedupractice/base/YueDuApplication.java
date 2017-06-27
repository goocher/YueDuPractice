package com.xes.yuedupractice.base;

import android.app.Application;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/06/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class YueDuApplication extends Application {
    public static YueDuApplication sYueDuApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sYueDuApplication = this;
    }

}
