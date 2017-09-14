package com.xes.yuedupractice.base;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {


    protected T mView;


    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }

    protected T getMvpView() {
        checkViewAttached();
        return mView;
    }

    private void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        private static final long serialVersionUID = 2443459480929610110L;

        MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
