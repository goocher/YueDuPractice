package com.xes.yuedupractice.model;

import com.xes.yuedupractice.base.YueDuApplication;
import com.xes.yuedupractice.contract.GankFragmentContract;
import com.xes.yuedupractice.data.BaseResult;
import com.xes.yuedupractice.data.response.TestBean;
import com.xes.yuedupractice.net.RetrofitClient;
import com.xes.yuedupractice.utils.CommonUtils;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class GankFragmentModel implements GankFragmentContract.Model {
    @Override
    public Observable<BaseResult<List<TestBean>>> start() {

        return RetrofitClient.getApiService().getTestData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TestBean> getTestData() {
        String json = CommonUtils.getJson(YueDuApplication.sYueDuApplication, "test.json");
        TestBean testBean = CommonUtils.jsonToBean(json, TestBean.class);
        return Observable.just(testBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
