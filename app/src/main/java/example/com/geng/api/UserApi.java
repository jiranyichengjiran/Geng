package example.com.geng.api;

import java.io.File;
import java.util.HashMap;

import example.com.geng.bean.AddAddress;
import example.com.geng.bean.BannerBean;
import example.com.geng.bean.CarBean;
import example.com.geng.bean.CreateOrder;
import example.com.geng.bean.FaBu;
import example.com.geng.bean.LoginBean;
import example.com.geng.bean.MyFoot;
import example.com.geng.bean.MyWalletBean;
import example.com.geng.bean.OneBean;
import example.com.geng.bean.OrderList;
import example.com.geng.bean.QuanBean;
import example.com.geng.bean.RecivieAddress;
import example.com.geng.bean.ReginBean;
import example.com.geng.bean.ShouBean;
import example.com.geng.bean.SouBean;
import example.com.geng.bean.TongBuBean;
import example.com.geng.bean.TwoBean;
import example.com.geng.bean.UpdatePwd;
import example.com.geng.bean.XiangBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface UserApi {

    @POST("small/user/v1/login")
    Observable<LoginBean> login(@Query("phone") String phone, @Query("pwd") String pwd);

    @POST("small/user/v1/register")
    Observable<ReginBean> regin(@Query("phone") String phone, @Query("pwd") String pwd);

    @GET("small/commodity/v1/commodityList")
    Observable<ShouBean> getShouData();

    @GET("small/commodity/v1/bannerShow")
    Observable<BannerBean> getBanData();

    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<XiangBean> zhan(@Query("commodityId") String id);

    @GET("small/circle/v1/findCircleList")
    Observable<QuanBean> getQuanData(@Query("page") int page, @Query("count") int count);

    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<SouBean> getSouData(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);

    @PUT("small/order/verify/v1/syncShoppingCart")
    Observable<TongBuBean> addCar(@HeaderMap HashMap<String, Object> hashmap, @Query("data") String data);

    @GET("small/order/verify/v1/findShoppingCart")
    Observable<CarBean> getCarData(@HeaderMap HashMap<String, Object> map);

    @GET("small/commodity/v1/findFirstCategory")
    Observable<OneBean> getOneData();

    @GET("small/commodity/v1/findSecondCategory")
    Observable<TwoBean> getTwoData(@Query("firstCategoryId") String id);

    @POST("small/user/verify/v1/addReceiveAddress")
    Observable<AddAddress> addAddress(@HeaderMap HashMap<String, Object> headmap, @QueryMap HashMap<String, Object> map);

    @GET("small/user/verify/v1/receiveAddressList")
    Observable<RecivieAddress> quaryAddress(@HeaderMap HashMap<String, Object> headmap);

    @POST("small/order/verify/v1/createOrder")
    Observable<CreateOrder> createOrder(@HeaderMap HashMap<String, Object> headmap, @Query("orderInfo") String data, @Query("totalPrice") int totalPrice, @Query("addressId") int id);

    @POST("circle/verify/v1/releaseCircle")
    Observable<FaBu> faBuQuan(@HeaderMap HashMap<String, Object> headmap, @Query("commodityId") int id, @Query("content") String content, @Query("image") File file);


    @GET("small/order/verify/v1/findOrderListByStatus")
    Observable<OrderList> OrderList(@HeaderMap HashMap<String, Object> headmap, @Query("status") int status, @Query("page") int page, @Query("count") int count);


    @PUT("small/user/verify/v1/modifyUserNick")
    Observable<UpdatePwd> updateName(@HeaderMap HashMap<String, Object> headmap, @Query("nickName") String name);

    @GET("small/user/verify/v1/findUserWallet")
    Observable<MyWalletBean> myWallet(@HeaderMap HashMap<String, Object> headmap, @Query("page") int page, @Query("count") int count);

    @GET("small/commodity/verify/v1/browseList")
    Observable<MyFoot> getFootData(@HeaderMap HashMap<String, Object> headmap, @Query("page") int page, @Query("count") int count);

    @PUT("small/user/verify/v1/modifyUserPwd")
    Observable<UpdatePwd> updatePwd(@HeaderMap HashMap<String, Object> headmap, @Query("oldPwd") String oldPwd, @Query("newPwd") String newPwd);

}
