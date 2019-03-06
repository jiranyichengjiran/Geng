package example.com.geng.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(initLayout());
        //初始化控件
        initView();
        //设置数据
        initData();
    }


    public abstract int initLayout();


    public abstract void initView();

    public abstract void initData();
}
