package com.xes.yuedupractice.net;

import com.xes.yuedupractice.data.BaseResult;
import com.xes.yuedupractice.data.response.TestBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
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

public interface ApiService {
    @GET("visitshop/task")
    Observable<BaseResult<List<TestBean>>> getTestData(@Query("pagenum") int pageNum);
}
