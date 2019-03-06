package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.OrderList;

public class OrderAdapter extends XRecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    List<OrderList.OrderListBean.DetailListBean> list;

    public OrderAdapter(Context context, List<OrderList.OrderListBean.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.order, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getCommodityPic().split("\\,")[0])
                .into(holder.img);
        holder.name.setText(list.get(position).getCommodityName());
        holder.num.setText(list.get(position).getCommodityCount() + "");
        holder.price.setText(list.get(position).getCommodityPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView name;
        private final TextView price;
        private final TextView add;
        private final TextView jian;
        private final EditText num;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.order_img);
            name = itemView.findViewById(R.id.order_name);
            price = itemView.findViewById(R.id.order_price);
            add = itemView.findViewById(R.id.order_num_add);
            jian = itemView.findViewById(R.id.order_num_jian);
            num = itemView.findViewById(R.id.order_num);
        }
    }
}
