package com.ironghui.mydrawlayoutdemo.showpart;

import java.util.List;

public class PartBean {

    /**
     * msg : success
     * result : 000
     * testDetails : [{"isView":true,"name":"asdfsd","sort":1,"testDetailId":"1029977580582731776","testId":"1029972089827753984","type":"1"},{"isView":true,"name":"eee","sort":2,"testDetailId":"1029982548807122944","testId":"1029972089827753984","type":"2"},{"isView":true,"name":"dfas","sort":3,"testDetailId":"1029982618638090240","testId":"1029972089827753984","type":"3"},{"isView":true,"name":"sasf","sort":4,"testDetailId":"1029982701756612608","testId":"1029972089827753984","type":"4"},{"isView":true,"name":"fdsg","sort":5,"testDetailId":"1029988287613239296","testId":"1029972089827753984","type":"3"}]
     */

    private String msg;
    private String result;
    private List<TestDetailsBean> testDetails;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<TestDetailsBean> getTestDetails() {
        return testDetails;
    }

    public void setTestDetails(List<TestDetailsBean> testDetails) {
        this.testDetails = testDetails;
    }

    public static class TestDetailsBean {
        /**
         * isView : true
         * name : asdfsd
         * sort : 1
         * testDetailId : 1029977580582731776
         * testId : 1029972089827753984
         * type : 1
         */

        private boolean isView;
        private String name;
        private int sort;
        private String testDetailId;
        private String testId;
        private String type;

        public boolean isIsView() {
            return isView;
        }

        public void setIsView(boolean isView) {
            this.isView = isView;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTestDetailId() {
            return testDetailId;
        }

        public void setTestDetailId(String testDetailId) {
            this.testDetailId = testDetailId;
        }

        public String getTestId() {
            return testId;
        }

        public void setTestId(String testId) {
            this.testId = testId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
