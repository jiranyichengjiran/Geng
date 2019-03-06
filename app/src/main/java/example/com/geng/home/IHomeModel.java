package example.com.geng.home;

import java.io.File;
import java.util.HashMap;

public interface IHomeModel {
    //登录
    void mLogin(String phone, String pwd, HomeModelCallBack callBack);

    //注册
    void mRegin(String phone, String pwd, HomeModelCallBack callBack);

    //首页
    void mLieData(HomeModelCallBack callBack);

    //banner
    void mBanData(HomeModelCallBack callBack);

    //详情
    void mXiang(String id, HomeModelCallBack callBack);

    //圈子
    void MQuan(int page, int count, HomeModelCallBack callBack);

    void mSou(String keyword, int page, int count, HomeModelCallBack callBack);

    void mGetCarDataFirst(HashMap<String, Object> map, HomeModelCallBack callBack);

    //加入购物车
    void mAddCar(HashMap<String, Object> map, String data, HomeModelCallBack callBack);

    void mGetCarData(HashMap<String, Object> map, HomeModelCallBack callBack);

    void mGetOne(HomeModelCallBack callBack);

    void mGetTwo(String id, HomeModelCallBack callBack);

    //新增地址
    void mAddAdress(HashMap<String, Object> headmap, HashMap<String, Object> map, HomeModelCallBack callBack);

    void mQuaryAddress(HashMap<String, Object> map, HomeModelCallBack callBack);

    void mCreateOrder(HashMap<String, Object> headmap, String data, int price, int addressId, HomeModelCallBack callBack);

    void mFaBu(HashMap<String, Object> headmap, int id, String content, File file, HomeModelCallBack callBack);

    void mOrderList(HashMap<String, Object> headmap, int status, int page, int count, HomeModelCallBack callBack);

    void mUpdateName(HashMap<String, Object> headmap, String name, HomeModelCallBack callBack);

    void mGetFootData(HashMap<String, Object> headmap, int page, int count, HomeModelCallBack callBack);

    void mUpdatePwd(HashMap<String, Object> headmap, String oldPwd, String newPwd, HomeModelCallBack callBack);

    public interface HomeModelCallBack {
        void success(Object o);

        void onFailed();
    }

}
