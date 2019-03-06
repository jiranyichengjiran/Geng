package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.RecivieAddress;

public class AddressAdapter extends XRecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context context;
    List<RecivieAddress.ResultBean> list;

    public AddressAdapter(Context context, List<RecivieAddress.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.address, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getRealName());
        holder.phone.setText(list.get(position).getPhone());
        holder.address.setText(list.get(position).getAddress());
        holder.orderId.setText(list.get(position).getId() + "");
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callOrderBack.getId(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView address;
        private final TextView update;
        private final TextView delete;
        private final CheckBox moren;
        private final TextView orderId;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.addr_name);
            phone = itemView.findViewById(R.id.addr_phone);
            address = itemView.findViewById(R.id.addr_addr);
            update = itemView.findViewById(R.id.addr_update);
            delete = itemView.findViewById(R.id.addr_delete);
            moren = itemView.findViewById(R.id.moren);
            orderId = itemView.findViewById(R.id.orderid);
        }
    }

    public CallOrderBack callOrderBack;

    public void SetOrderId(CallOrderBack callOrderBack) {
        this.callOrderBack = callOrderBack;
    }

    public interface CallOrderBack {
        void getId(int orderId);
    }

}
