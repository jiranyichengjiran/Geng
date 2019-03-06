package example.com.geng.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.config.AutoLayoutConifg;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
