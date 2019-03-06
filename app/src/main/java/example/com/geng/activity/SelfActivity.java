package example.com.geng.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.geng.R;
import example.com.geng.bean.UpdatePwd;
import example.com.geng.home.HomePresenter;

public class SelfActivity extends AppCompatActivity {

    @BindView(R.id.my_nickname)
    EditText myNickname;

    @BindView(R.id.save_ziliao)
    Button saveZiliao;
    @BindView(R.id.my_old_pwd)
    EditText myOldPwd;
    @BindView(R.id.my_new_pwd)
    EditText myNewPwd;
    private String name;
    private HomePresenter presenter;
    private HashMap<String, Object> map;
    private SharedPreferences login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        login = getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        saveZiliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = myNickname.getText().toString();
                String oldpwd = myOldPwd.getText().toString();
                String mynewpwd = myNewPwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(oldpwd) || TextUtils.isEmpty(mynewpwd)) {
                    Toast.makeText(SelfActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.pUpdataName(map, name);
                    presenter.pUpdatePwd(map,oldpwd,mynewpwd);
                }
            }
        });
    }

    public void updataNickname(Object o) {
        UpdatePwd updatePwd = (UpdatePwd) o;
        String message = updatePwd.getMessage();
        if (message.equals("修改成功")) {
            SharedPreferences.Editor edit = login.edit();
            edit.putString("name", name);
            edit.commit();
        } else {
            Toast.makeText(SelfActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePwd(Object o) {
        UpdatePwd updatePwd = (UpdatePwd) o;
        String message = updatePwd.getMessage();
        Toast.makeText(SelfActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}
