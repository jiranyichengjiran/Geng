package example.com.geng.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.geng.R;
import example.com.geng.bean.FaBu;
import example.com.geng.home.HomePresenter;

public class FaBuActivity extends AppCompatActivity {

    @BindView(R.id.fabu_img)
    ImageView fabuImg;
    @BindView(R.id.fabu_content)
    EditText fabuContent;
    @BindView(R.id.zouzhe)
    Button zouzhe;
    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
    }

    @OnClick(R.id.zouzhe)
    public void onViewClicked() {
        String s = fabuContent.getText().toString();
        File file = new File("");

        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        final HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        presenter.pFaBu(map, 1, s, file);
    }

    public void getFaBu(Object o) {
        FaBu faBu = (FaBu) o;
        Toast.makeText(FaBuActivity.this, faBu.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
