package example.com.geng.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.geng.R;
import example.com.geng.adapter.OrderListAdapter;
import example.com.geng.bean.OrderList;
import example.com.geng.home.HomePresenter;

public class AllListFragment extends Fragment {
    @BindView(R.id.all_list_recycler)
    XRecyclerView allListRecycler;
    Unbinder unbinder;
    private HashMap<String, Object> map;
    private int status = 0;
    private int page = 1;
    int count = 5;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        final HomePresenter presenter = new HomePresenter(this);
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter.pOrderList(map, status, page, count);
        allListRecycler.setPullRefreshEnabled(true);
        allListRecycler.setLoadingMoreEnabled(true);
        allListRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.pOrderList(map,status,page,count);
                    }
                },3000);
                allListRecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });
        return view;
    }

    public void getOderData(Object o) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        allListRecycler.setLayoutManager(manager);
        OrderList orderList = (OrderList) o;
        List<OrderList.OrderListBean> list = orderList.getOrderList();
        if (list.size() > 0) {
            OrderListAdapter adapter = new OrderListAdapter(getActivity(), list);
            allListRecycler.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), "暂时没有订单哦", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
