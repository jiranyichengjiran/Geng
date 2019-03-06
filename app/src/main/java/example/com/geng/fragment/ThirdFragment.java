package example.com.geng.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.geng.R;
import example.com.geng.ZhanActivity;
import example.com.geng.adapter.CarAdapter;
import example.com.geng.bean.AddBean;
import example.com.geng.bean.CarBean;
import example.com.geng.bean.CreateBean;
import example.com.geng.bean.CreateOrder;
import example.com.geng.home.HomePresenter;

public class ThirdFragment extends Fragment {
    @BindView(R.id.car_cycler)
    XRecyclerView carCycler;
    @BindView(R.id.all_chick)
    CheckBox allChick;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.jie)
    Button jie;
    Unbinder unbinder;
    private CarBean carBean;
    private HomePresenter presenter;
    private HashMap<String, Object> map;
    private CarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new HomePresenter(this);
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter.pGetCarData(map);
        carCycler.setLoadingMoreEnabled(true);
        carCycler.setPullRefreshEnabled(true);
        carCycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.pGetCarData(map);
                    }
                }, 3000);
                carCycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });
        return view;
    }

    public void getCarData(Object o) {
        carBean = (CarBean) o;
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        carCycler.setLayoutManager(manager);
        final List<CarBean.ResultBean> result = carBean.getResult();
        adapter = new CarAdapter(getActivity(), result);
        carCycler.setAdapter(adapter);
        allChick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CarBean.ResultBean resultBean : result) {
                    resultBean.setCheck(allChick.isChecked());
                }
                adapter.notifyDataSetChanged();
                allPrice.setText("¥" + getPrice(result));
                jie.setText(getNum(result) + "");
            }
        });
        adapter.getOnClick(new CarAdapter.SumCall() {
            @Override
            public void onClickJiaOrJian(List<CarBean.ResultBean> list) {
                allPrice.setText("¥" + getPrice(result));
            }
        });
        adapter.getOnSelect(new CarAdapter.SelectCall() {
            @Override
            public void onAllOn(List<CarBean.ResultBean> list) {
                boolean flag = true;
                for (CarBean.ResultBean resultBean : list) {
                    if (!resultBean.isCheck()) {
                        flag = false;
                    }
                    allPrice.setText("¥" + getPrice(result));
                    allChick.setChecked(flag);
                }
            }
        });
        initData();

    }
    public double getPrice(List<CarBean.ResultBean> list) {
        double price = 0.0;
        for (CarBean.ResultBean resultBean : list) {
            if (resultBean.isCheck()) {
                price += resultBean.getCount() * resultBean.getPrice();
            }
        }
        return price;
    }

    public double getNum(List<CarBean.ResultBean> list) {
        int num = 0;
        for (CarBean.ResultBean resultBean : list) {
            if (resultBean.isCheck()) {
                num += resultBean.getCount();
            }
        }
        return num;
    }

    private void initData() {
        jie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price = 0;
                List<CarBean.ResultBean> result = carBean.getResult();
                List<AddBean> list = new ArrayList<>();
                for (CarBean.ResultBean resultBean : result) {
                    list.add(new AddBean(resultBean.getCommodityId(), resultBean.getCount()));
                    price += (resultBean.getCount() * resultBean.getPrice());
                }
                Toast.makeText(getActivity(), price + "", Toast.LENGTH_SHORT).show();
                String s = jie.getText().toString();
                String data = new Gson().toJson(list);
                Log.e("zzzzzzz", data);
                presenter.pCreateOrder(map, data, price, 258);
            }
        });

    }

    public void createOrder(Object o) {
        CreateOrder createOrder = (CreateOrder) o;
        Toast.makeText(getActivity(), createOrder.getMessage() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
