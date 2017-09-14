package com.xes.yuedupractice.data.response;

import com.google.gson.annotations.SerializedName;
import com.xes.yuedupractice.data.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class TestBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = -3420897711974993692L;
    @SerializedName("body")
    private List<Product> data;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "data=" + data +
                '}';
    }

    private static class Product implements Serializable {
        private static final long serialVersionUID = 3353229379665877095L;
        private String title;// "新产品Y008调研"
        private String detail;// "针对公司新产品Y008的市场调研。需要来店里咨询的客户填写问卷，并留联系方式。问卷已发送至各位邮箱，请下载并打印。"
        private String publishdate;// "2016-08-15"
        private String executedate;// "2016-09-30"
        private String state;// 0

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getPublishdate() {
            return publishdate;
        }

        public void setPublishdate(String publishdate) {
            this.publishdate = publishdate;
        }

        public String getExecutedate() {
            return executedate;
        }

        public void setExecutedate(String executedate) {
            this.executedate = executedate;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "title='" + title + '\'' +
                    ", detail='" + detail + '\'' +
                    ", publishdate='" + publishdate + '\'' +
                    ", executedate='" + executedate + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }
    }

}
