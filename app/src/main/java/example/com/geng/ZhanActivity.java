package example.com.geng;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.geng.bean.AddBean;
import example.com.geng.bean.CarBean;
import example.com.geng.bean.TongBuBean;
import example.com.geng.bean.XiangBean;
import example.com.geng.home.HomePresenter;

public class ZhanActivity extends AppCompatActivity {

    @BindView(R.id.add_car)
    Button addCar;
    @BindView(R.id.xiang_banner)
    FlyBanner xiangBanner;
    @BindView(R.id.buy)
    Button buy;
    @BindView(R.id.web_view)
    WebView webView;

    private HomePresenter presenter;
    private XiangBean xiangBean;
    private int commodityId;
    private String sessionId;
    private int userId;
    private String id;
    private HashMap<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhan);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        presenter = new HomePresenter(this);
        presenter.pXiang(id);
        initData();
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
    }


    public void getZhan(Object o) {
        xiangBean = (XiangBean) o;
        commodityId = xiangBean.getResult().getCommodityId();
        String picture = xiangBean.getResult().getPicture();
        String[] split = picture.split("\\,");
        List<String> bannerData = new ArrayList<>();
        for (String s : split) {
            bannerData.add(s);
        }
        xiangBanner.setImagesUrl(bannerData);
        xiangBanner.startAutoPlay();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        String js = "<script type=\"text/javascript\">" +
                "var imgs=document.getElementsByTagName('img');" +
                "for(var i=0;i<imgs.length;i++){" +
                "imgs[i].style.width='100%';" +
                "imgs[i].style.height='auto';" +
                "}" +
                "</script>";
        webView.loadDataWithBaseURL(null, xiangBean.getResult().getDetails() + js,
                "text/html", "utf-8", null);

    }

    private void initData() {
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new HashMap<>();
                map.put("userId", userId);
                map.put("sessionId", sessionId);
                presenter.pGetCarDataFirst(map);
            }
        });

    }

    public void quaryCar(Object o) {
        CarBean carBean = (CarBean) o;
        List<CarBean.ResultBean> result = carBean.getResult();
        List<AddBean> list = new ArrayList<>();
        for (CarBean.ResultBean resultBean : result) {
            list.add(new AddBean(resultBean.getCommodityId(), resultBean.getCount()));
        }
        if (list.size() == 0) {
            list.add(new AddBean(Integer.parseInt(id), 1));
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCommodityId() == Integer.parseInt(id)) {
                    list.get(i).setCount(list.get(i).getCount() + 1);
                    break;
                } else if (i == list.size() - 1) {
                    list.add(new AddBean(Integer.parseInt(id), 1));
                    break;
                }
            }
        }
        String s = new Gson().toJson(list);
        presenter.pAddCar(map, s);


    }

    public void addShopCar(Object o) {
        TongBuBean tongBuBean = (TongBuBean) o;
        String message = tongBuBean.getMessage();
        Toast.makeText(ZhanActivity.this, message, Toast.LENGTH_SHORT).show();
    }


}
