package com.xes.yuedupractice.presenter;

import com.xes.yuedupractice.base.BasePresenter;
import com.xes.yuedupractice.contract.GankFragmentContract;
import com.xes.yuedupractice.data.response.TestBean;
import com.xes.yuedupractice.model.GankFragmentModel;
import com.xes.yuedupractice.ui.fragment.GankFragment;

import rx.functions.Action1;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class GankFragmentPresenter extends BasePresenter<GankFragment> implements GankFragmentContract.Presenter {

    private GankFragmentModel mModel;

    @Override
    public void attachView(GankFragment view) {
        super.attachView(view);
        mModel = new GankFragmentModel();
    }

    @Override
    public void start() {
        mModel.getTestData().subscribe(new Action1<TestBean>() {
            @Override
            public void call(TestBean testBean) {
                mView.setTestData(testBean);
            }
        });
    }
}
