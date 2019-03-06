package example.com.geng.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.QuanBean;

public class QuanAdapter extends XRecyclerView.Adapter {
    Context context;
    List<QuanBean.ResultBean> list;

    public QuanAdapter(Context context, List<QuanBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {

        View view = View.inflate(context, R.layout.quan_item2, null);
        return new TwoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TwoViewHolder) holder).name2.setText(list.get(position).getNickName());
        ((TwoViewHolder) holder).time2.setText(list.get(position).getCreateTime() + "");
        ((TwoViewHolder) holder).title2.setText(list.get(position).getContent());
        Glide.with(context)
                .load(list.get(position).getHeadPic())
                .into(((TwoViewHolder) holder).tou2);
        Glide.with(context)
                .load(list.get(position).getImage())
                .into(((TwoViewHolder) holder).img2);
        final int id = list.get(position).getId();
        ((TwoViewHolder) holder).zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class TwoViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView tou2;
        private final TextView name2;
        private final TextView time2;
        private final TextView title2;
        private final ImageView img2;
        private final ImageView zan;
        private final TextView zan_num;

        public TwoViewHolder(View itemView) {
            super(itemView);
            tou2 = itemView.findViewById(R.id.quan_tou2);
            name2 = itemView.findViewById(R.id.quan_name2);
            time2 = itemView.findViewById(R.id.quan_time2);
            title2 = itemView.findViewById(R.id.quan_title2);
            img2 = itemView.findViewById(R.id.quan_image2);
            zan = itemView.findViewById(R.id.zan);
            zan_num = itemView.findViewById(R.id.zan_num);
        }
    }

    private dianZan dianZan;

    public void setDianZan(dianZan dianZan) {
        this.dianZan = dianZan;
    }

    public interface dianZan {
        void getId(int id);
    }

}

