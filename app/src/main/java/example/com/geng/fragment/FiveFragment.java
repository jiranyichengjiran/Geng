package example.com.geng.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.com.geng.R;
import example.com.geng.activity.MyAddressActivity;
import example.com.geng.activity.MyFootActivity;
import example.com.geng.activity.MyMoneyActivity;
import example.com.geng.activity.MyQuanActivity;
import example.com.geng.activity.SelfActivity;

public class FiveFragment extends Fragment {

    @BindView(R.id.text_ge)
    TextView textGe;
    @BindView(R.id.text_quan)
    TextView textQuan;
    @BindView(R.id.text_zuji)
    TextView textZuji;
    @BindView(R.id.text_money)
    TextView textMoney;
    @BindView(R.id.text_address)
    TextView textAddress;
    Unbinder unbinder;
    @BindView(R.id.simview)
    SimpleDraweeView simview;
    @BindView(R.id.my_myname)
    TextView myMyname;
    @BindView(R.id.tuichu)
    Button tuichu;
    private SharedPreferences login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.five, container, false);
        unbinder = ButterKnife.bind(this, view);
        login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String name = login.getString("name", "");
        myMyname.setText(name);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.text_ge, R.id.text_quan, R.id.text_zuji, R.id.text_money, R.id.text_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_ge:
                Intent intent1 = new Intent(getActivity(), SelfActivity.class);
                startActivity(intent1);
                break;
            case R.id.text_quan:
                Intent intent2 = new Intent(getActivity(), MyQuanActivity.class);
                startActivity(intent2);
                break;
            case R.id.text_zuji:
                Intent intent3 = new Intent(getActivity(), MyFootActivity.class);
                startActivity(intent3);
                break;
            case R.id.text_money:
                Intent intent4 = new Intent(getActivity(), MyMoneyActivity.class);
                startActivity(intent4);
                break;
            case R.id.text_address:
                Intent intent = new Intent(getActivity(), MyAddressActivity.class);
                startActivity(intent);
                break;
        }
    }


}
