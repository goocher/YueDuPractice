package com.xes.yuedupractice.presenter;

import com.xes.yuedupractice.base.BasePresenter;
import com.xes.yuedupractice.contract.AndroidContract;
import com.xes.yuedupractice.model.AndroidModel;
import com.xes.yuedupractice.ui.fragment.AndroidFragment;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class AndroidPresenter extends BasePresenter<AndroidFragment> implements AndroidContract.Presenter {

    private AndroidModel mModel;

    @Override
    public void attachView(AndroidFragment view) {
        super.attachView(view);
        mModel = new AndroidModel();
    }

}
