package com.xes.yuedupractice.presenter;

import com.xes.yuedupractice.base.BasePresenter;
import com.xes.yuedupractice.contract.BookFragmentContract;
import com.xes.yuedupractice.model.BookFragmentModel;
import com.xes.yuedupractice.ui.fragment.BookFragment;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BookFragmentPresenter extends BasePresenter<BookFragment> implements BookFragmentContract.Presenter {

    private BookFragmentModel mModel;

    @Override
    public void attachView(BookFragment view) {
        super.attachView(view);
        mModel = new BookFragmentModel();
    }

    public void getData(String s) {
        mView.toast(s);
    }
}
