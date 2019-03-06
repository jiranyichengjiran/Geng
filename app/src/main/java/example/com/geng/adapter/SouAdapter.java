package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.ShouBean;
import example.com.geng.bean.SouBean;


public class SouAdapter extends RecyclerView.Adapter<SouAdapter.ViewHolder> {
    Context context;
    List<SouBean.ResultBean> list;

    public SouAdapter(Context context, List<SouBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.sou, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.name.setText(list.get(i).getCommodityName());
        holder.price.setText(list.get(i).getPrice() + ".00");
        holder.image.setImageURI(list.get(i).getMasterPic());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(list.get(i).getCommodityId() + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView image;
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.sou_image);
            name = itemView.findViewById(R.id.sou_name);
            price = itemView.findViewById(R.id.sou_price);
        }
    }

}

