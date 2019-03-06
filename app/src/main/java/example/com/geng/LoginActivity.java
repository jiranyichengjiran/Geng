package example.com.geng;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.geng.bean.LoginBean;
import example.com.geng.home.HomePresenter;
import example.com.geng.network.NetWorkUtils;

public class LoginActivity extends AutoLayoutActivity {

    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.rem_pwd)
    CheckBox remPwd;
    @BindView(R.id.fast_regin)
    TextView fastRegin;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.image_main)
    ImageView imageMain;
    private HomePresenter presenter;
    private NetWorkUtils utils;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag", false);
        remPwd.setChecked(flag);
        if (flag) {
            String phone = sp.getString("phone", "");
            String pwd = sp.getString("pwd", "");
            loginPhone.setText(phone);
            loginPwd.setText(pwd);
        }
        presenter = new HomePresenter(this);
        this.login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String phone = loginPhone.getText().toString();
                String pwd = loginPwd.getText().toString();
                utils = new NetWorkUtils();
                if (getNet()) {
                    imageMain.setVisibility(View.GONE);
                    if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
                        Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        presenter.pLogin(phone, pwd);
                    }
                } else {
                    imageMain.setVisibility(View.VISIBLE);
                    new AlertDialog.Builder(LoginActivity.this)
                            .setIcon(R.drawable.ic_launcher_background)
                            .setTitle("网络不可用,去设置网络")
                            //.setView(msg)
                            .setPositiveButton("确定",
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {
                                            // 跳转到设置界面
                                            LoginActivity.this.startActivityForResult(new Intent(Settings.ACTION_WIRELESS_SETTINGS), 0);
                                        }
                                    }).create().show();
                }
            }
        });
        fastRegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ReginActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean getNet() {
        if (utils.isNetworkConnected(LoginActivity.this)) {
            if (utils.isWifiConnected(LoginActivity.this)) {
                //Toast.makeText(LoginActivity.this, "WIFI已连接", Toast.LENGTH_SHORT).show();
            } else if (utils.isMobileConnected(LoginActivity.this)) {
                //Toast.makeText(LoginActivity.this, "数据已连接", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }


    public void login(Object o) {
        LoginBean loginBean = (LoginBean) o;
        int userId = loginBean.getResult().getUserId();
        String sessionId = loginBean.getResult().getSessionId();
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = login.edit();
        edit.putInt("userId", userId);
        edit.putString("sessionId", sessionId);
        String message = loginBean.getMessage();
        if (message.equals("登录成功")) {
            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            if (remPwd.isChecked()) {
                edit.putString("phone", loginPhone.getText().toString());
                edit.putString("pwd", loginPwd.getText().toString());
                edit.putBoolean("flag", true);
            } else {
                edit.putBoolean("flag", false);
            }
            edit.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
        }
    }

}
