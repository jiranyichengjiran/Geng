package example.com.geng.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.geng.R;
import example.com.geng.adapter.MyFootAdapter;
import example.com.geng.bean.MyFoot;
import example.com.geng.home.HomePresenter;

public class MyFootActivity extends AppCompatActivity {

    @BindView(R.id.myfootcycler)
    XRecyclerView myfootcycler;
    private HashMap<String, Object> map;
    private SharedPreferences login;
    private int page = 1;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_foot);
        ButterKnife.bind(this);
        final HomePresenter presenter = new HomePresenter(this);
        login = getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter.pGetFootData(map, page, count);
        myfootcycler.setLoadingMoreEnabled(true);
        myfootcycler.setPullRefreshEnabled(true);
        myfootcycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        presenter.pGetFootData(map, page, count);
                        myfootcycler.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        presenter.pGetFootData(map, page, count);
                        myfootcycler.loadMoreComplete();
                    }
                }, 3000);
            }
        });
    }

    public void getData(Object o) {

        MyFoot myFoot = (MyFoot) o;
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        myfootcycler.setLayoutManager(manager);
        List<MyFoot.ResultBean> result = myFoot.getResult();
        MyFootAdapter adapter = new MyFootAdapter(MyFootActivity.this, result);
        if (result.size() > 0) {
            myfootcycler.setAdapter(adapter);
        } else {
            Toast.makeText(MyFootActivity.this, "暂时没有新的足迹哦,快去逛逛吧", Toast.LENGTH_SHORT).show();
        }
    }
}
