package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.OrderList;

public class OrderListAdapter extends XRecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    Context context;
    List<OrderList.OrderListBean> list;

    public OrderListAdapter(Context context, List<OrderList.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.order_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.orderId.setText("订单号:" + list.get(position).getOrderId()+"");
        List<OrderList.OrderListBean.DetailListBean> detailList = list.get(position).getDetailList();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        holder.list_cycler.setLayoutManager(manager);
        OrderAdapter adapter = new OrderAdapter(context, detailList);
        holder.list_cycler.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder {

        private final TextView orderId;
        private final RecyclerView list_cycler;

        public ViewHolder(View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.ding_order);

            list_cycler = itemView.findViewById(R.id.list_recycler);
        }
    }
}
