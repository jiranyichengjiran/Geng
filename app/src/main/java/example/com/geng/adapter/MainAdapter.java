package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.BannerBean;
import example.com.geng.bean.ShouBean;

public class MainAdapter extends RecyclerView.Adapter {
    Context context;
    ShouBean focusBean;
    BannerBean bannerBean;
    private final int BANNER = 0;
    private final int RXXP = 1;
    private final int MLSS = 2;
    private final int PZSH = 3;


    public MainAdapter(Context context, ShouBean focusBean, BannerBean bannerBean) {
        this.context = context;
        this.focusBean = focusBean;
        this.bannerBean = bannerBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == RXXP) {
            View view = View.inflate(context, R.layout.item_rxxp, null);
            return new ItemViewHodler1(view);
        } else if (i == MLSS) {
            View view = View.inflate(context, R.layout.item_mlss, null);
            return new ItemViewHodler2(view);
        } else if (i == PZSH) {
            View view = View.inflate(context, R.layout.item_pzsh, null);
            return new ItemViewHodler3(view);
        } else {
            View view = View.inflate(context, R.layout.banner, null);
            return new ItemViewHodler0(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof ItemViewHodler0) {
            List<BannerBean.ResultBean> result = bannerBean.getResult();
            final ArrayList<String> xbanner = new ArrayList<>();
            for (BannerBean.ResultBean resultBean : result) {
                xbanner.add(resultBean.getImageUrl());
            }
            ((ItemViewHodler0) holder).banner.setData(xbanner, null);
            ((ItemViewHodler0) holder).banner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(context).load(xbanner.get(position)).into((ImageView) view);
                }
            });
            ((ItemViewHodler0) holder).banner.setPageChangeDuration(1000);
            ((ItemViewHodler0) holder).banner.setPageTransformer(Transformer.Default);
        }
        if (holder instanceof ItemViewHodler1) {
            String name = focusBean.getResult().getRxxp().getName();
            ((ItemViewHodler1) holder).rxxp.setText(name);
            //创建布局管理器
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ItemViewHodler1) holder).rxxp_cycler.setLayoutManager(manager);
            List<ShouBean.ResultBean.RxxpBean.CommodityListBean> list = focusBean.getResult().getRxxp().getCommodityList();
            RxxpAdapter rxxpAdapter = new RxxpAdapter(context, list);
            ((ItemViewHodler1) holder).rxxp_cycler.setAdapter(rxxpAdapter);
        }
        if (holder instanceof ItemViewHodler2) {
            String name = focusBean.getResult().getMlss().getName();
            ((ItemViewHodler2) holder).mlss.setText(name);
            //创建布局管理器
            LinearLayoutManager manager = new LinearLayoutManager(context);
            ((ItemViewHodler2) holder).mlss_cycler.setLayoutManager(manager);
            List<ShouBean.ResultBean.MlssBean.CommodityListBeanXX> list = focusBean.getResult().getMlss().getCommodityList();
            MlssAdapter mlssAdapter = new MlssAdapter(context, list);
            ((ItemViewHodler2) holder).mlss_cycler.setAdapter(mlssAdapter);
        }
        if (holder instanceof ItemViewHodler3) {
            String name = focusBean.getResult().getPzsh().getName();
            ((ItemViewHodler3) holder).pzsh.setText(name);
            //创建布局管理器
            GridLayoutManager manager = new GridLayoutManager(context, 2);
            ((ItemViewHodler3) holder).pzsh_cycler.setLayoutManager(manager);
            List<ShouBean.ResultBean.PzshBean.CommodityListBeanX> list = focusBean.getResult().getPzsh().getCommodityList();
            PzshAdapter pzshAdapter = new PzshAdapter(context, list);
            ((ItemViewHodler3) holder).pzsh_cycler.setAdapter(pzshAdapter);
        }


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER;
            case 1:
                return RXXP;
            case 2:
                return MLSS;
        }
        return PZSH;
    }

    class ItemViewHodler1 extends RecyclerView.ViewHolder {

        private final TextView rxxp;
        private final RecyclerView rxxp_cycler;

        public ItemViewHodler1(@NonNull View itemView) {
            super(itemView);
            rxxp = itemView.findViewById(R.id.rxxp);
            rxxp_cycler = itemView.findViewById(R.id.rxxp_cycler);
        }
    }

    class ItemViewHodler2 extends RecyclerView.ViewHolder {

        private final TextView mlss;
        private final RecyclerView mlss_cycler;

        public ItemViewHodler2(@NonNull View itemView) {
            super(itemView);
            mlss = itemView.findViewById(R.id.mlss);
            mlss_cycler = itemView.findViewById(R.id.mlss_cycler);
        }
    }

    class ItemViewHodler3 extends RecyclerView.ViewHolder {

        private final TextView pzsh;
        private final RecyclerView pzsh_cycler;

        public ItemViewHodler3(@NonNull View itemView) {
            super(itemView);
            pzsh = itemView.findViewById(R.id.pzsh);
            pzsh_cycler = itemView.findViewById(R.id.pzsh_cycler);

        }
    }

    class ItemViewHodler0 extends RecyclerView.ViewHolder {

        private final XBanner banner;

        public ItemViewHodler0(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

}
