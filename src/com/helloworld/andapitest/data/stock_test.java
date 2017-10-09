package com.helloworld.andapitest.data;

import java.util.List;

/**
 * Created by babycomingin100days on 2017/9/4.
 */
public class stock_test {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"list":[{"trade_money":"99310000","diff_money":"-0.62","open_price":"12.68","code":"600004","date":"2015-09-01","min_price":"11.92","market":"sh","trade_num":"81119","turnover":"0.71","close_price":"12.11","max_price":"12.68","swing":"5.97","diff_rate":"-4.87"}]}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * list : [{"trade_money":"99310000","diff_money":"-0.62","open_price":"12.68","code":"600004","date":"2015-09-01","min_price":"11.92","market":"sh","trade_num":"81119","turnover":"0.71","close_price":"12.11","max_price":"12.68","swing":"5.97","diff_rate":"-4.87"}]
         */

        private int ret_code;
        private List<ListBean> list;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * trade_money : 99310000
             * diff_money : -0.62
             * open_price : 12.68
             * code : 600004
             * date : 2015-09-01
             * min_price : 11.92
             * market : sh
             * trade_num : 81119
             * turnover : 0.71
             * close_price : 12.11
             * max_price : 12.68
             * swing : 5.97
             * diff_rate : -4.87
             */

            private String trade_money;
            private String diff_money;
            private String open_price;
            private String code;
            private String date;
            private String min_price;
            private String market;
            private String trade_num;
            private String turnover;
            private String close_price;
            private String max_price;
            private String swing;
            private String diff_rate;

            public String getTrade_money() {
                return trade_money;
            }

            public void setTrade_money(String trade_money) {
                this.trade_money = trade_money;
            }

            public String getDiff_money() {
                return diff_money;
            }

            public void setDiff_money(String diff_money) {
                this.diff_money = diff_money;
            }

            public String getOpen_price() {
                return open_price;
            }

            public void setOpen_price(String open_price) {
                this.open_price = open_price;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getMin_price() {
                return min_price;
            }

            public void setMin_price(String min_price) {
                this.min_price = min_price;
            }

            public String getMarket() {
                return market;
            }

            public void setMarket(String market) {
                this.market = market;
            }

            public String getTrade_num() {
                return trade_num;
            }

            public void setTrade_num(String trade_num) {
                this.trade_num = trade_num;
            }

            public String getTurnover() {
                return turnover;
            }

            public void setTurnover(String turnover) {
                this.turnover = turnover;
            }

            public String getClose_price() {
                return close_price;
            }

            public void setClose_price(String close_price) {
                this.close_price = close_price;
            }

            public String getMax_price() {
                return max_price;
            }

            public void setMax_price(String max_price) {
                this.max_price = max_price;
            }

            public String getSwing() {
                return swing;
            }

            public void setSwing(String swing) {
                this.swing = swing;
            }

            public String getDiff_rate() {
                return diff_rate;
            }

            public void setDiff_rate(String diff_rate) {
                this.diff_rate = diff_rate;
            }
        }
    }
}
