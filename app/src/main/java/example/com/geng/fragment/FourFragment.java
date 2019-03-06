package example.com.geng.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.com.geng.R;
import example.com.geng.adapter.ViewPagerAdapter;

public class FourFragment extends Fragment {


    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.all_list)
    TextView allList;
    @BindView(R.id.text_wait_pay)
    TextView textWaitPay;
    @BindView(R.id.text_wait_receive)
    TextView textWaitReceive;
    @BindView(R.id.text_wait_ping)
    TextView textWaitPing;
    @BindView(R.id.already_finish)
    TextView alreadyFinish;
    @BindView(R.id.ding_pager)
    ViewPager dingPager;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.four, container, false);
        unbinder = ButterKnife.bind(this, view);
        List<Fragment> list=new ArrayList<>();
        list.add(new AllListFragment());
        list.add(new WaitPayFragment());
        list.add(new WaitReceiveFragment());
        list.add(new WaitPingFragment());
        list.add(new AlreadyFisishFragment());
        FragmentManager fm = getActivity().getSupportFragmentManager();
        dingPager.setAdapter(new ViewPagerAdapter(fm,list));
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4, R.id.rb5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb1:dingPager.setCurrentItem(0);
                break;
            case R.id.rb2:dingPager.setCurrentItem(1);
                break;
            case R.id.rb3:dingPager.setCurrentItem(2);
                break;
            case R.id.rb4:dingPager.setCurrentItem(3);
                break;
            case R.id.rb5:dingPager.setCurrentItem(4);
                break;
        }
    }
}
