package example.com.geng.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.geng.R;
import example.com.geng.adapter.AddressAdapter;
import example.com.geng.bean.RecivieAddress;
import example.com.geng.home.HomePresenter;

public class MyAddressActivity extends AppCompatActivity {

    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.add_address)
    Button addAddress;
    @BindView(R.id.address_cycler)
    XRecyclerView addressCycler;
    private AddressAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        final HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        final HomePresenter presenter = new HomePresenter(this);
        presenter.pQuaryAddress(map);
        addressCycler.setPullRefreshEnabled(true);
        addressCycler.setLoadingMoreEnabled(true);
        addressCycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.pQuaryAddress(map);
                    }
                }, 3000);
                addressCycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyAddressActivity.this, "暂无更多数据", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
                addressCycler.refreshComplete();
            }
        });

    }

    @OnClick({R.id.finish, R.id.add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                break;
            case R.id.add_address:
                Intent intent = new Intent(MyAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void QuaryAddress(Object o) {
        RecivieAddress recivieAddress = (RecivieAddress) o;
        List<RecivieAddress.ResultBean> list = recivieAddress.getResult();
        if (list.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            addressCycler.setLayoutManager(manager);
            adapter = new AddressAdapter(MyAddressActivity.this, list);
            addressCycler.setAdapter(adapter);
        } else {
            Toast.makeText(MyAddressActivity.this, "暂时没有地址哦,快去添加吧", Toast.LENGTH_SHORT).show();
        }
        adapter.SetOrderId(new AddressAdapter.CallOrderBack() {
            @Override
            public void getId(int orderId) {
                //修改地址
            }
        });
    }
}
