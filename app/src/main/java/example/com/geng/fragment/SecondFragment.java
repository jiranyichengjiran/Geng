package example.com.geng.fragment;

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

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.geng.R;
import example.com.geng.adapter.QuanAdapter;
import example.com.geng.bean.QuanBean;
import example.com.geng.home.HomePresenter;

public class SecondFragment extends Fragment {
    @BindView(R.id.quan_recycler)
    XRecyclerView quanRecycler;
    Unbinder unbinder;

    private int page = 1;
    private int count = 5;
    private QuanAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second, container, false);
        final HomePresenter presenter = new HomePresenter(this);
        presenter.pQuan(page, count);
        unbinder = ButterKnife.bind(this, view);
        quanRecycler.setPullRefreshEnabled(true);
        quanRecycler.setLoadingMoreEnabled(true);
        quanRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        presenter.pQuan(page, count);
                        quanRecycler.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        presenter.pQuan(page, count);
                        quanRecycler.loadMoreComplete();
                    }
                }, 2000);
            }
        });
        return view;
    }

    public void getQuan(Object o) {
        QuanBean quanBean = (QuanBean) o;
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        quanRecycler.setLayoutManager(manager);
        List<QuanBean.ResultBean> result = quanBean.getResult();
        adapter = new QuanAdapter(getActivity(), result);
        quanRecycler.setAdapter(adapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
