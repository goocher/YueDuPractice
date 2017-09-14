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

public interface IBaseView {
    void toast(String msg);

    void showLoadingDialog();

    void dismissDialog();

    void showContent();

    void showError();

}
