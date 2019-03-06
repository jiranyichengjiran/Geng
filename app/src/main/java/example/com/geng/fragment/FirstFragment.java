package example.com.geng.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import example.com.geng.R;
import example.com.geng.ZhanActivity;
import example.com.geng.adapter.MainAdapter;
import example.com.geng.adapter.OneAdapter;
import example.com.geng.adapter.SouAdapter;
import example.com.geng.adapter.TwoAdapter;
import example.com.geng.bean.BannerBean;
import example.com.geng.bean.OneBean;
import example.com.geng.bean.ShouBean;
import example.com.geng.bean.SouBean;
import example.com.geng.bean.TwoBean;
import example.com.geng.home.HomePresenter;

public class FirstFragment extends Fragment {


    private RecyclerView recycler;
    private BannerBean bannerBean;
    private ShouBean shouBean;
    private ImageView fan;
    private EditText guan;
    private TextView sou;
    private RecyclerView sou_recycler;
    private SouAdapter sadapter;
    private RecyclerView two_view;
    private RecyclerView one_view;
    private HomePresenter presenter;
    private OneBean oneBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.first, container, false);
        //注册eventbus
        EventBus.getDefault().register(this);
        recycler = view.findViewById(R.id.recycler);
        sou_recycler = view.findViewById(R.id.sou_recycler);
        fan = view.findViewById(R.id.fanhui);
        guan = view.findViewById(R.id.guan);
        sou = view.findViewById(R.id.sou);
        //首页请求数据
        presenter = new HomePresenter(this);
        presenter.pLieData();
        presenter.pBanData();
        sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = guan.getText().toString();
                if (s.isEmpty()) {
                    Toast.makeText(getActivity(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.pSou(s, 1, 7);
                }
            }
        });
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(getActivity(), R.layout.pop, null);
                PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setFocusable(true);
                popupWindow.setTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());//CENTER_VERTICAL
                popupWindow.showAtLocation(v, Gravity.RIGHT, 0, -850);
                one_view = view.findViewById(R.id.one_view);
                two_view = view.findViewById(R.id.two_view);
                presenter.pGetOneData();
                // Toast.makeText(getActivity(),"臣妾做不到啊",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void getBannerData(Object o) {
        bannerBean = (BannerBean) o;
        recycler.setAdapter(new MainAdapter(getActivity(), shouBean, bannerBean));
    }

    public void getLieData(Object o) {
        shouBean = (ShouBean) o;
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(manager);
        recycler.setAdapter(new MainAdapter(getActivity(), shouBean, bannerBean));
    }

    public void getSouData(Object o) {
        SouBean souBean = (SouBean) o;
        List<SouBean.ResultBean> result = souBean.getResult();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        sou_recycler.setLayoutManager(manager);
        sadapter = new SouAdapter(getActivity(), result);
        sou_recycler.setAdapter(sadapter);
    }

    public void getOne(Object o) {
        oneBean = (OneBean) o;
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        List<OneBean.ResultBean> result = oneBean.getResult();
        one_view.setLayoutManager(manager);
        OneAdapter adapter = new OneAdapter(getActivity(), result);
        one_view.setAdapter(adapter);
        adapter.getId(new OneAdapter.oneCallBack() {
            @Override
            public void getOneId(String id) {
                presenter.pGetTwo(id);
            }
        });
    }

    public void getTwo(Object o) {
        TwoBean twoBean = (TwoBean) o;
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        List<TwoBean.ResultBean> result = twoBean.getResult();
        two_view.setLayoutManager(manager);
        TwoAdapter adapter = new TwoAdapter(getActivity(), result);
        two_view.setAdapter(adapter);
        adapter.getName(new TwoAdapter.twoCallBack() {
            @Override
            public void getName(String name) {
                guan.setText(name);
            }
        });
    }

    @Subscribe //必写
    public void getMsg(String msg) {
        Intent intent = new Intent(getActivity(), ZhanActivity.class);
        intent.putExtra("id", msg);
        startActivity(intent);
    }

    //反注册
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
