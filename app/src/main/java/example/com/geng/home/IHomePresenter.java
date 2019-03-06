package example.com.geng.home;

import java.io.File;
import java.util.HashMap;

public interface IHomePresenter {
    void pLogin(String phone, String pwd);

    void pRegin(String phone, String pwd);

    void pLieData();

    void pBanData();

    void pXiang(String id);

    void pQuan(int page, int count);

    void pSou(String keyword, int page, int count);

    void pGetCarDataFirst(HashMap<String, Object> map);

    void pAddCar(HashMap<String, Object> map, String data);

    void pGetCarData(HashMap<String, Object> map);

    void pGetOneData();

    void pGetTwo(String id);

    void pAddAdress(HashMap<String, Object> headmap, HashMap<String, Object> map);

    void pQuaryAddress(HashMap<String, Object> map);

    void pCreateOrder(HashMap<String, Object> headmap, String data, int price, int addressId);

    void pFaBu(HashMap<String, Object> headmap, int id, String content, File file);

    void pOrderList(HashMap<String, Object> headmap, int status, int page, int count);

    void pUpdataName(HashMap<String, Object> headmap, String name);

    void pGetFootData(HashMap<String, Object> headmap, int page, int count);

    void pUpdatePwd(HashMap<String, Object> headmap, String oldPwd, String newPwd);
}
