package example.com.geng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.geng.bean.ReginBean;
import example.com.geng.home.HomePresenter;

public class ReginActivity extends AppCompatActivity {

    @BindView(R.id.regin_phone)
    EditText reginPhone;
    @BindView(R.id.regin_yan)
    EditText reginYan;
    @BindView(R.id.regin_pwd)
    EditText reginPwd;
    @BindView(R.id.must)
    TextView must;
    @BindView(R.id.regin)
    Button regin;
    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regin);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        regin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = reginPhone.getText().toString();
                String pwd = reginPwd.getText().toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(ReginActivity.this, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.pRegin(phone, pwd);
                }
            }
        });
        must.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void regin(Object object) {

        ReginBean reginBean = (ReginBean) object;
        String message = reginBean.getMessage();
        if (message.equals("注册成功")) {
            Toast.makeText(ReginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ReginActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(ReginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
        }

    }
}
