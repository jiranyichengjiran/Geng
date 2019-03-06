package example.com.geng.bean;

import java.util.List;

public class SouBean {

    /**
     * result : [{"commodityId":138,"commodityName":"秋男鞋时尚男士休闲鞋系带防磨脚男士皮鞋潮流休闲板鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/4/1.jpg","price":449,"saleNum":0},{"commodityId":156,"commodityName":"黑色加绒牛皮商务休闲皮鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/1/1.jpg","price":99,"saleNum":0},{"commodityId":153,"commodityName":"红蜻蜓季系带布洛克雕花牛皮革男士英伦商务正装皮鞋绅士商务鞋男士皮鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/px/5/1.jpg","price":239,"saleNum":0},{"commodityId":150,"commodityName":"秋季真皮系带男款婚鞋尖头英伦男士正装商务鞋男鞋男士皮鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/px/2/1.jpg","price":328,"saleNum":0},{"commodityId":158,"commodityName":"系带商务鞋休闲鞋皮鞋棉鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/3/1.jpg","price":99,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 138
         * commodityName : 秋男鞋时尚男士休闲鞋系带防磨脚男士皮鞋潮流休闲板鞋
         * masterPic : http://172.17.8.100/images/small/commodity/nx/nbx/4/1.jpg
         * price : 449
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
