package example.com.geng.home;

import java.io.File;
import java.util.HashMap;

import example.com.geng.LoginActivity;
import example.com.geng.ReginActivity;
import example.com.geng.ZhanActivity;
import example.com.geng.activity.AddAddressActivity;
import example.com.geng.activity.FaBuActivity;
import example.com.geng.activity.MyAddressActivity;
import example.com.geng.activity.MyFootActivity;
import example.com.geng.activity.SelfActivity;
import example.com.geng.fragment.AllListFragment;
import example.com.geng.fragment.FirstFragment;
import example.com.geng.fragment.FiveFragment;
import example.com.geng.fragment.SecondFragment;
import example.com.geng.fragment.ThirdFragment;

public class HomePresenter implements IHomePresenter {
    FirstFragment firstFragment;

    private final HomeModel homeModel;

    LoginActivity loginActivity;

    ReginActivity reginActivity;

    ZhanActivity zhanActivity;

    SecondFragment secondFragment;

    FiveFragment fiveFragment;

    ThirdFragment thirdFragment;

    AddAddressActivity addAddressActivity;

    MyAddressActivity myAddressActivity;

    FaBuActivity faBuActivity;

    AllListFragment allListFragment;

    SelfActivity selfActivity;

    MyFootActivity myFootActivity;

    public HomePresenter(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
        homeModel = new HomeModel();
    }

    public HomePresenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        homeModel = new HomeModel();
    }

    public HomePresenter(ReginActivity reginActivity) {
        this.reginActivity = reginActivity;
        homeModel = new HomeModel();
    }

    public HomePresenter(ZhanActivity zhanActivity) {
        this.zhanActivity = zhanActivity;
        homeModel = new HomeModel();
    }

    public HomePresenter(SecondFragment secondFragment) {
        this.secondFragment = secondFragment;
        homeModel = new HomeModel();
    }

    public HomePresenter(FiveFragment fiveFragment) {
        this.fiveFragment = fiveFragment;
        homeModel = new HomeModel();
    }

    public HomePresenter(ThirdFragment thirdFragment) {
        this.thirdFragment = thirdFragment;
        homeModel = new HomeModel();
    }

    public HomePresenter(AddAddressActivity addAddressActivity) {
        this.addAddressActivity = addAddressActivity;
        homeModel = new HomeModel();
    }

    public HomePresenter(MyAddressActivity myAddressActivity) {
        this.myAddressActivity = myAddressActivity;
        homeModel = new HomeModel();
    }

    public HomePresenter(FaBuActivity faBuActivity) {
        this.faBuActivity = faBuActivity;
        homeModel = new HomeModel();
    }

    public HomePresenter(AllListFragment allListFragment) {
        this.allListFragment = allListFragment;
        homeModel = new HomeModel();
    }

    public HomePresenter(SelfActivity selfActivity) {
        this.selfActivity = selfActivity;
        homeModel = new HomeModel();
    }

    public HomePresenter(MyFootActivity myFootActivity) {
        this.myFootActivity = myFootActivity;
        homeModel = new HomeModel();
    }

    //登录
    @Override
    public void pLogin(String phone, String pwd) {

        homeModel.mLogin(phone, pwd, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                loginActivity.login(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    //注册
    @Override
    public void pRegin(String phone, String pwd) {

        homeModel.mRegin(phone, pwd, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                reginActivity.regin(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pLieData() {
        homeModel.mLieData(new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                firstFragment.getLieData(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pBanData() {
        homeModel.mBanData(new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                firstFragment.getBannerData(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    //详情
    @Override
    public void pXiang(String id) {
        homeModel.mXiang(id, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                zhanActivity.getZhan(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pQuan(int page, int count) {
        homeModel.MQuan(page, count, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                secondFragment.getQuan(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pSou(String keyword, int page, int count) {
        homeModel.mSou(keyword, page, count, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                firstFragment.getSouData(o);
            }

            @Override
            public void onFailed() {
            }
        });
    }

    @Override
    public void pGetCarDataFirst(HashMap<String, Object> map) {
        homeModel.mGetCarDataFirst(map, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                zhanActivity.quaryCar(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pAddCar(HashMap<String, Object> map, String data) {
        homeModel.mAddCar(map, data, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                zhanActivity.addShopCar(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pGetCarData(HashMap<String, Object> map) {
        homeModel.mGetCarData(map, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                thirdFragment.getCarData(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pGetOneData() {
        homeModel.mGetOne(new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                firstFragment.getOne(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pGetTwo(String id) {
        homeModel.mGetTwo(id, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                firstFragment.getTwo(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pAddAdress(HashMap<String, Object> headmap, HashMap<String, Object> map) {

        homeModel.mAddAdress(headmap, map, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                addAddressActivity.addAdress(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pQuaryAddress(HashMap<String, Object> map) {
        homeModel.mQuaryAddress(map, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                myAddressActivity.QuaryAddress(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pCreateOrder(HashMap<String, Object> headmap, String data, int price, int addressId) {
        homeModel.mCreateOrder(headmap, data, price, addressId, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                thirdFragment.createOrder(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pFaBu(HashMap<String, Object> headmap, int id, String content, File file) {
        homeModel.mFaBu(headmap, id, content, file, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                faBuActivity.getFaBu(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pOrderList(HashMap<String, Object> headmap, int status, int page, int count) {

        homeModel.mOrderList(headmap, status, page, count, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                allListFragment.getOderData(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pUpdataName(HashMap<String, Object> headmap, String name) {
        homeModel.mUpdateName(headmap, name, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                selfActivity.updataNickname(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pGetFootData(HashMap<String, Object> headmap, int page, int count) {

        homeModel.mGetFootData(headmap, page, count, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                myFootActivity.getData(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void pUpdatePwd(HashMap<String, Object> headmap, String oldPwd, String newPwd) {
        homeModel.mUpdatePwd(headmap, oldPwd, newPwd, new IHomeModel.HomeModelCallBack() {
            @Override
            public void success(Object o) {
                selfActivity.updatePwd(o);
            }

            @Override
            public void onFailed() {

            }
        });
    }


}
