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

public interface IBasePresenter<T extends IBaseView> {
    void attachView(T iBaseView);

    void detachView();
}
