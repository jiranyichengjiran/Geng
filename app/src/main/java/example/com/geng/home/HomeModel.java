package example.com.geng.home;

import android.widget.Toast;

import java.io.File;
import java.util.HashMap;

import example.com.geng.activity.MyAddressActivity;
import example.com.geng.api.Api;
import example.com.geng.api.UserApi;
import example.com.geng.app.App;
import example.com.geng.bean.AddAddress;
import example.com.geng.bean.AddCAr;
import example.com.geng.bean.BannerBean;
import example.com.geng.bean.CarBean;
import example.com.geng.bean.CreateOrder;
import example.com.geng.bean.FaBu;
import example.com.geng.bean.LoginBean;
import example.com.geng.bean.MyFoot;
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
import example.com.geng.network.RetrofitUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements IHomeModel {
    //登录
    @Override
    public void mLogin(String phone, String pwd, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.login(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //注册
    @Override
    public void mRegin(String phone, String pwd, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.regin(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ReginBean>() {
                    @Override
                    public void onNext(ReginBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mLieData(final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getShouData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ShouBean>() {
                    @Override
                    public void onNext(ShouBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mBanData(final HomeModelCallBack callBack) {

        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getBanData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<BannerBean>() {
                    @Override
                    public void onNext(BannerBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mXiang(String id, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.zhan(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<XiangBean>() {
                    @Override
                    public void onNext(XiangBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void MQuan(int page, int count, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getQuanData(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QuanBean>() {
                    @Override
                    public void onNext(QuanBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mSou(String keyword, int page, int count, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getSouData(keyword, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SouBean>() {
                    @Override
                    public void onNext(SouBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mGetCarDataFirst(HashMap<String, Object> map, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getCarData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CarBean>() {
                    @Override
                    public void onNext(CarBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mAddCar(HashMap<String, Object> map, String data, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.addCar(map, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<TongBuBean>() {
                    @Override
                    public void onNext(TongBuBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mGetCarData(HashMap<String, Object> map, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getCarData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CarBean>() {
                    @Override
                    public void onNext(CarBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mGetOne(final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getOneData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<OneBean>() {
                    @Override
                    public void onNext(OneBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mGetTwo(String id, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getTwoData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<TwoBean>() {
                    @Override
                    public void onNext(TwoBean value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mAddAdress(HashMap<String, Object> headmap, HashMap<String, Object> map, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.addAddress(headmap, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AddAddress>() {
                    @Override
                    public void onNext(AddAddress value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mQuaryAddress(HashMap<String, Object> map, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.quaryAddress(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecivieAddress>() {
                    @Override
                    public void accept(RecivieAddress recivieAddress) throws Exception {
                        callBack.success(recivieAddress);
                    }
                });

    }

    @Override
    public void mCreateOrder(HashMap<String, Object> headmap, String data, int price, int addressId, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.createOrder(headmap, data, price, addressId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CreateOrder>() {
                    @Override
                    public void onNext(CreateOrder value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mFaBu(HashMap<String, Object> headmap, int id, String content, File file, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.faBuQuan(headmap, id, content, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FaBu>() {
                    @Override
                    public void onNext(FaBu value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mOrderList(HashMap<String, Object> headmap, int status, int page, int count, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.OrderList(headmap, status, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<OrderList>() {
                    @Override
                    public void onNext(OrderList value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mUpdateName(HashMap<String, Object> headmap, String name, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.updateName(headmap, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UpdatePwd>() {
                    @Override
                    public void onNext(UpdatePwd value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void mGetFootData(HashMap<String, Object> headmap, int page, int count, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.getFootData(headmap, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<MyFoot>() {
                    @Override
                    public void onNext(MyFoot value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void mUpdatePwd(HashMap<String, Object> headmap, String oldPwd, String newPwd, final HomeModelCallBack callBack) {
        UserApi userApi = RetrofitUtils.getInstance().create(UserApi.class);
        userApi.updatePwd(headmap, oldPwd, newPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UpdatePwd>() {
                    @Override
                    public void onNext(UpdatePwd value) {
                        callBack.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
