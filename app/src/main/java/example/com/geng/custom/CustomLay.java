package example.com.geng.custom;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import example.com.geng.R;
import example.com.geng.adapter.CarAdapter;
import example.com.geng.bean.CarBean;

public class CustomLay extends RelativeLayout implements View.OnClickListener {

    private TextView add;
    private TextView jian;
    private EditText edit_num;
    private int num;
    private List<CarBean.ResultBean> list;
    private CarAdapter adapter;
    private int position;
    private Context mcontext;
    public CustomLay(Context context) {
        super(context);
        init(context);
    }

    public CustomLay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomLay(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.mcontext=context;
        View view = View.inflate(context, R.layout.custom, null);
        addView(view);
        add = view.findViewById(R.id.num_add1);
        jian = view.findViewById(R.id.num_jian1);
        edit_num = view.findViewById(R.id.edit_num1);
        add.setOnClickListener(this);
        jian.setOnClickListener(this);
        edit_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //改变数量
                num=Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getData(CarAdapter adapter, List<CarBean.ResultBean> list, int i) {
        this.adapter = adapter;
        this.list = list;
        this.position = i;
        int count = list.get(position).getCount();
        edit_num.setText(count+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.num_add:
                num++;
                edit_num.setText(num+"");
                list.get(position).setCount(num);
                customCallBack.callback();
                break;
            case R.id.num_jian:
                if(num>1)
                {
                    num--;
                }else {
                    Toast.makeText(mcontext,"我就没有底线吗",Toast.LENGTH_SHORT).show();
                }
                edit_num.setText(num+"");
                list.get(position).setCount(num);
                customCallBack.callback();
                break;*/

        }
    }
    private CustomCallBack customCallBack;

    public void setCustomCallBack(CustomCallBack customCallBack)
    {
        this.customCallBack=customCallBack;
    }
    public interface CustomCallBack{
        void callback();
    }
}
