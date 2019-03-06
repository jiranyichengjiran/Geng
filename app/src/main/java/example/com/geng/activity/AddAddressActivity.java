package example.com.geng.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.geng.R;
import example.com.geng.bean.AddAddress;
import example.com.geng.home.HomePresenter;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

public class AddAddressActivity extends AppCompatActivity implements CityPickerListener {

    @BindView(R.id.receive_person)
    EditText receivePerson;
    @BindView(R.id.address_phone)
    EditText addressPhone;
    @BindView(R.id.suo_address)
    EditText suoAddress;
    @BindView(R.id.san_ji_lian_dong)
    ImageView sanJiLianDong;
    @BindView(R.id.xiang_address)
    EditText xiangAddress;
    @BindView(R.id.zip_code)
    EditText zipCode;
    @BindView(R.id.xinzeng)
    Button xinzeng;
    private CityPicker cityPicker;
    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        cityPicker = new CityPicker(AddAddressActivity.this, this);
    }

    @OnClick({R.id.san_ji_lian_dong, R.id.xinzeng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.san_ji_lian_dong:
                cityPicker.show();
                break;
            case R.id.xinzeng:
                String name = receivePerson.getText().toString();
                String phone = addressPhone.getText().toString();
                String address = suoAddress.getText().toString();
                String code = zipCode.getText().toString();
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(address)||TextUtils.isEmpty(code))
                {
                    Toast.makeText(AddAddressActivity.this,"请完善信息",Toast.LENGTH_SHORT).show();
                }else
                {

                    HashMap<String, Object> headmap = new HashMap<>();
                    SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                    int userId = login.getInt("userId", 0);
                    String sessionId = login.getString("sessionId", "");
                    headmap.put("userId",userId);
                    headmap.put("sessionId",sessionId);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("realName",name);
                    map.put("phone",phone);
                    map.put("address",address);
                    map.put("zipCode",code);
                    presenter.pAddAdress(headmap,map);
                }
                break;
        }
    }
    public void addAdress(Object o)
    {
        AddAddress addAddress=(AddAddress)o;
        String message = addAddress.getMessage();
        if(message.equals("添加成功"))
        {
            finish();
        }

    }

    @Override
    public void getCity(String s) {
        suoAddress.setText(s);
    }

    @Override
    public void onBackPressed() {

        if (cityPicker.isShow()) {
            cityPicker.close();
            return;
        }
        super.onBackPressed();
    }
}
