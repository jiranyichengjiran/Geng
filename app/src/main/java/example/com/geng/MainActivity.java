package example.com.geng;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.geng.fragment.FirstFragment;
import example.com.geng.fragment.FiveFragment;
import example.com.geng.fragment.FourFragment;
import example.com.geng.fragment.SecondFragment;
import example.com.geng.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bttombar)
    BottomTabBar bttombar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bttombar.init(getSupportFragmentManager())
                .setImgSize(100,100)
                .setFontSize(8)
                .setTabPadding(10,10,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem(" ",R.drawable.fang,FirstFragment.class)
                .addTabItem("  ",R.drawable.ufo,SecondFragment.class)
                .addTabItem("   ",R.drawable.car1,ThirdFragment.class)
                .addTabItem("    ",R.drawable.bens,FourFragment.class)
                .addTabItem("     ",R.drawable.mys,FiveFragment.class)
                .isShowDivider(false);


    }
}
