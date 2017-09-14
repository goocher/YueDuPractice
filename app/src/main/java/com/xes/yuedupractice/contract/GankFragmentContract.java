package com.xes.yuedupractice.contract;

import com.xes.yuedupractice.data.BaseResult;
import com.xes.yuedupractice.data.response.TestBean;

import java.util.List;

import rx.Observable;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface GankFragmentContract {
    interface Model {
        Observable<BaseResult<List<TestBean>>> start();
        Observable<TestBean> getTestData();
    }

    interface View {
        void setTestData(TestBean testData);
    }

    interface Presenter {
        void start();
    }
}
