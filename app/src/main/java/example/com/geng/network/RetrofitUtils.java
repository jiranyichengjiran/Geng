package example.com.geng.network;

import java.util.concurrent.TimeUnit;

import example.com.geng.api.Api;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private final Retrofit retrofit;

    //单例
    private static final class SINGLE_INSTANCE {
        public static final RetrofitUtils _IMSTANCE = new RetrofitUtils();
    }

    //方法请求
    public static RetrofitUtils getInstance() {
        return SINGLE_INSTANCE._IMSTANCE;
    }

    private RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build();
    }

    //设置读写超时
    private OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
